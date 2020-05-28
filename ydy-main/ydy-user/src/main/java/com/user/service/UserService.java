package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.dto.UserDto;

@Service
public interface UserService {

	public boolean selectUsername(String username);

	public String selectPassword(String username);
}
