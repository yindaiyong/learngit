package com.sams.batchfile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sams.batchfile.service.FxqUnResidentService;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.*;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.AccountInfoMapper;
import com.sams.custom.mapper.ChannelInfoMapper;
import com.sams.custom.mapper.DFxqUnresidentDataMapper;
import com.sams.custom.mapper.ResidentTaxInformMapper;
import com.sams.custom.model.*;
import com.sams.custom.model.result.CreateExcelBean;
import com.sams.custom.model.result.UnResidentInfoRes;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName 反洗钱非居民信息service
 * 描述 :
 * @Author weijunjie
 * @Date 2020/3/17 14:06
 */
@Service
public class FxqUnResidentServiceImpl implements FxqUnResidentService {

    @Autowired
    private DFxqUnresidentDataMapper fxqUnresidentDataMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private ResidentTaxInformMapper residentTaxInformMapper;


    /**
     * @Description 展示数据查询结果
     * @Author weijunjie
     * @Date 2020/3/20 8:41
     **/
    public List<DFxqUnresidentData> getAllFxqDataToShow(Map<String, Object> condition, PageInfo pageInfo){
        PageHelperUtils.startPage(pageInfo);
        String date = MapUtils.getString(condition, "date", null);
        if(StringUtils.isNotBlank(date)){
            condition.put("date",date.replaceAll("-",""));
        }else{
            condition.put("date",null);
        }
        List<DFxqUnresidentData> dFxqUnresidentDataList = fxqUnresidentDataMapper.getAllDataToShow(condition,pageInfo);
        return dFxqUnresidentDataList;
    }

    /**
     * @Description 获取反洗钱非居民信息数据模板
     * @Author weijunjie
     * @Date 2020/3/17 17:57
     **/
    public Workbook getTemplate(String type){
        Workbook workbook = null;
        List<Map<String, Object>> jsonObjects = new ArrayList<>();
        LinkedHashMap<String, String> aipTitleForPersonal = FxqTitleUtil.getAIPTitleForPersonal();
        if("2".equals(type)){
            //双sheet模板
            aipTitleForPersonal = FxqTitleUtil.getAIPTitleForOrgan();
            CreateExcelBean createExcelBean = new CreateExcelBean();
            createExcelBean.setSheetTitle(FxqTitleUtil.getAIPTitleForPersonal());
            createExcelBean.setSheetName("个人模板");
            workbook =PublicCreateExcelUtils.createOrgTemp(aipTitleForPersonal,null,jsonObjects,"机构模板",createExcelBean);
        }else if("0".equals(type)){
            //机构
            aipTitleForPersonal = FxqTitleUtil.getAIPTitleForOrgan();
            workbook =PublicCreateExcelUtils.createOrgTemp(aipTitleForPersonal,null,jsonObjects,"机构模板",null);
        }else if("1".equals(type)){
            //个人
            aipTitleForPersonal = FxqTitleUtil.getAIPTitleForPersonal();
            workbook =PublicCreateExcelUtils.createWorkbook(aipTitleForPersonal,null,jsonObjects,"个人模板");
        }
        return workbook;

    }

    /**
     * @Description 接收上传文件数据（文件中已经有渠道 产品等信息数据，前端可以不用选择渠道进行上传）
     * @Author weijunjie
     * @Date 2020/3/18 8:53
     **/
    public String importFxqFile(InputStream inputStream,String type,String channelCode){
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            List<DFxqUnresidentData> beanFromExcelData = new ArrayList<>();
            if("2".equals(type)){
                //多sheet上传
                Sheet sheet = workbook.getSheetAt(0);
                Sheet sheet2 = workbook.getSheetAt(1);
                //验证两次数据标题title
                String s = checkExcelTitlePerson(sheet.getRow(0));
                if(!ExceptionConStants.retCode_0000.equals(s)){
                    return s;
                }
                s = checkExcelTitleOrg(sheet2.getRow(0));
                if(!ExceptionConStants.retCode_0000.equals(s)){
                    return s;
                }
                beanFromExcelData = createBeanFromExcelData(sheet, 1,channelCode,"1");

                beanFromExcelData.addAll(createBeanFromExcelData(sheet2,3,channelCode,"0"));
            }else if("1".equals(type)){
                //个人
                Sheet sheet = workbook.getSheetAt(0);
                String s = checkExcelTitlePerson(sheet.getRow(0));
                if(!ExceptionConStants.retCode_0000.equals(s)){
                    return s;
                }
                beanFromExcelData = createBeanFromExcelData(sheet,1,channelCode,type);
            }else if("0".equals(type)){
                //机构
                Sheet sheet = workbook.getSheetAt(0);
                String s = checkExcelTitleOrg(sheet.getRow(0));
                if(!ExceptionConStants.retCode_0000.equals(s)){
                    return s;
                }
                beanFromExcelData = createBeanFromExcelData(sheet,3,channelCode,type);
            }
            if(beanFromExcelData.size() == 0){
                return "上传文件中数据为null";
            }
            int i = saveData(beanFromExcelData);
            if(i>0){
                return "success";
            }else{
                return "上传文件保存发生异常";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "上传文件保存发生异常";
        }
    }

    /**
     * @Description 个人文件标题check
     * @Author weijunjie
     * @Date 2020/3/19 18:15
     **/
    public String checkExcelTitlePerson(Row row){
        return checkExcelTitle(row,FxqTitleUtil.getAIPTitleForPersonal());
    }

    /**
     * @Description 机构文件标题check
     * @Author weijunjie
     * @Date 2020/3/19 18:15
     **/
    public String checkExcelTitleOrg(Row row){
        //机构文件标题涉及到了合并单元格  先生成一个机构文件  获取首行cell
        List<Map<String, Object>> jsonObjects = new ArrayList<>();
        LinkedHashMap<String, String> aipTitleForPersonal = FxqTitleUtil.getAIPTitleForOrgan();
        Workbook workbook =PublicCreateExcelUtils.createOrgTemp(aipTitleForPersonal,null,jsonObjects,"机构模板",null);
        Sheet sheetAt = workbook.getSheetAt(0);
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        Row row1 = sheetAt.getRow(0);
        for(int i = 0;i<row1.getLastCellNum();i++){
            sb1.append(row1.getCell(i).getStringCellValue());
        }
        for(int i = 0;i<row.getLastCellNum();i++){
            sb2.append(row.getCell(i).getStringCellValue());
        }
        if(sb1.toString().equals(sb2.toString())){
            return ExceptionConStants.retCode_0000;
        }else{
            return "标题验证未通过，确认文件正确性";
        }

    }


    /**
     * @Description 验证标题的合法性
     * @Author weijunjie
     * @Date 2020/3/19 18:07
     **/
    public String checkExcelTitle(Row row,LinkedHashMap<String,String> title){
        JSONObject newTitleMap = new JSONObject(true);
        int i=0;
        for(String key:title.keySet()){
            newTitleMap.put(key,row.getCell(i).getStringCellValue());
            i++;
        }
        String errorMsg = ExceptionConStants.retCode_0000;
        for(String key:title.keySet()){
            String oldVal = title.get(key)+"";
            String newVal = newTitleMap.getString(key);
            if(!newVal.equals(oldVal)){
                errorMsg = newVal+"!="+oldVal+"文件数据异常";
                break;
            }
        }
        return errorMsg;
    }

