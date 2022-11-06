package com.tapcell.mukesh.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapcell.mukesh.database.entity.JwtRequest;
import com.tapcell.mukesh.database.entity.JwtResponse;
import com.tapcell.mukesh.database.entity.Users;
import com.tapcell.mukesh.database.repository.User_RoleRepository;
import com.tapcell.mukesh.database.repository.UsersRepository;
import com.tapcell.mukesh.database.service.JwtService;
import com.tapcell.mukesh.database.util.JwtUtil;

@RestController
@CrossOrigin
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private User_RoleRepository user_RoleRepository;
	
	@PostMapping("/getToken")
	public JwtResponse getToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		
		String username = jwtRequest.getUserName();
		String password = jwtRequest.getPassword();
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad credential");
			
		}catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad credential");
		}
		
		UserDetails userDetails = this.jwtService.loadUserByUsername(username);
		
		String token =jwtUtil.generateToken(userDetails);
		
		
		
		String roleString = user_RoleRepository.findById(username).get().getRole_Id();
		
		
		
		return new JwtResponse(token, roleString);
	}

}
