package com.localme.api.vo.userimpl;

import java.util.ArrayList;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.localme.api.dao.UsersRepo;
import com.localme.api.exception.BusinessException;
import com.localme.api.vo.AuthRequest;
import com.localme.api.vo.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	 @Autowired
	 private UsersRepo userRepo;
	 
	   @Autowired
		private PasswordEncoder bcryptEncoder;

	    
	   @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Users user = userRepo.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		}
	   
		
	   public Users save(AuthRequest user) throws BusinessException{
			
			
			try {
				Users userDet=userRepo.findByUsername(user.getUsername());
				if(userDet!=null)
				{
					throw new BusinessException("400","Already Exisit");
				}
			Users newUser = new Users();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			return userRepo.save(newUser);
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
		
	 }


}