    /**
     * @Description 防止下载的数据量过大 或者查询不到数据 引起excel文件生成异常，添加数据量校验
     * @Author weijunjie
     * @Date 2020/3/19 15:39
     **/
    public String checkExcelDateRow(String type,String channelCode,String date){
        String res = "success";
        if(StringUtils.isNotBlank(date)){
            date = date.replaceAll("-","");
        }else{
            date = null;
        }
        if(StringUtils.isBlank(channelCode)){
            channelCode = null;
        }

        //查询数据库符合条件的数据
        if(StringUtils.isNotBlank(type) && "2".equals(type)){
            //双模板查询两中条件下的sql 1 = 个人  0 = 机构
            List<DFxqUnresidentData> perData = fxqUnresidentDataMapper.selectDataByType(channelCode, "1", date);
            List<DFxqUnresidentData> orgData = fxqUnresidentDataMapper.selectDataByType(channelCode, "0", date);
            if((perData.size()+orgData.size()) >=1000){
                res= "条件下查询到的数据量过大，请选择条件获取";
            }
            if((perData.size()+orgData.size()) == 0){
                res= "条件下没有查询到对应的数据";
            }
        }else{
            if("0".equals(type)){
                List<DFxqUnresidentData> orgData = fxqUnresidentDataMapper.selectDataByType(channelCode, "0", date);
                if(orgData.size() >=1000){
                    res= "条件下查询到的数据量过大，请选择条件获取";
                }
                if(orgData.size() == 0){
                    res= "条件下没有查询到对应的数据";
                }
            }else{
                //双模板查询两中条件下的sql 1 = 个人  0 = 机构
                List<DFxqUnresidentData> perData = fxqUnresidentDataMapper.selectDataByType(channelCode, "1", date);
                if(perData.size() >=1000){
                    res= "条件下查询到的数据量过大，请选择条件获取";
                }
                if(perData.size() == 0){
                    res= "条件下没有查询到对应的数据";
                }
            }
        }
        return res;
    }

