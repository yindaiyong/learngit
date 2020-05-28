package com.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2019/8/22 13:59
 * @Description:
 */
public interface UserDtoMapper {

    String selectUsername(@Param("userName") String userName);

    String selectPassword(@Param("userName") String userName);
}
