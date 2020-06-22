package com.sams.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sams.common.utils.StringUtils;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.SysDict;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;

import net.sf.json.JSONArray;

/**
 * 字典管理
 * @author hq 2016-9-12 08:31:16
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 所有字典树
     * @return
     */
    @RequestMapping("/allTree/json")
    @ResponseBody
    public Object allTree() {
        return sysDictService.findDictAllTree(null);
    }
    
    /**
     * 字典管理页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
    	return "sys/sysDict";
    }

    /**
     * 字典管理列表
     * @return
     */
    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "/treeGrid/json", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return sysDictService.findDictAllVo(null);
    }

    /**
     * 添加页面
     * @return
     */
    @RequiresPermissions("sys:dict:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return "sys/sysDictAdd";
    }

    /**
     * 添加字典数据
     * @param sysDict
     * @return
     */
    @RequiresPermissions("sys:dict:add")
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SysDict sysDict) {
		if (StringUtils.isBlanks(sysDict.getDictPid())) {
            sysDict.setDictPid("");
		}
    	sysDictService.save(sysDict);
        return resultSuccess("添加成功！");
    }


    /**
     * 编辑字典页面
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        SysDict sysDict = sysDictService.selectByKey(id);
        model.addAttribute("sysDict", sysDict);
        return "sys/sysDictEdit";
    }

    /**
     * 编辑字典
     * @param dict
     * @return
     */
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysDict dict) {
		SysDict sysDict = sysDictService.selectByKey(dict.getDictId());
        if (sysDict != null) {
            sysDict.setDictPid(StringUtils.isBlanks(dict.getDictPid())?"":dict.getDictPid());
            sysDict.setDictCode(dict.getDictCode());
            sysDict.setDictCode(dict.getDictCode());
            sysDict.setDictName(dict.getDictName());
            sysDict.setDictType(dict.getDictType());
            sysDict.setDictSeq(dict.getDictSeq());
            sysDict.setDescription(dict.getDescription());
            //PID可能为空且有外键约束，所以update全部
            sysDictService.updateAll(sysDict);
        }
        return resultSuccess("编辑成功！");
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @RequiresPermissions("sys:resource:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        sysDictService.delete(id);
        return resultSuccess("删除成功！");
    }
    
    /**
     * 查询字典
     * @param id
     * @return
     */
    public JSONArray  dictgroup(String dict)
    		throws Exception {
    	List<SysDictVo> fundStatusList= sysDictService.findDictAllVo(dict);
    	for(int i=0;i<fundStatusList.size();i++){
    		if(fundStatusList.get(i).getDictCode()==null||"".equals(fundStatusList.get(i).getDictCode())){
    			fundStatusList.remove(i);
    		}
    	}
    	JSONArray array = JSONArray.fromObject(fundStatusList);			
    	return array;
    }

    
    /**
     * 查询基金状态的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/fundStatusGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  getProfitsGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
    	JSONArray array = dictgroup("fundStatus");
		return array;
	}
    
    /**
     * 查询基金状态的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/fundStatusGroup1", method = RequestMethod.POST)
	@ResponseBody
    public Object  getProfitsGroup1( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
    	List<Map<String,Object>> fundStatusList= sysDictService.findFundStatus("fundStatus");
		return fundStatusList;
	}
    
    /**
     * 查询产品类型的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/productTypeGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  productTypeGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("fundType");
		return array;
	}
    
    /**
     * 查询市场的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/marketCodeGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  marketCodeGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("marketCode");
		return array;
	}
    
    /**
     * 查询复核状态的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkStateGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  checkStateGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("checkState");
		return array;
	}
    
    /**
     * 查询启停状态的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/validFlagGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  validFlagGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("validFlag");
		return array;
	}
    
    /**
     * 查询支付币种的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/currencyGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  currencyGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("currency");
		return array;
	}
    
    /**
     * 查询支付方式的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/payTypeGroup", method = RequestMethod.POST)
	@ResponseBody
    public Object  payTypeGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		
		List<Map<String, Object>> list = sysDictService.queryTaTcapType();
		return JSON.toJSON(list);
		
	}
    
    
    
    /**
     * 查询所有的角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRoleIds", method = RequestMethod.POST)
	@ResponseBody
    public Object  getRoleId( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		List<Map<String, Object>> list = sysDictService.getRoleId();
		return JSON.toJSON(list);
		
	}
    
    /**
     * 中登版本的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/csdcVerGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  csdcVerGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("csdcVer");
		return array;
	}
    
    /**
     * 电子合同校验方式的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/econVerifyTypeGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  econVerifyTypeGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("econVerifyType");
		return array;
	}
    
    /**
     * 交易类型的数据字典
     * @param id
     * @return
     */
    @RequestMapping(value = "/businessCodeGroup", method = RequestMethod.POST)
	@ResponseBody
    public JSONArray  businessCodeGroup( HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONArray array = dictgroup("businessCode");
		return array;
	}

	/**
	 * @Description 下拉列表对象取值
	 * @Author weijunjie
	 * @Date 2020/3/13 9:39
	 **/
	@RequestMapping("/getSysDictForCombobox")
    @ResponseBody
	public com.alibaba.fastjson.JSONArray getSysDictForCombobox(String dictName,String type){
        List<Map<String, Object>> maps = sysDictService.selectByDictName(dictName);
        type = StringUtils.isBlank(type)?"":type;
        if(type.equals("show")){
            for(Map<String, Object> map:maps){
                map.put("disabled",true);
            }
        }
        return com.alibaba.fastjson.JSONArray.parseArray(JSONObject.toJSONString(maps));
    }
    
}
