package com.user.service;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2019/8/22 13:34
 * @Description:
 */

import com.common.dto.UserDto;
import com.common.util.StringUtil;
import com.user.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:(这里用一句话描述这个类的作用)
 * @author:
 * @date:
 */
@Service
public class UserServiceImpl  implements UserService{


    @Autowired
    private UserDtoMapper userDtoMapper;

    @Override
    public boolean selectUsername(String username) {
        String userName = userDtoMapper.selectUsername(username);
        if(StringUtil.isNotEmpty(userName)){
            return true;
        }
        return false;
    }

    @Override
    public String selectPassword(String username) {
        String password = userDtoMapper.selectPassword(username);
        return password;
    }
}
