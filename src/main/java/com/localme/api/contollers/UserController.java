package com.localme.api.contollers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.localme.api.dao.CategoryRepo;
import com.localme.api.dao.GameRepo;
import com.localme.api.dao.UserRepo;
import com.localme.api.service.CategoryService;
import com.localme.api.service.GameService;
import com.localme.api.utils.EncryptDecryptUtil;
import com.localme.api.vo.Category;
import com.localme.api.vo.GameDetail;
import com.localme.api.vo.GameList;
import com.localme.api.vo.UserDetailsVO;
import com.localme.api.vo.UserLoginVO;


@RestController
public class UserController {


	@Autowired
	UserRepo userRepo;
	
	@Autowired
	GameRepo gameRepo;
	
	@Autowired
	CategoryRepo catRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	GameService gameService;
	
	EncryptDecryptUtil encryptUtil  = new EncryptDecryptUtil();
	
	 Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/getUser")
	public Map<String, Object> getUser(@RequestBody UserLoginVO uservo) {
		logger.trace("Entering method get user");
		logger.debug("Logged in user:"+uservo.getUsername());
		Map<String, Object> result = new HashMap<String,Object>();
		UserDetailsVO userDetails = userRepo.findByuid(uservo.getUsername());
		if(userDetails == null) {
			logger.error("User not found");
			result.put("STATUS", "FAILURE");
			result.put("ERROR", "USER NOT FOUND");
		}else {
			String decodedPwd =  encryptUtil.decrypt(userDetails);
			System.out.println("Decoded PWd : "+decodedPwd);
			if(decodedPwd.equals(uservo.getPassword())) {
			result.put("STATUS", "SUCCESS");
			userDetails.setPwd("");
			result.put("DATA", userDetails);
			}else {
				result.put("STATUS", "FAILURE");
				result.put("DATA", "Invalid Credentials");
			}
		}
		return result;
	}
	
	@PostMapping("/addUser")
	public Map<String, Object> addUser(@RequestBody UserDetailsVO uservo) {
		
		Map<String, Object> result = new HashMap<String,Object>();
		UserDetailsVO userDetails = userRepo.findByuid(uservo.getUid());
	if(userDetails == null )
		{
		String encodedPwd =  encryptUtil.encrypt(uservo);
		System.out.println("Encoded PWd : "+encodedPwd);
		 uservo.setPwd(encodedPwd);
		UserDetailsVO restultvo = userRepo.save(uservo);
		result.put("STATUS", "SUCCESS");
		restultvo.setPwd("");
		result.put("DATA", restultvo);
		}
	else {
		result.put("STATUS","FAILURE");
		
	}
		return result;
	}
	
	
	@PostMapping("/addGame")
	public Map<String, Object> addGame(@RequestBody GameDetail gamevo) {
		
		Map<String, Object> result = new HashMap<String,Object>();
		GameDetail gameDetail = gameRepo.findByname(gamevo.getName());
	if(gameDetail == null )
		{
		GameDetail restultvo = gameRepo.save(gamevo);
		result.put("STATUS", "SUCCESS");
		result.put("DATA", restultvo);
		}
	else {
		result.put("STATUS","FAILURE");
		
	}
		return result;
	}
	
	@PostMapping("/getGame")
	public Map<String, Object> getGame(@RequestBody GameList gamevo) {
		logger.trace("Entering method get Game");
		Map<String, Object> result = new HashMap<String,Object>();
		GameDetail gameDetail = gameRepo.findByname(gamevo.getName());
		if(gameDetail == null) {
			logger.error("game not found");
			result.put("STATUS", "FAILURE");
			result.put("ERROR", "GAME NOT FOUND");
		}else {
			result.put("STATUS", "SUCCESS");
			result.put("DATA", gameDetail);
			}
		return result;
	}
	
	@GetMapping("/findGameForCategory/{name}")
	 List<GameDetail> findGamesForCategory(@PathVariable String name) {
		logger.trace("Entering method");
	   return gameService.findGamesForCategory(name);
	 } 
	@GetMapping("/findAllCategories")
	 Iterable<Category> findAllCategories() {
	  return categoryService.findAllCategories();
	 }
}
