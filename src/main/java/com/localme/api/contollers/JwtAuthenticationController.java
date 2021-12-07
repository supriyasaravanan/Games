package com.localme.api.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.localme.api.exception.ErrorDetails;
import com.localme.api.utils.JwtUtility;
import com.localme.api.vo.AuthRequest;
import com.localme.api.vo.JwtRequest;
import com.localme.api.vo.JwtResponse;
import com.localme.api.vo.Users;
import com.localme.api.vo.userimpl.CustomUserDetailsService;
import com.localme.api.vo.userimpl.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtility jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AuthRequest user) throws Exception {
		try {
		return ResponseEntity.ok(userDetailsService.save(user));
		}
		catch (Exception e) {
			ErrorDetails errorDetails = new ErrorDetails(404,"Already present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<?>  getUser(@RequestBody  AuthRequest user) {
		
		try 
		{
			Users gameFind=userService.getUser(user);
			return new ResponseEntity<Users>(gameFind, HttpStatus.OK);
		}catch (Exception e) {
			ErrorDetails errorDetails = new ErrorDetails(404,"Not present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		}
			
	}

}
