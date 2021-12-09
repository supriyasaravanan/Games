package com.localme.api.contollers;

import java.util.HashMap;


import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.localme.api.dao.CategoryRepo;
import com.localme.api.dao.GameRepo;
import com.localme.api.exception.BusinessException;
import com.localme.api.exception.ControllerException;
import com.localme.api.exception.ErrorDetails;
import com.localme.api.service.CategoryService;
import com.localme.api.service.GameService;
import com.localme.api.utils.JwtUtility;
import com.localme.api.vo.AuthRequest;
import com.localme.api.vo.Category;
import com.localme.api.vo.GameDetail;
import com.localme.api.vo.GameList;
import com.localme.api.vo.JwtRequest;
import com.localme.api.vo.JwtResponse;
import com.localme.api.vo.userimpl.CustomUserDetailsService;


@RestController
public class UserController {
	
	@Autowired
	GameRepo gameRepo;
	
	@Autowired
	CategoryRepo catRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Autowired
    private JwtUtility jwtUtility;
	
    @Autowired
    private AuthenticationManager authenticationManager;

	
	 Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	 @PostMapping("/addGame")
		public ResponseEntity<?> addGame(@RequestBody GameDetail gamedetail){
			logger.trace("Entering method add Game");
			GameDetail gameDet=gameRepo.findByname(gamedetail.getName());
			 if(gameDet!=null)
				{
				 ErrorDetails errorDetails = new ErrorDetails(404,"Name is already present");
				    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);	
				}
			 else
			 {
				 if(gamedetail.getName().isEmpty() || gamedetail.getName().length() == 0 )
					{ErrorDetails errorDetails = new ErrorDetails(404,"Enter a valid name");
				    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);	
					} 
			 }
				
			try 
			{
				GameDetail employeeSaved = gameService.addGame(gamedetail);
				return new ResponseEntity<GameDetail>(employeeSaved, HttpStatus.CREATED);
		    }
			catch(BusinessException e)
			{
				ErrorDetails errorDetails = new ErrorDetails(404,"Enter a valid category");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
			}
			catch (Exception e) 
			{
				ErrorDetails errorDetails = new ErrorDetails(404,"Enter a category value");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
			}
			
		}
		
	
	
	@GetMapping("/getGame")
	public ResponseEntity<?>  getGame(@RequestBody GameList gamedetails) {
		logger.trace("Entering method get Game");
		if(gamedetails.getName().isEmpty() || gamedetails.getName().length() == 0 )
		{
			ErrorDetails errorDetails = new ErrorDetails(404,"Enter the game name");
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
		try 
		{
			GameDetail gameFind=gameService.getGame(gamedetails);
			return new ResponseEntity<GameDetail>(gameFind, HttpStatus.OK);
		
		}catch (Exception e) {
			ErrorDetails errorDetails = new ErrorDetails(404,"Not present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}	
	}	
	
	@PostMapping("/addCat")
	public ResponseEntity<?> addCat(@RequestBody Category gamedetail){
		logger.trace("Entering method add cat");
		if(gamedetail.getName().isEmpty() || gamedetail.getName().length() == 0 )
		{
			ErrorDetails errorDetails = new ErrorDetails(404,"Enter the valid username");
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
		
		try 
		{
			Category employeeSaved = gameService.addCat(gamedetail);
			return new ResponseEntity<Category>(employeeSaved, HttpStatus.CREATED);
	    }
		catch (Exception e) 
		{
			ErrorDetails errorDetails = new ErrorDetails(404,"Already Present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
		
	}
	

	@GetMapping("/findGameForCategory/{name}")
	public ResponseEntity<?> findGamesForCategory(@PathVariable String name) {
		logger.trace("Entering method");
		if(name.isEmpty() ||name.length() == 0  )
		{
			ErrorDetails errorDetails = new ErrorDetails(404,"Enter the valid username");
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
		try
		{
			  List< GameDetail> gameDet= gameService.findGamesForCategory(name);
			   return new ResponseEntity<List<GameDetail>>(gameDet, HttpStatus.OK);
		}
			   catch (Exception e) 
				{
					ErrorDetails errorDetails = new ErrorDetails(404,"Not found");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
				}
	 }
	
	/*@GetMapping("/findGameForCategory/{name}")
	 List<GameDetail> findGamesForCategory(@PathVariable String name) {
		logger.trace("Entering method");
			   return gameService.findGamesForCategory(name);
	 } */
	
	@GetMapping("/findAllCategories")
	 Iterable<Category> findAllCategories() {
	  return categoryService.findAllCategories();
	 }
	
	
}
