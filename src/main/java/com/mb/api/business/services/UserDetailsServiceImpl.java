package com.mb.api.business.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> user = userRepository.findByEmail(username);
		if (!user.isPresent())
			throw new UsernameNotFoundException("Error :: Username not found !!!");
		
	
		List<GrantedAuthority> authorities = 
		user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
				
		return new org.springframework.security.core.userdetails.
				User(user.get().getEmail(), user.get().getPassword(),authorities);
	}

}
