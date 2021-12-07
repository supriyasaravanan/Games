package com.localme.api.vo.userimpl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.localme.api.dao.UsersRepo;
import com.localme.api.exception.BusinessException;
import com.localme.api.service.UserInterface;
import com.localme.api.vo.AuthRequest;
import com.localme.api.vo.Users;


@Service
public class UserService implements UserInterface{
	
	@Autowired
	UsersRepo userRepo;

	@Override
		public Users getUser(AuthRequest userdetails) {

			try {
				Users gameDet=userRepo.findByUsername(userdetails.getUsername());
				
				if(gameDet==null)
				{
					throw new BusinessException("405","Not Present");
				}
				Users savedEmployees=userRepo.findByUsername(userdetails.getUsername());
				return savedEmployees;	
			}
			catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
			
	 }		
		
}