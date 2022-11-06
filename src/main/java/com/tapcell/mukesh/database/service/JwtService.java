package com.tapcell.mukesh.database.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tapcell.mukesh.database.entity.Users;
import com.tapcell.mukesh.database.repository.UsersRepository;

@Service
public class JwtService implements UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users = usersRepository.findById(username).get();
		
		if(users != null) {
			return new User(users.getEmail(), users.getPassword(), getAuthorities(users));
		}else {
			throw new UsernameNotFoundException("user name not found "+ username);
		}
	}
	
	public Set getAuthorities(Users users) {
		Set authoritie = new HashSet<>();
		
		users.getRoles().forEach(rolls -> {
			authoritie.add(new SimpleGrantedAuthority("ROLE_"+rolls.getRoleName()));
		});
		
		return authoritie;
	}
	
	
}
