package com.sams.business.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.alibaba.fastjson.JSONObject;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.utils.StringUtils;
import com.sams.common.web.PageInfo;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.ReadFileService;
import com.sams.common.constant.Const;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.PchannelInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/viewFileData")
public class ViewFileDataController extends BaseController{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ViewFileDataController.class);
	
	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private ReadFileService readFileService;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;

	@Autowired
	private ExchangeProcessorService processorService;

    FTPUtils ftp = new FTPUtils();

	@RequestMapping(method = RequestMethod.GET)
    public String viewFileData() {
        return "sys/viewFileData";
    }
	
	/**
	 * 查看生成文件的数据
	 * @Title: getFileData   
	 * @author: yindy 2019年5月15日 上午9:40:36
	 * @param: @param date
	 * @param: @param channelId
	 * @param: @param type
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping( value ="/getFileData", method = RequestMethod.POST)
	@ResponseBody
    public Object getFileData(String date,String channelId,String type,Integer page, Integer rows) {
		Map<String,Object> retMap = new HashMap<String, Object>();
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		date = date.replace("-", "");
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelId);
		if(channelInfo == null){
			retMap.put("rows",new ArrayList<Map<String,Object>>());
			retMap.put("total", 0);
			return retMap;
		}
		String filePath = MapUtils.getString(fileDataService.getDictInfo(),"ftplocaldir")+File.separator
				+Const.FTP_UPLOAD+File.separator+channelId+File.separator+date;
		LOGGER.info("查看的文件路径为:"+filePath);
		retMap = readFileService.readFile(filePath,type, date,channelInfo);
		if(retMap.get("datalist") != null){
			data = (List<Map<String, Object>>) retMap.get("datalist");
//			LOGGER.info("查看的文件类型为"+type+",渠道编码为:"+channelId+",数据为:"+data);
		}
		int size = (data == null ? 0 : data.size());
		int start = (page-1)*rows;
		int end = page*rows;

        List<Map<String, Object>> retList  = new ArrayList<Map<String,Object>>();
		  for(int i = start;i < (end > size ? size: end ); i++ ){
		  		retList.add(data.get(i));
		  }
		retMap.put("rows", retList);
		retMap.put("total", size);
		return retMap;
    }

    /**
     * @Description 根据文件类型生成title数据信息
     * @Author weijunjie
     * @Date 2019/11/15 11:30
     **/
    @RequestMapping( value ="/getFileTitle")
    @ResponseBody
    public Object getFileTitle(String type) {
//	    type = null == type?"R2":type;
        List<Map<String, Object>> cnNameByfundCode = fileDataService.getColumnsTitle(type);
        return cnNameByfundCode;
    }

	/**
	 * @Description 上传文件接收方法
	 * @Author weijunjie
	 * @Date 2020/5/8 10:47
	 **/
	@RequestMapping("/uploadFileToFtp")
	@ResponseBody
	public Object uploadFileToFtp(@RequestParam(value = "files[]", required = true) MultipartFile[] files, String type, String channelCode,String businessDate) throws Exception{
		try {
            if(StringUtils.isBlank(channelCode) || StringUtils.isBlank(businessDate)){
                return resultSuccess("日期数据为null");
            }
            if(files.length <1){
                return resultSuccess("未选择文件，上传失败");
            }

            businessDate = businessDate.replaceAll("-","");
            for(MultipartFile mFile : files){
                String fileName = mFile.getOriginalFilename();
                //判断文件与日期是否符合
                if(!fileName.contains(businessDate)){
                    return resultSuccess("日期文件不符，上传失败");
                }
                //判断文件是否符合
                String[] split = fileName.split("\\.");
                String fileSuf = split[split.length-1];
                if("OK".equals(fileSuf) || "TXT".equals(fileSuf) || "txt".equals(fileSuf)){
                    //文件后缀验证通过
                }else{
                    return resultSuccess("文件格式非法");
                }
            }
            long start = System.currentTimeMillis();
            logger.info("**************执行文件上传开始**************");
            for(MultipartFile mFile : files){
                String fileName = mFile.getOriginalFilename();
                InputStream fileIs = mFile.getInputStream();
                processorService.uploadFiles(channelCode,fileIs,"0",fileName);
            }
            long end = System.currentTimeMillis();
            logger.info("**************文件上传完成，耗时：{}**************",end-start);
            return resultSuccess("success");
        }catch (Exception e){
		    e.printStackTrace();
            return resultSuccess("error");
        }finally {
            try {
                ftp.getCloseFtpClient();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

	}


	/**
	 * @Description 多文件压缩下载
	 * @Author weijunjie
	 * @Date 2020/5/13 15:09
	 **/
	@RequestMapping("/downloadFile")
    @ResponseBody
	public void downloadFile(String ckNames,String type, String channelCode,String businessDate,HttpServletRequest request, HttpServletResponse response){
	    businessDate = businessDate.replaceAll("-","");
        List<Map<String, String>> maps = processorService.checkFileStatus(channelCode, businessDate, type);
        List<Map<String, String>> localUrlToDow = processorService.getFileLocalUrls(channelCode, businessDate,maps);
        //组装下载文件名数据
        PchannelInfo pchannelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
        String fileType = "";
        if("1".equals(type)){
            fileType = "行情文件";
        }else if("2".equals(type)){
            fileType = "确认文件";
        }else{
            fileType = "申请文件";
        }
        List<Map<String, String>> urlMapToDow = new ArrayList<>();
        //解析获取ckNames 的内容数据
        for(Map<String, String> m:localUrlToDow){
            if(ckNames.contains(MapUtils.getString(m,"fileName",""))){
                urlMapToDow.add(m);
            }
        }
        String fileName = pchannelInfo.getCiChannelName()+businessDate+fileType+".zip";
        downloadFiles(fileName,request,response,urlMapToDow);
    }

    /**
     * @Description 确认条件下是否有对应的文件数据
     * @Author weijunjie
     * @Date 2020/5/13 15:09
     **/
    @RequestMapping("/checkFileDow")
    @ResponseBody
    public Object checkFileDow(String ckNames,String type, String channelCode,String businessDate,HttpServletRequest request, HttpServletResponse response){
        if("3".equals(type)){
            return resultSuccess("申请数据文件不允许下载！！！ 重新操作");
        }
        if(StringUtils.isBlank(channelCode) || StringUtils.isBlank(businessDate)){
            return resultSuccess("日期数据为null");
        }else{
            businessDate = businessDate.replaceAll("-","");
            List<Map<String, String>> maps = processorService.checkFileStatus(channelCode, businessDate, type);
            //执行文件下载操作
            /*for(Map<String,String> map:maps){
                String fileUrl = MapUtils.getString(map,"fileUrl","");
                processorService.downloadFileFromFtp(fileUrl);
            }*/
            if(maps.size() == 0){
                return resultSuccess("找不到对应的文件");
            }
            List<Map<String, String>> localUrlToDow = processorService.getFileLocalUrls(channelCode, businessDate,maps);
            //判断当前选中的文件是否存在
            List<Map<String, String>> urlMapToDow = new ArrayList<>();
            //解析获取ckNames 的内容数据
            for(Map<String, String> m:localUrlToDow){
                if(ckNames.contains(MapUtils.getString(m,"fileName",""))){
                    urlMapToDow.add(m);
                }
            }
            if(urlMapToDow.size() == 0){
                return resultSuccess("找不到对应的文件");
            }else{
                return resultSuccess("success");
            }
        }

    }

    /**
     * @Description 展示上传文件方法
     * @Author weijunjie
     * @Date 2020/5/13 15:09
     **/
    @RequestMapping("/showUploadFileStatus")
    @ResponseBody
    public PageInfo showUploadFileStatus(String type, String channelCode,String businessDate){
        PageInfo pageInfo = new PageInfo(1, 20);
        if(StringUtils.isBlank(channelCode) || StringUtils.isBlank(businessDate)){
            pageInfo.setPageResult(new ArrayList());
        }else{
            businessDate = businessDate.replaceAll("-","");
            pageInfo.setPageResult(processorService.getFileStatusForPage(pageInfo,channelCode,businessDate,type));
        }
        return pageInfo;
    }

    /**
     * @Description ftp服务器下载对应文件数据  -- 边压缩边下载
     * @Author weijunjie
     * @Date 2020/5/14 10:22
     **/
    public void downloadFiles(String downloadName,HttpServletRequest request, HttpServletResponse response, List<Map<String,String>> fileUrlMaps){

        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        //设置压缩包的名字
        downloadName = downloadName.contains(".zip")?downloadName:downloadName+".zip";
        String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE")||agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"),"ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        //循环将文件写入压缩流
        DataOutputStream os = null;
        //遍历对象，获取文件下载全路径
        for(Map<String,String> map:fileUrlMaps){
            String fileName = MapUtils.getString(map,"fileName","");
            String fileUrl = MapUtils.getString(map,"fileUrl","");
            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                zipos.putNextEntry(new ZipEntry(fileName));
                os = new DataOutputStream(zipos);
                if(StringUtils.isBlank(fileUrl)){
                    continue;
                }
                InputStream is = null;
                is = new FileInputStream(new File(fileUrl));
                if(is == null){
                    continue;
                }else{
                    byte[] b = new byte[1024];
                    int length = 0;
                    while((length = is.read(b))!= -1){
                        os.write(b, 0, length);
                    }
                    is.close();
                    zipos.closeEntry();
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        //关闭流
        try {
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
}