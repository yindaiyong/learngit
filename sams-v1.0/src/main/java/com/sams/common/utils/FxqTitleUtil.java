package com.sams.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

/**
 * @ClassName 反洗钱excel文件对应的标题title数据
 * 描述 :
 * @Author weijunjie
 * @Date 2020/3/17 14:10
 */
public class FxqTitleUtil {

    /**
     * @Description 导出crm机构数据 标题模板
     * @Author weijunjie
     * @Date 2020/3/19 8:47
     **/
    public static LinkedHashMap<String,String> getTitleToCrmOrg(){
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("export_date","日期");
        hashMap.put("inst_certificate_no","证件号码");
        hashMap.put("channel_name","渠道名称");
        hashMap.put("post_office_code","邮政编码");
        hashMap.put("post_office_address","通讯地址");
        hashMap.put("phone_number","手机号码");
        hashMap.put("tel_number","电话号码");
        hashMap.put("capital_source","机构认购资金来源");
        hashMap.put("oi_businessScope","经营范围");
        hashMap.put("industry_type","行业类型");
        hashMap.put("organ_type","机构类型");
        hashMap.put("oi_registeredCurrency","注册币种");
        hashMap.put("oi_registeredAmount","注册资金");
        hashMap.put("oi_registeredAddress","注册地址");
        hashMap.put("fr_name","法定代表人");
        hashMap.put("fr_certificate_type","证件类型");
        hashMap.put("fr_certificate_no","证件号码");
        hashMap.put("fr_certificateStartTime","证件有效期始");
        hashMap.put("fr_certificateEndTime","证件有效期终");
        hashMap.put("fr_contactsTelNumber","法人联系电话");
        hashMap.put("sq_name","授权经办人");
        hashMap.put("sq_certificate_type","证件类型");
        hashMap.put("sq_certificate_no","证件号码");
        hashMap.put("sq_certificateStartTime","证件有效期始");
        hashMap.put("sq_certificateEndTime","证件有效期终");
        hashMap.put("sq_contactsTelNumber","授权经办人联系电话");
        hashMap.put("fzr_name","负责人");
        hashMap.put("fzr_certificate_type","证件类型");
        hashMap.put("fzr_certificate_no","证件号码");
        hashMap.put("fzr_certificateStartTime","证件有效期始");
        hashMap.put("fzr_certificateEndTime","证件有效期终");
        hashMap.put("fzr_contactsTelNumber","负责人联系电话");
        hashMap.put("kggd_name","控股股东/实际控制人");
        hashMap.put("kggd_certificate_type","证件类型");
        hashMap.put("kggd_certificate_no","证件号码");
        hashMap.put("kggd_certificateStartTime","证件有效期始");
        hashMap.put("kggd_certificateEndTime","证件有效期终");
        hashMap.put("kggd_contactsTelNumber","控股股东/实际控制人联系电话");
        hashMap.put("organ_type2","机构类别");
        hashMap.put("organ_tax_statement","机构税收居民身份声明");
        hashMap.put("organ_name_EN","机构名称（英文）");
        hashMap.put("organ_address_EN","地址（英文）");
        hashMap.put("organ_tax_country","税收居民国（地区）（如仅为中国居民后续无需填写）");
        hashMap.put("organ_tax_id","纳税人识别号（如有）");
        hashMap.put("organ_tax_id_reason","如不能提供纳税人识别号请解释提供原因");
        hashMap.put("controller_name","控制人姓名（税收居民身份声明）");
        hashMap.put("controller_flag","控制人个人居民身份识别");
        hashMap.put("controller_tax_country","税收居民国（地区）（如仅为中国居民后续无需填写）");
        hashMap.put("controller_first_name","（控制人）姓（英文或拼音）");
        hashMap.put("controller_family_name","（控制人）名（英文或拼音）");
        hashMap.put("controller_gender","性别");
        hashMap.put("controller_birthday","出生日期");
        hashMap.put("in_address_CN","现居地址（中文）（国家）（省）（市）");
        hashMap.put("in_address_EN","现居地址（英文）（国家）（省）（市）");
        hashMap.put("birth_address_CN","出生地（中文）（国家）（省）（市）");
        hashMap.put("birth_address_EN","出生地（英文）（国家）（省）（市）");
        hashMap.put("controller_tax_id","纳税人识别号（如有）");
        hashMap.put("controller_tax_id_reason","如不能提供纳税人识别号请解释提供原因");
        hashMap.put("customer_type","客户类型");
        hashMap.put("nature_type","性质类别");
        hashMap.put("beneficiary_name","受益所有人姓名");
        hashMap.put("beneficiary_adress","联系地址");
        hashMap.put("beneficiary_id_type","身份证件类别");
        hashMap.put("certificate_no","证件号码");
        hashMap.put("bc_certificateStartTime","有效期起始日");
        hashMap.put("bc_certificateEndTime","有效期到期日");
        hashMap.put("bc_ShareRatio","持股比例");
        hashMap.put("bc_ShareType1","直接/间");
        hashMap.put("bc_ShareType2","持股类型");
        hashMap.put("bc_reason","上述受益人的识别和判断依据");
        hashMap.put("bc_reason_file","具体证明材料");
        hashMap.put("bc_reason_type","上述识别和判断依据的获取方式为");
        hashMap.put("dsh_user_list","董事会成员名单");
        hashMap.put("gg_user_list","高级管理层名单");
        hashMap.put("ois_user_list","股东名单（持股比例超过25%及以上）");
        return hashMap;
    }

