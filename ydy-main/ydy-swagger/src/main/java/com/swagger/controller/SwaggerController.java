package com.swagger.controller;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2019/8/23 09:43
 * @Description:
 */

import com.common.dto.UserDto;
import com.common.util.WebUrl;
import com.common.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:
 * @Description:(这里用一句话描述这个类的作用)
 * @author:
 * @date:
 */
@Api(description = "swagger接口")
@RestController
@RequestMapping(value=WebUrl.SWAGGERURL)
public class SwaggerController {


    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation(value = "查询swagger" ,  notes="查询swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDto", value = "用户信息", required = false, paramType = "com.common.dto.UserDto", dataType = "String")
    })
    @PostMapping(value=WebUrl.GETSWAGGER)
    public String querySwagger(UserDto userDto){
        //UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("张三");
        userDto.setPassword("123456");
        return JsonUtil.getJson(userDto);
    }


}
