package com.mb.api.business.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mb.api.business.exceptions.CustomException;
import com.mb.api.constants.ERole;
import com.mb.api.jwt.JwtUtils;
import com.mb.api.persistance.entity.Role;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.RoleRepository;
import com.mb.api.persistance.repository.UserRepository;
import com.mb.api.web.models.LoginModel;
import com.mb.api.web.models.UserRegisterModel;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public String registerUser(UserRegisterModel userRegisterModel)
	{

		Optional<User> optUser = userRepository.findByEmail(userRegisterModel.getEmail());
		
		if (optUser.isPresent())
		{
			throw new CustomException("Error : User Already exist with email " + userRegisterModel.getEmail());
		}
		
		try
		{
			Set<Role> roles = new HashSet<>();
			
			Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).orElseThrow(()-> new CustomException("Role Not Found!!")); 
			roles.add(userRole);
			
			User user = modelMapper.map(userRegisterModel, User.class);
			user.setPassword(passwordEncoder.encode(userRegisterModel.getPassword()));
			user.setRoles(roles);
			
			userRepository.save(user);
		}
		catch (Exception e)
		{
			throw new CustomException("Internal Server error !!!");
		}
		return "Succeess : User register successfully !";
	}

	@Override
	public Map<String, Object >login(LoginModel loginModel)
	{
		Map<String, Object> data = new HashMap<>();
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		 org.springframework.security.core.userdetails.User securiyUser  = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
		
		 System.out.println(securiyUser.getAuthorities());
		 List<String> roles = securiyUser.getAuthorities().stream().map((item) -> item.getAuthority()).collect(Collectors.toList());
		 
		 
		String token = jwtUtils.generateJwtToken(loginModel.getEmail());
		
		data.put("token", token);
		data.put("message", "Login Successful!!");
		data.put("role", roles);
		data.put("username", securiyUser.getUsername());
		
		return data;
	}

	@Override
	public String roleCheck()
	{
	
		System.out.println(roleRepository.findByRoleName(ERole.ROLE_USER).get());
		return "Role check method called please check console!!";
	}

	
	
	
}