    /**
     * @Description 导出crm文件 个人模板表头
     * @Author weijunjie
     * @Date 2020/3/18 17:34
     **/
    public static LinkedHashMap<String,String> getTitleToCrmPersion(){
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("export_date","日期");
        hashMap.put("financi_account","理财账号");
        hashMap.put("channel_name","渠道名称");
        hashMap.put("product_code","产品代码");
        hashMap.put("insure_date","份额确认时间");
        hashMap.put("insure_amount","确认份额");
        hashMap.put("empty_code","虚代码");
        hashMap.put("product_name","产品名称");
        hashMap.put("customer_name","客户名称");
        hashMap.put("customer_type","客户类型");
        hashMap.put("certificate_type","证件类型");
        hashMap.put("certificate_no","证件号码");
        hashMap.put("post_office_code","邮政编码");
        hashMap.put("post_office_address","通讯地址");
        hashMap.put("tel_number","电话号码");
        hashMap.put("phone_number","手机号码");
        hashMap.put("amount","金额");
        hashMap.put("benefit_level","受益级别");
        hashMap.put("payment_date","缴款日期");
        hashMap.put("certificate_start_time","证件有效期起始日期");
        hashMap.put("certificate_end_time","证件有效期到期日期");
        hashMap.put("country","国籍");
        hashMap.put("capital_source","自然人认购资金来源");
        hashMap.put("pi_vocation","职业");
        hashMap.put("pi_gender","性别");
        hashMap.put("pi_birthday","出生日期");
        hashMap.put("pi_indetityAttestation","个人居民身份识别");
        hashMap.put("pi_originalAddress","现居住地址");
        hashMap.put("pi_birthAddress","出生地");
        hashMap.put("pi_taxCountry","税收居民国");
        hashMap.put("pi_taxAccount","纳税人识别号");
        hashMap.put("pi_taxReason","如不能提供纳税人识别号请解释提供原因");
        return hashMap;
    }

    /**
     * @Description 个人反洗钱数据信息导入表头
     * @Author weijunjie
     * @Date 2020/1/19 13:47
     **/
    public static LinkedHashMap<String,String> getAIPTitleForPersonal(){
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("accountId","理财账号");
        hashMap.put("channel_name","渠道名称");
        hashMap.put("product_code","产品代码");
        hashMap.put("share_cfm_date","份额确认时间");
        hashMap.put("share_cfm","确认份额");
        hashMap.put("empty_code","虚代码（如有）");
        hashMap.put("product_name","产品名称");
        hashMap.put("customer_name","客户名称");
        hashMap.put("customer_type","客户类型");
        hashMap.put("certificate_type","证件类型");
        hashMap.put("certificate_no","证件号码");
        hashMap.put("post_office_code","邮政编码");
        hashMap.put("post_office_address","通讯地址");
        hashMap.put("tel_number","电话号码");
        hashMap.put("phone_number","手机号码");
        hashMap.put("payment_amount","金额");
        hashMap.put("benefit_level","受益级别");
        hashMap.put("payment_date","缴款日期");
        hashMap.put("certificate_start_time","证件有效期起始日期");
        hashMap.put("certificate_end_time","证件有效期到期时间");
        hashMap.put("country","国籍");
        hashMap.put("capital_source","自然人认购资金来源");
        hashMap.put("pi_vocation","职业");
        hashMap.put("pi_gender","性别（如提供的为身份证则无需填写）");
        hashMap.put("pi_birthday","出生日期（如提供的为身份证则无需填写）");
        hashMap.put("pi_indetityAttestation","个人居民身份识别");
        hashMap.put("pi_originalAddress","现居住地址");
        hashMap.put("pi_birthAddress","出生地");
        hashMap.put("pi_taxCountry","税收居民国（地区）（如仅为中国居民后续无需填写）");
        hashMap.put("pi_taxAccount","纳税人识别号（如有）");
        hashMap.put("pi_taxReason","如不能提供纳税人识别号请解释提供原因");
        return hashMap;
    }

