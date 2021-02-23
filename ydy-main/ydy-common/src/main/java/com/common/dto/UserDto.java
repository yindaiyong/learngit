package com.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = -1336554316740307372L;
	private Integer id;// 主键
	private String username;// 用户名
	private String password;// 密码
	public UserDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserDto(){}

	

}