    /**
     * @Description 根据条件导出对应的数据
     * @Author weijunjie
     * @Date 2020/3/19 15:16
     **/
    public Workbook downloadByType(String type,String channelCode,String date){
        if(StringUtils.isNotBlank(date)){
            date = date.replaceAll("-","");
        }else{
            date = null;
        }
        if(StringUtils.isBlank(channelCode)){
            channelCode = null;
        }
        Workbook workbook = null;
        try {
            //查询数据库符合条件的数据
            if(StringUtils.isNotBlank(type) && "2".equals(type)){
                ArrayList<CreateExcelBean> excelBeans = new ArrayList<>();
                //双模板查询两中条件下的sql 1 = 个人  0 = 机构
                List<DFxqUnresidentData> perData = fxqUnresidentDataMapper.selectDataByType(channelCode, "1", date);
                CreateExcelBean excelDataForPer = createExcelDataForPer(perData);
                excelBeans.add(excelDataForPer);
                List<DFxqUnresidentData> orgData = fxqUnresidentDataMapper.selectDataByType(channelCode, "0", date);
                CreateExcelBean excelDataForOrg = createExcelDataForOrg(orgData);
                excelBeans.add(excelDataForOrg);
                workbook =PublicCreateExcelUtils.createWorkbook(excelBeans);
//                return workbook;
            }else{
                if("0".equals(type)){
                    ArrayList<CreateExcelBean> excelBeans = new ArrayList<>();
                    List<DFxqUnresidentData> orgData = fxqUnresidentDataMapper.selectDataByType(channelCode, "0", date);
                    CreateExcelBean excelDataForOrg = createExcelDataForOrg(orgData);
                    excelBeans.add(excelDataForOrg);
                    workbook = PublicCreateExcelUtils.createWorkbook(excelBeans);
//                    return workbook;
                }else{
                    //双模板查询两中条件下的sql 1 = 个人  0 = 机构
                    ArrayList<CreateExcelBean> excelBeans = new ArrayList<>();
                    List<DFxqUnresidentData> perData = fxqUnresidentDataMapper.selectDataByType(channelCode, "1", date);
                    CreateExcelBean excelDataForPer = createExcelDataForPer(perData);
                    excelBeans.add(excelDataForPer);
                    workbook = PublicCreateExcelUtils.createWorkbook(excelBeans);
//                    return workbook;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //数据库更新对应条件下数据的下载时间
        updateDowloadTime(type,channelCode,date);
        return workbook;
    }

    /**
     * @Description 更新数据的下载时间
     * @Author weijunjie
     * @Date 2020/3/20 13:07
     **/
    public void updateDowloadTime(String type,String channelCode,String date){
        fxqUnresidentDataMapper.updateDowloadTime(channelCode,type,date,DateUtils.formatDate(new Date(),"yyyyMMdd"));
    }

    /**
     * @Description 获取客户信息数据----根据相关数据获取客户信息相关数据  渠道编号 客户证件号 等数据
     * @Author weijunjie
     * @Date 2020/3/20 9:56
     **/
    public void getAccountReqToPerson(DFxqUnresidentData data){
        AccountInfo accountReq = accountInfoMapper.selectAccountInfo(data.getFudChannelCode(),
                data.getFudCertificateNo(),data.getFudCertificateType(), data.getFudCustomerType());
        if(null == accountReq){
            return;
        }
        boolean flag = false;
        DResidentTaxInformReq unResidentInfoRes = residentTaxInformMapper.selectResidentTaxInfo(accountReq.getAiChannelCode(),
                accountReq.getAiIndividualOrInstitution(), accountReq.getAiTransactionAccountId());
        if(null != unResidentInfoRes){
            flag = true;
        }
        //判断数据值信息，替换反洗钱数据信息表
//        if(StringUtils.isBlank(data.getFudAccountid())) data.setFudAccountid(accountReq.getArTargetTransactAcctCode());
        if(StringUtils.isBlank(data.getFudPostOfficeCode()))data.setFudPostOfficeCode(accountReq.getAiPostCode());
        if(StringUtils.isBlank(data.getFudPostOfficeAddress()))data.setFudPostOfficeAddress(accountReq.getAiEmailAddress());
        if(StringUtils.isBlank(data.getFudPhoneNumber()))data.setFudPhoneNumber(accountReq.getAiMobileTelNo());
        if(StringUtils.isBlank(data.getFudTelNumber()))data.setFudTelNumber(accountReq.getAiTelNo());
        //投资人基本信息数据补充
        String dataString = data.getFudPerInfo();
        if(StringUtils.isNotBlanks(dataString)){
            JSONObject jsonIndiInfo = JSONObject.parseObject(dataString);
            //申请信息数据--查询非居民信息表数据
            if(flag){
                jsonGetNotNullValue(jsonIndiInfo,"country",unResidentInfoRes.getRtirLivingCountry());
                jsonGetNotNullValue(jsonIndiInfo,"pi_gender",unResidentInfoRes.getRtirSex());
                jsonGetNotNullValue(jsonIndiInfo,"pi_birthday",unResidentInfoRes.getRtirBirthDate());
                jsonGetNotNullValue(jsonIndiInfo,"pi_indetityAttestation",unResidentInfoRes.getRtirNonResiFlag());
                jsonGetNotNullValue(jsonIndiInfo,"pi_originalAddress",unResidentInfoRes.getRtirLivingAddress());
                jsonGetNotNullValue(jsonIndiInfo,"pi_birthAddress",unResidentInfoRes.getRtirBirthCountry()+unResidentInfoRes.getRtirBirthCity());
                jsonGetNotNullValue(jsonIndiInfo,"pi_taxCountry",unResidentInfoRes.getRtirTaxCountry());
                jsonGetNotNullValue(jsonIndiInfo,"pi_taxAccount",unResidentInfoRes.getRtirTaxId());
                jsonGetNotNullValue(jsonIndiInfo,"pi_taxReason",unResidentInfoRes.getRtirSpecification());
            }
            //更新为null的额外数据信息
            jsonIndiInfo.put("country",getNotNull(jsonIndiInfo.getString("country"),accountReq.getAiNationality()));
            String pi_gender = getNotNull(jsonIndiInfo.getString("pi_gender"), accountReq.getAiSex());
            if(StringUtils.isNotBlank(pi_gender)){
                if(pi_gender.contains("1")){
                    jsonIndiInfo.put("pi_gender","男");
                }else{
                    jsonIndiInfo.put("pi_gender","女");
                }
            }else{
                jsonIndiInfo.put("pi_gender","");
            }
            jsonIndiInfo.put("pi_birthday",getNotNull(jsonIndiInfo.getString("pi_birthday"),accountReq.getAiInvestorsBirthday()));
            jsonIndiInfo.put("pi_originalAddress",getNotNull(jsonIndiInfo.getString("pi_originalAddress"),accountReq.getAiAddress()));
            jsonIndiInfo.put("pi_birthAddress",getNotNull(jsonIndiInfo.getString("pi_birthAddress"),accountReq.getAiAddress()));


            data.setFudPerInfo(JSONObject.toJSONString(jsonIndiInfo));
        }
    }

    /**
     * @Description 传入json对象  解析获取json中key不为null的数据
     * @Author weijunjie
     * @Date 2020/3/27 14:55
     **/
    public void jsonGetNotNullValue(JSONObject jsonObject,String key,String value){
        if(StringUtils.isBlank(jsonObject.getString(key))){
            if(StringUtils.isNotBlank(value)){
                jsonObject.put(key,value);
            }
        }
    }

    /**
     * @Description 获取客户信息数据----根据相关数据获取客户信息相关数据  渠道编号 客户证件号 等数据
     * @Author weijunjie
     * @Date 2020/3/20 9:56
     **/
    public void getAccountReqToOrg(DFxqUnresidentData data){
        AccountInfo accountReq = accountInfoMapper.selectAccountInfo(data.getFudChannelCode(),
                data.getFudCertificateNo(),data.getFudCertificateType(), data.getFudCustomerType());
        if(null == accountReq){
            return;
        }
        boolean flag = false;
        DResidentTaxInformReq unResidentInfoRes = residentTaxInformMapper.selectResidentTaxInfo(accountReq.getAiChannelCode(),
                accountReq.getAiIndividualOrInstitution(), accountReq.getAiTransactionAccountId());
        if(null != unResidentInfoRes){
            flag = true;
        }
        //判断数据值信息，替换反洗钱数据信息表
//        if(StringUtils.isBlank(data.getFudAccountid())) data.setFudAccountid(accountReq.getArTargetTransactAcctCode());
        if(StringUtils.isBlank(data.getFudPostOfficeCode()))data.setFudPostOfficeCode(accountReq.getAiPostCode());
        if(StringUtils.isBlank(data.getFudPostOfficeAddress()))data.setFudPostOfficeAddress(accountReq.getAiEmailAddress());
        if(StringUtils.isBlank(data.getFudPhoneNumber()))data.setFudPhoneNumber(accountReq.getAiMobileTelNo());
        if(StringUtils.isBlank(data.getFudTelNumber()))data.setFudTelNumber(accountReq.getAiTelNo());
        //机构基本信息 数据null值判断
        String fudOrgInfo = data.getFudOrgInfo();
        if(StringUtils.isNotBlanks(fudOrgInfo)){
            JSONObject jsonOrgInfo = JSONObject.parseObject(fudOrgInfo);
            jsonOrgInfo.put("oi_businessScope",getNotNull(jsonOrgInfo.getString("oi_businessScope"),accountReq.getAiInstReprManageRange()));
            jsonOrgInfo.put("industry_type",getNotNull(jsonOrgInfo.getString("work_type"),accountReq.getAiVocation()));
            jsonOrgInfo.put("organ_type",getNotNull(jsonOrgInfo.getString("org_type"),accountReq.getAiInstitutionType()));
            jsonOrgInfo.put("oi_registeredAddress",getNotNull(jsonOrgInfo.getString("oi_registeredAddress"),accountReq.getAiAddress()));
            data.setFudOrgInfo(JSONObject.toJSONString(jsonOrgInfo));
        }
        //法人信息补充
        String fudFrInfo = data.getFudOrgFr();
        if(StringUtils.isNotBlanks(fudFrInfo)){
            JSONObject jsonFrInfo = JSONObject.parseObject(fudFrInfo);
            jsonFrInfo.put("fr_name",getNotNull(jsonFrInfo.getString("fr_name"),accountReq.getAiInstReprName()));
            jsonFrInfo.put("fr_certificateType",getNotNull(jsonFrInfo.getString("fr_certificateType"),accountReq.getAiInstReprIdType()));
            jsonFrInfo.put("fr_certificateNo",getNotNull(jsonFrInfo.getString("fr_certificateNo"),accountReq.getAiInstReprIdCode()));
            jsonFrInfo.put("fr_certificateEndTime",getNotNull(jsonFrInfo.getString("fr_certificateEndTime"),accountReq.getAiInstReprCertValidDate()));
            data.setFudOrgFr(JSONObject.toJSONString(jsonFrInfo));
        }
        //授权经办人
        String fudSqInfo = data.getFudOrgSqjbr();
        if(StringUtils.isNotBlanks(fudSqInfo)){
            JSONObject jsonSqInfo = JSONObject.parseObject(fudSqInfo);
            jsonSqInfo.put("sq_name",getNotNull(jsonSqInfo.getString("sq_name"),accountReq.getAiTransactorName()));
            jsonSqInfo.put("sq_certificateType",getNotNull(jsonSqInfo.getString("sq_certificateType"),accountReq.getAiTransactorCertType()));
            jsonSqInfo.put("sq_certificateNo",getNotNull(jsonSqInfo.getString("sq_certificateNo"),accountReq.getAiTransactorCertNo()));
            jsonSqInfo.put("sq_certificateEndTime",getNotNull(jsonSqInfo.getString("sq_certificateEndTime"),accountReq.getAiInstTranCertValidDate()));
            data.setFudOrgSqjbr(JSONObject.toJSONString(jsonSqInfo));
        }
        //控股股东
        String fudKgInfo = data.getFudOrgKggd();
        if(StringUtils.isNotBlanks(fudKgInfo)){
            JSONObject jsonKgInfo = JSONObject.parseObject(fudKgInfo);
            jsonKgInfo.put("kggd_name",getNotNull(jsonKgInfo.getString("kggd_name"),accountReq.getAiControlHolder()));
            data.setFudOrgKggd(JSONObject.toJSONString(jsonKgInfo));
        }
        //其他机构信息数据
        String fudOtherInfo = data.getFudOrgOther();
        if(StringUtils.isNotBlanks(fudOtherInfo)){
            JSONObject jsonOtherInfo = JSONObject.parseObject(fudOtherInfo);
            if(flag){
                jsonGetNotNullValue(jsonOtherInfo,"org_name_en",unResidentInfoRes.getRtirEnglishName());
                jsonGetNotNullValue(jsonOtherInfo,"org_address_en",unResidentInfoRes.getRtirLivingAddress3());
                jsonGetNotNullValue(jsonOtherInfo,"tax_country",unResidentInfoRes.getRtirTaxCountry());
                jsonGetNotNullValue(jsonOtherInfo,"tax_id",unResidentInfoRes.getRtirTaxId());
                jsonGetNotNullValue(jsonOtherInfo,"reason",unResidentInfoRes.getRtirSpecification());
                jsonGetNotNullValue(jsonOtherInfo,"org_tax_id",unResidentInfoRes.getRtirNonResiFlag());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_name",unResidentInfoRes.getRtirChineseName2());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_id",unResidentInfoRes.getRtirTaxId2());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_tax_country",unResidentInfoRes.getRtirTaxCountry2());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_tax_id",unResidentInfoRes.getRtirConNonResiFlag());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_reason",unResidentInfoRes.getRtirSpecification2());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_family_name",unResidentInfoRes.getRtirEnglishFamliyName3());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_first_name",unResidentInfoRes.getRtirEnglishFirstName3());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_sex",unResidentInfoRes.getRtirSex());
                jsonGetNotNullValue(jsonOtherInfo,"kzr_birthday",unResidentInfoRes.getRtirBirthDate2());
                jsonGetNotNullValue(jsonOtherInfo,"in_address_cn",unResidentInfoRes.getRtirLivingAddress5());
                jsonGetNotNullValue(jsonOtherInfo,"in_address_en",unResidentInfoRes.getRtirLivingAddress7());
                jsonGetNotNullValue(jsonOtherInfo,"birth_address_cn",unResidentInfoRes.getRtirBirthCountry2()+unResidentInfoRes.getRtirBirthCity2());
                jsonGetNotNullValue(jsonOtherInfo,"birth_address_en",unResidentInfoRes.getRtirBirthCouEngName2()+unResidentInfoRes.getRtirBirthCity2());
            }
            //数据为null  做额外数据判断
            String orgType = getNotNull(jsonOtherInfo.getString("ot_tax_country"),accountReq.getAiInstitutionType());
            if(StringUtils.isNotBlank(orgType)){
                jsonOtherInfo.put("org_type",getOrgType(orgType));
            }else{
                jsonOtherInfo.put("org_type","");
            }

            String country = getNotNull(jsonOtherInfo.getString("ot_tax_country"),accountReq.getAiNationality());
            if(StringUtils.isBlank(country)){
                jsonOtherInfo.put("tax_country","中国");
            }else{
                jsonOtherInfo.put("tax_country",country);
            }
            jsonOtherInfo.put("kzr_name",getNotNull(jsonOtherInfo.getString("ot_kzr_name"),accountReq.getAiActualController()));
            //控制人姓名
            jsonOtherInfo.put("controller_first_name",getNotNull(jsonOtherInfo.getString("kzr_family_name"),accountReq.getAiEnglishFirstName()));
            jsonOtherInfo.put("controller_family_name",getNotNull(jsonOtherInfo.getString("kzr_first_name"),accountReq.getAiEnglishFamliyName()));
//            jsonOtherInfo.put("kzr_tax_country",jsonOtherInfo.getString("ot_kzr_tax_country"));
            String kzrCountry = getNotNull(jsonOtherInfo.getString("ot_kzr_tax_country"),accountReq.getAiNationality());
            if(StringUtils.isBlank(kzrCountry)){
                jsonOtherInfo.put("kzr_tax_country","中国");
            }else{
                jsonOtherInfo.put("kzr_tax_country",kzrCountry);
            }
            jsonOtherInfo.put("kzr_sex",getNotNull(jsonOtherInfo.getString("ot_kzr_sex"),accountReq.getAiSex()));
            String sex = getNotNull(jsonOtherInfo.getString("ot_kzr_sex"),accountReq.getAiSex());
            if(StringUtils.isNotBlank(sex)){
                if(sex.contains("1")){
                    jsonOtherInfo.put("kzr_sex","男");
                }else{
                    jsonOtherInfo.put("kzr_sex","女");
                }
            }else{
                jsonOtherInfo.put("kzr_sex","");
            }
            jsonOtherInfo.put("kzr_birthday",getNotNull(jsonOtherInfo.getString("ot_kzr_birthday"),accountReq.getAiInvestorsBirthday()));
            //控制人数据信息

            data.setFudOrgOther(JSONObject.toJSONString(jsonOtherInfo));
        }

    }



    /**
     * @Description 传入两个数值  判断取一个不为null的数值
     * @Author weijunjie
     * @Date 2020/3/23 15:32
     **/
    public String getNotNull(String s1,String s2){
        if(StringUtils.isBlank(s2)){
            return s1;
        }else{
            if(StringUtils.isBlank(s1)){
                return s2;
            }else{
                return s1;
            }
        }
    }


    /**
     * @Description 通过sheet数据以及首行数据下标，解析并组装对象获取sheet的数据
     * @Author weijunjie
     * @Date 2020/3/18 9:09
     **/
    public List<DFxqUnresidentData> createBeanFromExcelData(Sheet sheet,int firstRow,String channelCode,String type){
        //查询获取渠道名
        ChannelInfoMapper channelInfoMapper = SpringUtils.getBean(ChannelInfoMapper.class);
        ChannelInfo channelInfo = channelInfoMapper.selectChannelInfo(channelCode);
        String channelName = "";
        if(null != channelInfo){
            channelName = channelInfo.getCiChannelName();
        }
        JSONObject titleJson = makeMapToJSON(FxqTitleUtil.getAIPTitleForPersonal());
        if("0".equals(type)){
            titleJson = makeMapToJSON(FxqTitleUtil.getAIPTitleForOrgan());
        }
        int lastRowNum = sheet.getLastRowNum();
        List<DFxqUnresidentData> jsonObjects = new ArrayList<>();
        for(int i = firstRow;i<=lastRowNum;i++){
            //每次重新创建一个json
            JSONObject oneDataJSON = titleJson;
            Row row = sheet.getRow(i);
            if(null != row){
                int j = 0;
                for(String key:titleJson.keySet()){
                    //强制读取String数据
                    Cell cell = row.getCell(j);
                    if(null != cell){
                        //先做数据格式类型判断
                        int cellType = cell.getCellType();
                        if(cellType == Cell.CELL_TYPE_NUMERIC){
                            String excelTime = getExcelTime(cell);
                            oneDataJSON.put(key,excelTime);
                        }else{
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValueToString = cell.getStringCellValue();
                            //清空该键对应的值
                            oneDataJSON.put(key,cellValueToString);
                        }

                    }else{
                        oneDataJSON.put(key,"");
                    }
                    j++;
                }
            }else{
                break;
            }
            //解析组转数据库需要的数据data
            DFxqUnresidentData dataByJSON = getDataByJSON(oneDataJSON, type,channelCode,channelName);
            jsonObjects.add(dataByJSON);
        }
        return jsonObjects;
    }

    /**
     * @Description 组装个人导出到crm文件数据
     * @Author weijunjie
     * @Date 2020/3/19 10:08
     **/
    public CreateExcelBean createExcelDataForPer(List<DFxqUnresidentData> dataList){
        CreateExcelBean excelBeanSheet1 = new CreateExcelBean();
        List<Map<String,Object>> sheetDateList1 = new ArrayList<>();
        for(DFxqUnresidentData data:dataList){
            Map<String,Object> sheetData1 = new HashMap<>();
            //获取个人信息解析json数据
            String indiInfo = data.getFudPerInfo();
            //组装数据查询对象 查询账户申请表对应的数据信息
            //组装文件中对应的数据
            sheetData1.put("export_date",DateUtils.formatDate(new Date(),"yyyyMMdd"));
            sheetData1.put("financi_account",data.getFudAccountid());
            sheetData1.put("insure_date",data.getFudShareCfmDate());
            sheetData1.put("insure_amount",data.getFudShareCfm());
            sheetData1.put("empty_code",data.getFudEmptyCode());
            sheetData1.put("amount",data.getFudPaymentAmount());
            sheetData1.put("channel_name",data.getFudChannelName());
            sheetData1.put("product_code",data.getFudProductCode());
            sheetData1.put("product_name",data.getFudProductName());
            sheetData1.put("customer_name",data.getFudCustomerName());
            sheetData1.put("customer_type",customerTypeToCN(data.getFudCustomerType()));
            sheetData1.put("certificate_type",certTypeToCN(data.getFudCertificateType()));
            sheetData1.put("certificate_no",data.getFudCertificateNo());
            sheetData1.put("post_office_code",data.getFudPostOfficeCode());
            sheetData1.put("post_office_address",data.getFudPostOfficeAddress());
            sheetData1.put("tel_number",data.getFudTelNumber());
            sheetData1.put("phone_number",data.getFudPhoneNumber());
            sheetData1.put("benefit_level",data.getFudBenefitLevel());
            sheetData1.put("payment_date",data.getFudPaymentDate());
            sheetData1.put("certificate_start_time",data.getFudCertificateStart());
            sheetData1.put("certificate_end_time",data.getFudCertificateEnd());
            sheetData1.put("capital_source",data.getFudCapitalSource());
            //个人数据信息解析
            makePerInfoForExcel(sheetData1,indiInfo);
            sheetDateList1.add(sheetData1);
        }
        excelBeanSheet1.setSheetName("个人信息");
        excelBeanSheet1.setDataList(sheetDateList1);
        excelBeanSheet1.setSheetTitle(FxqTitleUtil.getTitleToCrmPersion());

        return excelBeanSheet1;
    }

    /**
     * @Description 组装机构导出到crm文件数据
     * @Author weijunjie
     * @Date 2020/3/19 10:08
     **/
    public CreateExcelBean createExcelDataForOrg(List<DFxqUnresidentData> dataList){
        CreateExcelBean excelBeanSheet1 = new CreateExcelBean();
        List<Map<String,Object>> sheetDateList1 = new ArrayList<>();
        for(DFxqUnresidentData data:dataList){
            Map<String,Object> sheetData1 = new HashMap<>();
            //通用字段
            sheetData1.put("export_date",DateUtils.formatDate(new Date(),"yyyyMMdd"));
            sheetData1.put("inst_certificate_no",data.getFudCertificateNo());
            sheetData1.put("channel_name",data.getFudChannelName());
            sheetData1.put("post_office_code",data.getFudPostOfficeCode());
            sheetData1.put("post_office_address",data.getFudPostOfficeAddress());
            sheetData1.put("phone_number",data.getFudPhoneNumber());
            sheetData1.put("tel_number",data.getFudTelNumber());
            sheetData1.put("capital_source",data.getFudCapitalSource());
            //机构基本信息解析组装
            String orgInfo = data.getFudOrgInfo();
            makeOrgInfoForExcel(sheetData1,orgInfo);
            //法人相关信息数据组装
            makeOrgFrForExcel(sheetData1,data);
            //机构其他数据信息组装
            makeOrgOtherForExcel(sheetData1,data);

            sheetData1.put("customer_type",orgTypeToCN(data.getFudOrgCustomerType()));
            sheetData1.put("nature_type",orgTypeToCN(data.getFudOrgType()));
            //根据类型组装数据
            makeOrgTypeInfoForExcel(sheetData1,data);
            sheetDateList1.add(sheetData1);
        }
        excelBeanSheet1.setSheetName("机构信息");
        excelBeanSheet1.setDataList(sheetDateList1);
        excelBeanSheet1.setSheetTitle(FxqTitleUtil.getTitleToCrmOrg());

        return excelBeanSheet1;
    }

    /**
     * @Description 反洗钱非居民信息保存至数据库
     * @Author weijunjie
     * @Date 2020/2/5 16:39
     **/
    public int saveData(List<DFxqUnresidentData> dFxqUnresidentDataS){
        List<List<DFxqUnresidentData>> lists = subBeanList( 50,dFxqUnresidentDataS);
        int newCount = 0;
        for(List<DFxqUnresidentData> list:lists){
            newCount +=fxqUnresidentDataMapper.insertByBatch(list);
        }
        return newCount;
    }

    /**
     * @Description 分段批量插入数据库数据  分段方法
     * @Author weijunjie
     * @Date 2020/1/7 15:37
     **/
    public List<List<DFxqUnresidentData>> subBeanList(Integer max, List<DFxqUnresidentData> beanList){
        List<List<DFxqUnresidentData>> resList = new ArrayList<>();
        max = max == null?50:max;
        if(beanList.size() <= max){
            resList.add(beanList);
            return resList;
        }else{
            int out = beanList.size()%max;
            int z = beanList.size()/max;
            for(int i=0;i<=z;i++){
                if(i==z){
                    resList.add(beanList.subList(i*max,i*max+out));
                }else{
                    resList.add(beanList.subList(i*max,(i+1)*max));
                }
            }
        }
        return resList;
    }

    /**
     * @Description 根据产品类型  判断组装对应的数据
     * @Author weijunjie
     * @Date 2020/3/19 14:44
     **/
    public void makeOrgTypeInfoForExcel(Map<String,Object> data,DFxqUnresidentData fxq){
        String orgType = fxq.getFudOrgCustomerType();
        if(StringUtils.isBlank(orgType)){
            return;
        }
        //判断解析那个json数据
        String theJSON = "";
        switch (orgType){
            case "1":theJSON = fxq.getFudOrgType1Info(); break;
            case "2":theJSON = fxq.getFudOrgType2Info(); break;
            case "3":theJSON = fxq.getFudOrgType3Info(); break;
            case "4":theJSON = fxq.getFudOrgType4Info(); break;
            case "5":theJSON = fxq.getFudOrgType5Info(); break;
            case "6":theJSON = fxq.getFudOrgType6Info(); break;
            default:return;
        }
        String t = orgType;
        JSONObject jsonObject = JSONObject.parseObject(theJSON);
        data.put("beneficiary_name",jsonObject.getString("name"+t));
        data.put("beneficiary_adress",jsonObject.getString("address"+t));
        data.put("beneficiary_id_type",certTypeToCN(jsonObject.getString("certificate_type"+t)));
        data.put("certificate_no",jsonObject.getString("certificate_id"+t));
        data.put("bc_certificateStartTime",jsonObject.getString("certificate_start"+t));
        data.put("bc_certificateEndTime",jsonObject.getString("certificate_end"+t));
        data.put("bc_reason",jsonObject.getString("judge_reason"+t));
        data.put("bc_reason_file",jsonObject.getString("judge_file"+t));
        data.put("bc_reason_type",jsonObject.getString("judge_type"+t));
        if("1".equals(orgType)){
            data.put("bc_ShareRatio",jsonObject.getString("shares_scale"+t));
            data.put("bc_ShareType1",jsonObject.getString("dircet_type"+t));
            data.put("bc_ShareType2",jsonObject.getString("shares_type"+t));
            data.put("dsh_user_list",jsonObject.getString("dsh_names"+t));
            data.put("gg_user_list",jsonObject.getString("gg_names"+t));
            data.put("ois_user_list",jsonObject.getString("gd_names"+t));
        }else if("2".equals(orgType) || "4".equals(orgType)){
            data.put("bc_ShareRatio",jsonObject.getString("scale"+t));
        }else if("3".equals(orgType)){
            data.put("info",jsonObject.getString("info"+t));
        }
    }

    /**
     * @Description 获取组装导出机构其他数据信息
     * @Author weijunjie
     * @Date 2020/3/19 14:44
     **/
    public void makeOrgOtherForExcel(Map<String,Object> data,DFxqUnresidentData fxq){
        String otherInfo = fxq.getFudOrgOther();
        if(StringUtils.isBlank(otherInfo)){
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(otherInfo);
        data.put("organ_type2",jsonObject.getString("org_type"));
        data.put("organ_tax_statement",jsonObject.getString("org_tax_id"));
        data.put("organ_name_EN",jsonObject.getString("org_name_en"));
        data.put("organ_address_EN",jsonObject.getString("org_address_en"));
        data.put("organ_tax_country",jsonObject.getString("tax_country"));
        data.put("organ_tax_id",jsonObject.getString("tax_id"));
        data.put("organ_tax_id_reason",jsonObject.getString("reason"));
        data.put("controller_name",jsonObject.getString("kzr_name"));
        data.put("controller_flag",jsonObject.getString("kzr_id"));
        data.put("controller_tax_country",jsonObject.getString("kzr_tax_country"));
        data.put("controller_first_name",jsonObject.getString("kzr_family_name"));
        data.put("controller_family_name",jsonObject.getString("kzr_first_name"));
        data.put("controller_gender",jsonObject.getString("kzr_sex"));
        data.put("controller_birthday",jsonObject.getString("kzr_birthday"));
        data.put("in_address_CN",jsonObject.getString("in_address_cn"));
        data.put("in_address_EN",jsonObject.getString("in_address_en"));
        data.put("birth_address_CN",jsonObject.getString("birth_address_cn"));
        data.put("birth_address_EN",jsonObject.getString("birth_address_en"));
        data.put("controller_tax_id",jsonObject.getString("kzr_tax_id"));
        data.put("controller_tax_id_reason",jsonObject.getString("kzr_reason"));
    }

    /**
     * @Description 获取解析组装法人 负责人 等相关数据信息
     *   fr 法人  sq 授权经办人  kggd 控制股东  fzr 负责人
     * @Author weijunjie
     * @Date 2020/3/18 10:40
     **/
    public void makeOrgFrForExcel(Map<String,Object> data,DFxqUnresidentData fxq){
        String types = "fr,sq,kggd,fzr";
        String[] split = types.split(",");
        for(String t:split){
            String theJson = "";
            if(t.equals("fr")){
                theJson = fxq.getFudOrgFr();
            }else if(t.equals("sq")){
                theJson = fxq.getFudOrgSqjbr();
            }else if(t.equals("kggd")){
                theJson = fxq.getFudOrgKggd();
            }else{
                //负责人
                theJson = fxq.getFudOrgFzr();
            }
            if(StringUtils.isBlank(theJson)){
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(theJson);
            data.put(t+"_name",jsonObject.getString(t+"_name"));
            data.put(t+"_certificate_type",certTypeToCN(jsonObject.getString(t+"_certificateType")));
            data.put(t+"_certificate_no",jsonObject.getString(t+"_certificateNo"));
            data.put(t+"_certificateStartTime",jsonObject.getString(t+"_certificateStartTime"));
            data.put(t+"_certificateEndTime",jsonObject.getString(t+"_certificateEndTime"));
            data.put(t+"_contactsTelNumber",jsonObject.getString(t+"_contactsTelNumber"));
        }
    }

    /**
     * @Description 解析组装导出excel需要的机构基本信息数据
     * @Author weijunjie
     * @Date 2020/3/19 14:21
     **/
    public void makeOrgInfoForExcel(Map<String,Object> data,String dataString){
        if(StringUtils.isNotBlanks(dataString)){
            JSONObject jsonIndiInfo = JSONObject.parseObject(dataString);
            data.put("oi_businessScope",jsonIndiInfo.getString("oi_businessScope"));
            data.put("industry_type",jsonIndiInfo.getString("work_type"));
            data.put("organ_type",jsonIndiInfo.getString("org_type"));
            data.put("oi_registeredCurrency",jsonIndiInfo.getString("oi_registeredCurrency"));
            data.put("oi_registeredAmount",jsonIndiInfo.getString("oi_registeredAmount"));
            data.put("oi_registeredAddress",jsonIndiInfo.getString("oi_registeredAddress"));
        }

    }

    /**
     * @Description 解析个人基本信息 导出excel需要的数据
     * @Author weijunjie
     * @Date 2020/3/19 10:14
     **/
    public void makePerInfoForExcel(Map<String,Object> data,String dataString){
        if(StringUtils.isNotBlanks(dataString)){
            JSONObject jsonIndiInfo = JSONObject.parseObject(dataString);
            data.put("country",jsonIndiInfo.getString("country"));
            data.put("pi_vocation",jsonIndiInfo.getString("pi_vocation"));
            data.put("pi_gender",jsonIndiInfo.getString("pi_gender"));
            data.put("pi_birthday",jsonIndiInfo.getString("pi_birthday"));
            data.put("pi_indetityAttestation",personContryId(jsonIndiInfo.getString("pi_indetityAttestation")));
            data.put("pi_originalAddress",jsonIndiInfo.getString("pi_originalAddress"));
            data.put("pi_birthAddress",jsonIndiInfo.getString("pi_birthAddress"));
            //税收据民国  默认为中国
            data.put("pi_taxCountry",StringUtils.isBlank(jsonIndiInfo.getString("pi_taxCountry"))?"中国":jsonIndiInfo.getString("pi_taxCountry"));
            data.put("pi_taxAccount",jsonIndiInfo.getString("pi_taxAccount"));
            data.put("pi_taxReason",jsonIndiInfo.getString("pi_taxReason"));
        }

    }

    /**
     * @Description 解析组装个人反洗钱非居民信息
     * @Author weijunjie
     * @Date 2020/3/18 9:35
     **/
    public DFxqUnresidentData getDataByJSON(JSONObject jsonObject,String type,String channelCode,String channelName){
        DFxqUnresidentData bean = new DFxqUnresidentData();
        //个人机构公用数据字段
        bean.setFudAccountid(jsonObject.getString("accountId"));
        bean.setFudChannelCode(channelCode);
        bean.setFudChannelName(channelName);
        bean.setFudProductCode(jsonObject.getString("product_code"));
        bean.setFudShareCfmDate(jsonObject.getString("share_cfm_date"));
        bean.setFudShareCfm(jsonObject.getString("share_cfm"));
        bean.setFudEmptyCode(jsonObject.getString("empty_code"));
        bean.setFudProductName(jsonObject.getString("product_name"));
        bean.setFudCustomerName(jsonObject.getString("customer_name"));
        //客户类型（1个人 0机构）
        bean.setFudCustomerType(typesFormat(jsonObject.getString("customer_type")));
        bean.setFudCertificateType(typesFormat(jsonObject.getString("certificate_type")));
        bean.setFudCertificateNo(jsonObject.getString("certificate_no"));
        bean.setFudPostOfficeCode(jsonObject.getString("post_office_code"));
        bean.setFudPostOfficeAddress(jsonObject.getString("post_office_address"));
        bean.setFudTelNumber(jsonObject.getString("tel_number"));
        bean.setFudPhoneNumber(jsonObject.getString("phone_number"));
        bean.setFudPaymentAmount(jsonObject.getString("payment_amount"));
        bean.setFudBenefitLevel(jsonObject.getString("benefit_level"));
        bean.setFudPaymentDate(jsonObject.getString("payment_date"));
        bean.setFudCertificateStart(jsonObject.getString("certificate_start_time"));
        bean.setFudCertificateEnd(jsonObject.getString("certificate_end_time"));
        bean.setFudCapitalSource(jsonObject.getString("capital_source"));
        if("1".equals(type)){
            //个人信息字段
            bean.setFudPerInfo(makeFudPerInfo(jsonObject));
            //读取个人申请表数据  补充相关缺失字段数据
            getAccountReqToPerson(bean);
        }else{
            //机构 字段
            bean.setFudOrgAddress(jsonObject.getString("contacts_address"));
            bean.setFudOrgPhone(jsonObject.getString("contacts_tel_number"));
            //到账日期
            bean.setFudOrgPayDate(jsonObject.getString("arrival_account_date"));
            //客户类型
            bean.setFudOrgCustomerType(typesFormat2(jsonObject.getString("fud_org_customer_type")));
            bean.setFudOrgType(typesFormat2(jsonObject.getString("fud_org_type")));

            //机构基本信息
            bean.setFudOrgInfo(makeFudOrgInfo(jsonObject));
            //法人信息
            bean.setFudOrgFr(makeOrgInfoByType(jsonObject,"fr"));
            //授权经办人
            bean.setFudOrgSqjbr(makeOrgInfoByType(jsonObject,"sq"));
            //控制股东数据
            bean.setFudOrgKggd(makeOrgInfoByType(jsonObject,"kggd"));
            //负责人数据
            bean.setFudOrgFzr(makeOrgInfoByType(jsonObject,"fzr"));
            //机构其他数据信息
            bean.setFudOrgOther(makeOrgOtherInfo(jsonObject));
            bean.setFudOrgType1Info(makeOrgTypeInfo(jsonObject,"1"));
            bean.setFudOrgType2Info(makeOrgTypeInfo(jsonObject,"2"));
            bean.setFudOrgType3Info(makeOrgTypeInfo(jsonObject,"3"));
            bean.setFudOrgType4Info(makeOrgTypeInfo(jsonObject,"4"));
            bean.setFudOrgType5Info(makeOrgTypeInfo(jsonObject,"5"));
            bean.setFudOrgType6Info(makeOrgTypeInfo(jsonObject,"6"));
            getAccountReqToOrg(bean);
        }
        //数据库应用标记字段
        bean.setFudStatus("1");
        bean.setFudValidFlag("1");
        bean.setFudUploadTime(DateUtils.formatDate(new Date(),"yyyyMMdd"));

        return bean;
    }

    /**
     * @Description 根据后缀名获取对应的机构客户类型需要填写的数据信息
     * @Author weijunjie
     * @Date 2020/3/18 10:58
     **/
    public String makeOrgTypeInfo(JSONObject jsonObject,String t){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name"+t,jsonObject.getString("name"+t));
        jsonObj.put("address"+t,jsonObject.getString("address"+t));
        jsonObj.put("certificate_type"+t,typesFormat(jsonObject.getString("certificate_type"+t)));
        jsonObj.put("certificate_id"+t,jsonObject.getString("certificate_id"+t));
        jsonObj.put("certificate_start"+t,jsonObject.getString("certificate_start"+t));
        jsonObj.put("certificate_end"+t,jsonObject.getString("certificate_end"+t));
        jsonObj.put("judge_reason"+t,jsonObject.getString("judge_reason"+t));
        jsonObj.put("judge_file"+t,jsonObject.getString("judge_file"+t));
        jsonObj.put("judge_type"+t,jsonObject.getString("judge_type"+t));
        if("1".equals(t)){
            jsonObj.put("shares_scale"+t,jsonObject.getString("shares_scale"+t));
            jsonObj.put("dircet_type"+t,jsonObject.getString("dircet_type"+t));
            jsonObj.put("shares_type"+t,jsonObject.getString("shares_type"+t));
            jsonObj.put("dsh_names"+t,jsonObject.getString("dsh_names"+t));
            jsonObj.put("gg_names"+t,jsonObject.getString("gg_names"+t));
            jsonObj.put("gd_names"+t,jsonObject.getString("gd_names"+t));
            jsonObj.put("gd_shares_count"+t,jsonObject.getString("gd_shares_count"+t));
            jsonObj.put("gd_shares_type"+t,jsonObject.getString("gd_shares_type"+t));
        }else if("2".equals(t) || "4".equals(t)){
            jsonObj.put("scale"+t,jsonObject.getString("scale"+t));
        }else if("3".equals(t)){
            jsonObj.put("info"+t,jsonObject.getString("info"+t));
        }
        return jsonObj.toJSONString();

    }

    /**
     * @Description 组装机构其他信息数据
     * @Author weijunjie
     * @Date 2020/3/18 10:44
     **/
    public String makeOrgOtherInfo(JSONObject jsonObject){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("org_type",jsonObject.getString("ot_org_type"));
        jsonObj.put("org_tax_id",jsonObject.getString("ot_org_tax_id"));
        jsonObj.put("org_name_en",jsonObject.getString("ot_org_name_en"));
        jsonObj.put("org_address_en",jsonObject.getString("ot_org_address_en"));
        jsonObj.put("tax_country",jsonObject.getString("ot_tax_country"));
        jsonObj.put("tax_id",jsonObject.getString("ot_tax_id"));
        jsonObj.put("reason",jsonObject.getString("ot_reason"));
        jsonObj.put("kzr_name",jsonObject.getString("ot_kzr_name"));
        jsonObj.put("kzr_id",jsonObject.getString("ot_kzr_id"));
        jsonObj.put("kzr_tax_country",jsonObject.getString("ot_kzr_tax_country"));
        jsonObj.put("kzr_family_name",jsonObject.getString("ot_kzr_family_name"));
        jsonObj.put("kzr_first_name",jsonObject.getString("ot_kzr_first_name"));
        jsonObj.put("kzr_sex",jsonObject.getString("ot_kzr_sex"));
        jsonObj.put("kzr_birthday",jsonObject.getString("ot_kzr_birthday"));
        jsonObj.put("in_address_cn",jsonObject.getString("ot_in_address_cn"));
        jsonObj.put("in_address_en",jsonObject.getString("ot_in_address_en"));
        jsonObj.put("birth_address_cn",jsonObject.getString("ot_birth_address_cn"));
        jsonObj.put("birth_address_en",jsonObject.getString("ot_birth_address_en"));
        jsonObj.put("kzr_tax_id",jsonObject.getString("ot_kzr_tax_id"));
        jsonObj.put("kzr_reason",jsonObject.getString("ot_kzr_reason"));
        return jsonObj.toJSONString();
    }

    /**
     * @Description 根据类型前缀获取组装法人 负责人 等相关数据信息
     *   fr 法人  sq 授权经办人  kggd 控制股东  fzr 负责人
     * @Author weijunjie
     * @Date 2020/3/18 10:40
     **/
    public String makeOrgInfoByType(JSONObject jsonObject,String t){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(t+"_name",jsonObject.getString(t+"_name"));
        jsonObj.put(t+"_certificateType",jsonObject.getString(t+"_certificateType"));
        jsonObj.put(t+"_certificateNo",jsonObject.getString(t+"_certificateNo"));
        jsonObj.put(t+"_certificateStartTime",jsonObject.getString(t+"_certificateStartTime"));
        jsonObj.put(t+"_certificateEndTime",jsonObject.getString(t+"_certificateEndTime"));
        jsonObj.put(t+"_contactsTelNumber",jsonObject.getString(t+"_contactsTelNumber"));
        return jsonObj.toJSONString();
    }

    /**
     * @Description 组装结构基本信息
     * @Author weijunjie
     * @Date 2020/3/18 10:12
     **/
    public String makeFudOrgInfo(JSONObject jsonObject) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("oi_businessScope", jsonObject.getString("oi_businessScope"));
        jsonObj.put("work_type", jsonObject.getString("work_type"));
        jsonObj.put("org_type", jsonObject.getString("org_type"));
        jsonObj.put("oi_registeredCurrency", jsonObject.getString("oi_registeredCurrency"));
        jsonObj.put("oi_registeredAmount", jsonObject.getString("oi_registeredAmount"));
        jsonObj.put("oi_registeredAddress", jsonObject.getString("oi_registeredAddress"));
        return jsonObj.toJSONString();
    }

    /**
     * @Description 组装个人fxq数据信息数据
     * @Author weijunjie
     * @Date 2020/3/18 10:12
     **/
    public String makeFudPerInfo(JSONObject jsonObject){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("country",jsonObject.getString("country"));
        jsonObj.put("pi_vocation",jsonObject.getString("pi_vocation"));
        jsonObj.put("pi_gender",jsonObject.getString("pi_gender"));
        jsonObj.put("pi_birthday",jsonObject.getString("pi_birthday"));
        jsonObj.put("pi_indetityAttestation",jsonObject.getString("pi_indetityAttestation"));
        jsonObj.put("pi_originalAddress",jsonObject.getString("pi_originalAddress"));
        jsonObj.put("pi_birthAddress",jsonObject.getString("pi_birthAddress"));
        jsonObj.put("pi_taxCountry",jsonObject.getString("pi_taxCountry"));
        jsonObj.put("pi_taxAccount",jsonObject.getString("pi_taxAccount"));
        jsonObj.put("pi_taxReason",jsonObject.getString("pi_taxReason"));
        return jsonObj.toJSONString();
    }

    /**
     * @Description linkedHashMap 转 有序json
     * @Author weijunjie
     * @Date 2020/3/18 9:13
     **/
    public JSONObject makeMapToJSON(LinkedHashMap<String,String> jsonO){
//        LinkedHashMap<String,String> jsonO = FxqTitleUtil.getAIPTitleForOrgan();
        JSONObject jsonObject = new JSONObject(true);
        for(String key:jsonO.keySet()){
            jsonObject.put(key,jsonO.get(key));
        }
        return jsonObject;
    }

    /**
     * @Description 客户类型转中文
     * @Author weijunjie
     * @Date 2020/3/23 11:08
     **/
    public String customerTypeToCN(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        switch (type){
            case "0":res = "机构";break;
            case "1":res = "个人";break;
        }
        return res;
    }

    /**
     * @Description 证件类型转中文
     * @Author weijunjie
     * @Date 2020/3/23 11:08
     **/
    public String certTypeToCN(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        switch (type){
            case "0":res = "身份证";break;
            case "1":res = "中国护照";break;
            case "2":res = "军官证";break;
            case "6":res = "外籍护照";break;
            case "7":res = "其他";break;
            case "A":res = "港澳通行证";break;
            case "B":res = "居住证";break;
            case "D":res = "台湾居民来往大陆通行证";break;
        }
        return res;
    }

    /**
     * @Description 机构证件类型中文转换
     * @Author weijunjie
     * @Date 2020/3/23 16:22
     **/
    public String orgCertTypeToCN(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        switch (type){
            case "I":res = "统一社会信用代码";break;
            case "0":res = "技术监督局代码";break;
            case "1":res = "营业执照";break;
            case "2":res = "行政机关";break;
            case "3":res = "社会团体";break;
            case "4":res = "军队";break;
            case "5":res = "武警";break;
            case "6":res = "下属机构";break;
            case "7":res = "基金会";break;
            case "8":res = "其他机构";break;
            case "9":res = "事业单位法人证书";break;
            case "B":res = "组织机构代码证";break;
        }
        return res;

    }

    /**
     * @Description 居民身份识别类型转中文
     * @Author weijunjie
     * @Date 2020/3/23 11:08
     **/
    public String personContryId(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        switch (type){
            case "1":res = "仅为中国税收居民";break;
            case "2":res = "仅为非居民";break;
            case "3":res = "既是中国税收居民又是其他国家（地区）税收居民";break;
        }
        return res;
    }

    /**
     * @Description 导出crmexcel中时间格式转换
     * @Author weijunjie
     * @Date 2020/3/23 11:28
     **/
    public String excelTimeFormat(String time){
        if(time.contains("-")){
            time = time.replaceAll(time,"-");
        }
        if(time.contains("/")){
            time = time.replaceAll(time,"/");
        }
        if("长期".equals(time)){
            return "长期";
        }
        return time;

    }

    /**
     * @Description 格式化类型（传进来的字段进行类型字段匹配，匹配到关键字返回关键字）
     *      传入数据中只要有该类型的标记，返回标记
     * @Author weijunjie
     * @Date 2020/3/23 11:59
     **/
    public String typesFormat(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        if(StringUtils.isNotBlank(type)){
            //初始化赋值  防止数据库出现超长字段
            res = type.substring(0,1);
            //对当前传入的字段值进行判断  客户类型：0 机构  1 个人
            if(type.contains("1")) return"1";
            if(type.contains("0")) return"0";
            //证件类型判断  0:身份证
            //1:中国护照
            //2:军官证
            //6:外籍护照
            //7:其他
            //A:港澳通行证
            //B：居住证
            //D：台湾居民来往大陆通行证
            if(type.contains("2")) return "2";
            if(type.contains("6")) return "6";
            if(type.contains("7")) return "7";
            if(type.contains("A")) return "A";
            if(type.contains("B")) return "B";
            if(type.contains("D")) return "D";
            //I:统一社会信用代码
            //0:技术监督局代码
            //1:营业执照
            //2:行政机关
            //3:社会团体
            //4:军队
            //5:武警
            //6:下属机构
            //7:基金会
            //8:其他机构
            //9:事业单位法人证书
            //B:组织机构代码证
            if(type.contains("I")) return "I";
            if(type.contains("3")) return "3";
            if(type.contains("4")) return "4";
            if(type.contains("5")) return "5";
            if(type.contains("8")) return "8";
            if(type.contains("9")) return "9";
            if(type.contains("B")) return "B";
        }
        return res;
    }

    /**
     * @Description 类型格式转换  中文转阿拉伯数字
     * @Author weijunjie
     * @Date 2020/3/23 16:29
     **/
    public String typesFormat2(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        if(StringUtils.isNotBlank(type)){
            res = res.substring(0,1);
            if(type.contains("一")) return "I";
            if(type.contains("二")) return "3";
            if(type.contains("三")) return "4";
            if(type.contains("四")) return "5";
            if(type.contains("五")) return "8";
            if(type.contains("六")) return "9";
        }
        return res;
    }

    /**
     * @Description 类型格式转换  中文转阿拉伯数字
     * @Author weijunjie
     * @Date 2020/3/23 16:29
     **/
    public String orgTypeToCN(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        String res = type;
        switch (type){
            case "1":res = "股份有限公司、有限责任公司（非国有控股企业）";break;
            case "2":res = "合伙企业";break;
            case "3":res = "信托";break;
            case "4":res = "基金";break;
            case "5":res = "非政府控制的企事业单位";break;
            case "6":res = "个体工商户、个人独资企业、不具备法人资格的专业服务机构；农林渔牧产业的非公司制农民专业合作组织；政府控制的企事业单位";break;
        }
        return res;
    }

    /**
     * @Description excel中时间格式数据读取为字符串格式
     * @Author weijunjie
     * @Date 2020/3/23 11:28
     **/
    public String getExcelTime(Cell cell){
        if(HSSFDateUtil.isCellDateFormatted(cell)){
            Date theDate = cell.getDateCellValue();
            String yyyyMMdd = DateUtils.formatDate(theDate, "yyyyMMdd");
            return yyyyMMdd;
        }else{
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        }
    }

    /**
     * @Description 获取机构类型  中文
     * @Author weijunjie
     * @Date 2020/3/26 16:25
     **/
    public String getOrgType(String type){
        String res = type;
        switch (type){
            case "0":res = "保险公司";break;
            case "1":res = "基金管理公司";break;
            case "3":res = "信托公司";break;
            case "4":res = "证券公司";break;
            case "8":res = "其他";break;
            case "9":res = "银行";break;
            case "A":res = "私募基金管理人";break;
            case "B":res = "期货公司";break;
            case "C":res = "基金管理公司子公司";break;
            case "D":res = "证券公司子公司";break;
            case "E":res = "期货公司子公司";break;
            case "F":res = "财务公司";break;
            case "G":res = "其他境内金融机构";break;
            case "H":res = "机关法人";break;
            case "I":res = "事业单位法人";break;
            case "J":res = "社会团体法人";break;
            case "K":res = "非金融机构企业法人";break;
            case "L":res = "非金融类非法人机构";break;
            case "M":res = "境外代理人";break;
            case "N":res = "境外金融机构";break;
            case "P":res = "外国战略投资者";break;
            case "Q":res = "境外非金融机构";break;
        }
        return res;
    }

}
