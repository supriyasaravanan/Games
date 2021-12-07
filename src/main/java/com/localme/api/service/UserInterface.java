package com.localme.api.service;

import com.localme.api.exception.BusinessException;
import com.localme.api.vo.AuthRequest;

import com.localme.api.vo.Users;

public interface UserInterface {

	public Users getUser(AuthRequest userdetails) throws BusinessException ;
	
}