    /**
     * @Description 机构反洗钱数据表头获取
     * @Author weijunjie
     * @Date 2020/3/17 14:25
     **/
    public static LinkedHashMap<String,String> getAIPTitleForOrgan(){
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put("accountId","理财账号");
        hashMap.put("channel_name","渠道名称");
        hashMap.put("product_code","产品代码");
        hashMap.put("share_cfm_date","份额确认时间");
        hashMap.put("share_cfm","确认份额");
        hashMap.put("empty_code","虚代码（如有）");
        hashMap.put("product_name","产品名称");
        hashMap.put("customer_name","客户名称");
        hashMap.put("customer_type","客户类型");
        hashMap.put("certificate_type","证件类型");
        hashMap.put("certificate_start_time","有效期起始");
        hashMap.put("certificate_end_time","有效期到期");
        hashMap.put("certificate_no","证件号码");
        hashMap.put("post_office_code","邮政编码");
        hashMap.put("post_office_address","通讯地址");
        hashMap.put("tel_number","电话号码");
        hashMap.put("phone_number","手机号码");
        hashMap.put("contacts_address"," 联系人地址");
        hashMap.put("contacts_tel_number","联系人电话");
        hashMap.put("payment_amount","缴款金额");
        hashMap.put("arrival_account_date","到账日期");
        hashMap.put("benefit_level","受益级别");
        hashMap.put("payment_date","缴款日期");
        hashMap.put("capital_source","机构认购资金来源");
        hashMap.put("oi_businessScope","经营范围");
        hashMap.put("work_type","行业类型");
        hashMap.put("org_type","机构类型");
        hashMap.put("oi_registeredCurrency","注册币种");
        hashMap.put("oi_registeredAmount","注册资金");
        hashMap.put("oi_registeredAddress","注册地址");
        //法人数据信息
        hashMap.put("fr_name","法定代表人");
        hashMap.put("fr_certificateType","证件类型");
        hashMap.put("fr_certificateNo","证件号码");
        hashMap.put("fr_certificateStartTime","证件有效期始");
        hashMap.put("fr_certificateEndTime","证件有效期终");
        hashMap.put("fr_contactsTelNumber","法人联系电话");
        //授权经办人数据信息
        hashMap.put("sq_name","授权经办人");
        hashMap.put("sq_certificateType","证件类型");
        hashMap.put("sq_certificateNo","证件号码");
        hashMap.put("sq_certificateStartTime","证件有效期始");
        hashMap.put("sq_certificateEndTime","证件有效期终");
        hashMap.put("sq_contactsTelNumber","授权经办人联系电话");
        //控制股东数据信息
        hashMap.put("kggd_name","控股股东/实际控制人");
        hashMap.put("kggd_certificateType","证件类型");
        hashMap.put("kggd_certificateNo","证件号码");
        hashMap.put("kggd_certificateStartTime","证件有效期始");
        hashMap.put("kggd_certificateEndTime","证件有效期终");
        hashMap.put("kggd_contactsTelNumber","控股股东/实际控制人联系电话");
        //负责人数据信息
        hashMap.put("fzr_name","负责人");
        hashMap.put("fzr_certificateType","证件类型");
        hashMap.put("fzr_certificateNo","证件号码");
        hashMap.put("fzr_certificateStartTime","证件有效期始");
        hashMap.put("fzr_certificateEndTime","证件有效期终");
        hashMap.put("fzr_contactsTelNumber","负责人联系电话");
        //机构其他数据信息
        hashMap.put("ot_org_type","机构类别");
        hashMap.put("ot_org_tax_id","机构税收居民身份声明");
        hashMap.put("ot_org_name_en","机构名称（英文）");
        hashMap.put("ot_org_address_en","地址（英文）");
        hashMap.put("ot_tax_country","税收居民国（地区）（如仅为中国居民后续无需填写）");
        hashMap.put("ot_tax_id","纳税人识别号（如有）");
        hashMap.put("ot_reason","如不能提供纳税人识别号请解释提供原因");
        hashMap.put("ot_kzr_name","控制人姓名（税收居民身份声明）");
        hashMap.put("ot_kzr_id","控制人个人居民身份识别");
        hashMap.put("ot_kzr_tax_country","税收居民国（地区）（如仅为中国居民后续无需填写）");
        hashMap.put("ot_kzr_family_name","（控制人）姓（英文或拼音）");
        hashMap.put("ot_kzr_first_name","（控制人）名（英文或拼音）");
        hashMap.put("ot_kzr_sex","性别");
        hashMap.put("ot_kzr_birthday","出生日期");
        hashMap.put("ot_in_address_cn","现居地址（中文）（国家）（省）（市）");
        hashMap.put("ot_in_address_en","现居地址（英文）（国家）（省）（市）");
        hashMap.put("ot_birth_address_cn","出生地（中文）（国家）（省）（市）");
        hashMap.put("ot_birth_address_en","出生地（英文）（国家）（省）（市）");
        hashMap.put("ot_kzr_tax_id","纳税人识别号（如有）");
        hashMap.put("ot_kzr_reason","如不能提供纳税人识别号请解释提供原因");
        //负责人数据信息
        hashMap.put("fud_org_customer_type","客户类型");
        hashMap.put("fud_org_type","性质类别");
        //客户为股份有限公司、有限责任公司（非国有控股企业）的受益所有人
        hashMap.put("name1","受益所有人姓名");
        hashMap.put("address1","联系地址");
        hashMap.put("certificate_type1","身份证件类别");
        hashMap.put("certificate_id1","证件号码");
        hashMap.put("certificate_start1","有效期起始日");
        hashMap.put("certificate_end1","有效期到期日");
        hashMap.put("shares_scale1","持股比例");
        hashMap.put("dircet_type1","直接/间");
        hashMap.put("shares_type1","持股类型");
        hashMap.put("judge_reason1","上述受益人的识别和判断依据");
        hashMap.put("judge_file1","具体证明材料");
        hashMap.put("judge_type1","上述识别和判断依据的获取方式为");
        hashMap.put("dsh_names1","董事会成员名单");
        hashMap.put("gg_names1","高级管理层名单");
        hashMap.put("gd_names1","股东名单（持股比例超过25%及以上）-姓名");
        hashMap.put("gd_shares_count1","持股数量");
        hashMap.put("gd_shares_type1","持股类型");
        //客户为合伙企业的受益所有人
        hashMap.put("name2","受益所有人姓名");
        hashMap.put("address2","联系地址");
        hashMap.put("certificate_type2","身份证件类型");
        hashMap.put("certificate_id2","证件号码");
        hashMap.put("certificate_start2","有效期起始日");
        hashMap.put("certificate_end2","有效期到期日");
        hashMap.put("scale2","权益比例");
        hashMap.put("judge_reason2","上述受益人的识别和判断依据");
        hashMap.put("judge_file2","具体证明材料");
        hashMap.put("judge_type2","上述识别和判断依据的获取方式");
        //客户为信托的受益所有人
        hashMap.put("name3","受益所有人姓名");
        hashMap.put("address3","联系地址");
        hashMap.put("certificate_type3","身份证件类型");
        hashMap.put("certificate_id3","证件号码");
        hashMap.put("certificate_start3","有效期起始日");
        hashMap.put("certificate_end3","有效期到期日");
        hashMap.put("info3","委托人/受托人/受益人");
        hashMap.put("judge_reason3","上述受益人的识别和判断依据");
        hashMap.put("judge_file3","具体证明材料");
        hashMap.put("judge_type3","上述识别和判断依据的获取方式");
        //客户为基金的受益所有人
        hashMap.put("name4","受益所有人姓名");
        hashMap.put("address4","联系地址");
        hashMap.put("certificate_type4","身份证件类型");
        hashMap.put("certificate_id4","证件号码");
        hashMap.put("certificate_start4","有效期起始日");
        hashMap.put("certificate_end4","有效期到期日");
        hashMap.put("scale4","权益比例");
        hashMap.put("judge_reason4","上述受益人的识别和判断依据");
        hashMap.put("judge_file4","具体证明材料");
        hashMap.put("judge_type4","上述识别和判断依据的获取方式");
        //客户为非政府控制的企事业单位的受益所有人
        hashMap.put("name5","受益所有人姓名");
        hashMap.put("address5","联系地址");
        hashMap.put("certificate_type5","身份证件类型");
        hashMap.put("certificate_id5","证件号码");
        hashMap.put("certificate_start5","有效期起始日");
        hashMap.put("certificate_end5","有效期到期日");
        hashMap.put("judge_reason5","判断为非政府控制的企事业单位的依据");
        hashMap.put("judge_file5","具体证明材料");
        hashMap.put("judge_type5","上述识别和判断依据的获取方式");
        //客户为个体工商户、个人独资企业、不具备法人资格的专业服务机构
        hashMap.put("name6","受益所有人姓名");
        hashMap.put("address6","联系地址");
        hashMap.put("certificate_type6","身份证件类型");
        hashMap.put("certificate_id6","证件号码");
        hashMap.put("certificate_start6","有效期起始日");
        hashMap.put("certificate_end6","有效期到期日");
        hashMap.put("judge_reason6","判断为政府控制的企事业单位的依据");
        hashMap.put("judge_file6","具体证明材料");
        hashMap.put("judge_type6","上述识别和判断依据的获取方式");
        return hashMap;
    }
}