package com.sams.common.combobox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sams.sys.mapper.SysDictMapper;
import com.sams.sys.model.SysDict;
import com.sams.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.common.constant.Const;
import com.sams.custom.mapper.ChannelInfoMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.mapper.PChannelManagerMapper;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.mapper.ProductTemplateMapper;

/**
 * @ClassName:  ComboboxController   
 * @Description:下拉菜单   
 * @author: yindy
 * @date:   2019年5月24日 下午5:56:40   
 *
 */
@Controller 
@RequestMapping(value = "/combobox")
public class ComboboxController {
	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private ProductInfoMapper ProductInfoMapper;
	
	@Autowired
	private ProductTemplateMapper productTemplateMapper;
	
	@Autowired
	private PchannelInfoMapper  pchannelInfoMapper;
	
	@Autowired
	private PChannelManagerMapper  channelManagerMapper;
	
	@Autowired
	private ChannelInfoMapper  channelInfoMapper;

	@Autowired
	private SysDictMapper sysDictMapper;

	/**
	 * 查询中登接口基本信息下拉框  
	 * @Title: queryBaseCsdc   
	 * @author: yindy 2019年5月24日 下午6:01:52
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryBaseCsdc", method = RequestMethod.POST)
	@ResponseBody
	public Object queryBaseCsdcCombox(){
		List<Map<String, Object>> list = interfaceColInfoMapper.queryBaseCsdcCombox();
		return JSON.toJSON(list);
	}
	
	/**
	 * 查询证件类型 
	 * @Title: queryCardTypeCombox   
	 * @author: yindy 2019年6月14日 上午10:17:07
	 * @param: @param type 区分个人还是机构
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryCardType", method = RequestMethod.POST)
	@ResponseBody
	public Object queryCardTypeCombox(String type){
		type = Const.GRIDENTITYTYPE;
		if("0".equals(type)){
			type = Const.ZZIDENTITYTYPE;
		}
		List<Map<String, Object>> list = interfaceColInfoMapper.queryCardTypeCombox(type);
		return JSON.toJSON(list);
	}
	
	
	/**
	 * 渠道信息下拉框   
	 * @Title: queryChannelInfoComBox   
	 * @author: yindy 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryChannelInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object queryChannelInfoComBox(){
		List<Map<String, Object>> list = interfaceColInfoMapper.queryChannelInfoComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * 所有渠道信息下拉框   
	 * @Title: queryChannelInfoComBox   
	 * @author: yindy 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryAllChannelInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllChannelInfoComBox(){
		List<Map<String, Object>> list = interfaceColInfoMapper.queryAllChannelInfoComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * TA渠道信息   
	 * @Title: queryChannelInfoComBox   
	 * @author: wangchao 2019年8月13日 上午11:24:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTAChannelInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTAChannelInfo(){
		List<Map<String, Object>> list = pchannelInfoMapper.queryTAChannelInfo();
		return JSON.toJSON(list);
	}
	
	/**
	 * T+N产品下拉框   
	 * @Title: queryTnFundInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTnFundInfoComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTnFundInfoComBox(String channelCode){
		List<Map<String, Object>> list = ProductInfoMapper.queryTnFundInfoComBox(channelCode);
		return JSON.toJSON(list);
	}
	
	
	/**
	 * 产品下拉框   
	 * @Title: queryProductInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryProductInfoComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryProductInfoComBox(String channelCode){
		List<Map<String, Object>> list = ProductInfoMapper.queryProductInfoComBox(channelCode);
		return JSON.toJSON(list);
	}
	
	/**
	 * 所有产品下拉框   
	 * @Title: queryAllProductInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryAllProductInfoComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllProductInfoComBox(String channelCode){
		List<Map<String, Object>> list = ProductInfoMapper.queryAllProductInfoComBox(channelCode);
		return JSON.toJSON(list);
	}
	
	/**
	 * 所有在用产品下拉框   
	 * @Title: queryAllProductInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryUsedProductInfoComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUsedProductInfoComBox(String channelCode){
		List<Map<String, Object>> list = ProductInfoMapper.queryUsedProductInfoComBox(channelCode);
		return JSON.toJSON(list);
	}
	
	/**
	 * 查询渠道所有的子渠道信息  
	 * @Title: queryAllProductInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/querySubChannelComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object querySubChannelComBox(String channelCode){
		List<Map<String, Object>> list = channelInfoMapper.selectSubChannelList(channelCode);
		return JSON.toJSON(list);
	}
	
	/**
	 * TA产品下拉框   
	 * @Title: queryTaFundInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTaFundInfoComBox", method = RequestMethod.GET)
	@ResponseBody
	public Object queryTaFundInfoComBox(String fundCode,String channelCode){
		Map<String,Object> map = Maps.newHashMap();
		map.put("fundCode", fundCode);
		map.put("channelCode", channelCode);
		List<Map<String, Object>> list = ProductInfoMapper.queryTaFundInfoComBox(map);
		return JSON.toJSON(list);
	}
	
	/**
	 * TA所有产品下拉框   
	 * @Title: queryTaFundInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryAllTaFundInfoComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllTaFundInfoComBox(){
		List<Map<String, Object>> list = ProductInfoMapper.queryAllTaFundInfoComBox();
		return JSON.toJSON(list);
	}
	
	
	
	/**
	 * 产品模板编号下拉框   
	 * @Title: queryTempalteCodeComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTempalteCodeComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTempalteCodeComBox(){
		List<Map<String, Object>> list = productTemplateMapper.queryTempalteCodeComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * 产品模板名称下拉框   
	 * @Title: queryTempalteNameComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTempalteNameComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTempalteNameComBox(){
		List<Map<String, Object>> list = productTemplateMapper.queryTempalteNameComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * 渠道名称下拉框   
	 * @Title: queryChanneleNameComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryChanneleNameComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryChanneleNameComBox(){
		List<Map<String, Object>> list = pchannelInfoMapper.queryChanneleNameComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * 渠道客户经理名称下拉框   
	 * @Title: queryChanneleNameComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryManagerNameComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryManagerNameComBox(){
		List<Map<String, Object>> list = pchannelInfoMapper.queryManagerNameComBox();
		List<Map<String, Object>> arrayList = Lists.newArrayList();
		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null){
				arrayList.add(list.get(i));
			}
		}
		return JSON.toJSON(arrayList);
	}
	
	
	/**
	 * 渠道客户经理手机号下拉框   
	 * @Title: queryChanneleNameComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryManagerPhoneComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryManagerPhoneComBox(){
		List<Map<String, Object>> list = pchannelInfoMapper.queryManagerPhoneComBox();
		List<Map<String, Object>> arrayList = Lists.newArrayList();
		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null){
				arrayList.add(list.get(i));
			}
		}
		return JSON.toJSON(arrayList);
	}
	
	/**
	 * 模板下拉框   
	 * @Title: queryTempalteComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTempalteComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTempalteComBox(){
		List<Map<String, Object>> list = productTemplateMapper.queryTempalteComBox();
		return JSON.toJSON(list);
	}
	
	/**
	 * Ta客户经理下拉框   
	 * @Title: queryTempalteComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/queryTaManagerComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTaManagerComBox(){
		List<Map<String, Object>> list = channelManagerMapper.selectTaManager();
		return JSON.toJSON(list);
	}

	/**
	 * @Description 获取产品类型列表下拉框
	 * @Author weijunjie
	 * @Date 2020/4/30 17:38
	 **/
	@RequestMapping(value = "/queryFundTypeComBox", method = RequestMethod.POST)
	@ResponseBody
	public Object queryFundTypeComBox(){
        List<Map<String, Object>> objects = new ArrayList<>();
        List<SysDict> fundTypes = sysDictMapper.findChildByDictType("fundType");
        for(SysDict sysDict:fundTypes){
			Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("ID",sysDict.getDictCode());
            hashMap.put("NAME",sysDict.getDictName());
           objects.add(hashMap);
        }
        return JSON.toJSON(objects);
	}
	
}
