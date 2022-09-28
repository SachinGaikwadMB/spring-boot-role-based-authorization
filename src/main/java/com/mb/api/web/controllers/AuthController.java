package com.mb.api.web.controllers;

import static com.mb.api.constants.GenericConstants.AUTH_URL;
import static com.mb.api.constants.GenericConstants.LOGIN;
import static com.mb.api.constants.GenericConstants.REGISTER;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.mb.api.business.services.UserService;
import com.mb.api.web.models.LoginModel;
import com.mb.api.web.models.UserRegisterModel;

@RestController
@RequestMapping(AUTH_URL)

public class AuthController extends Base
{
	@Autowired
	private UserService userService;
	
	@PostMapping(REGISTER)
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegisterModel userRegisterModel)
	{

		String responseMessage = userService.registerUser(userRegisterModel);

		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}
	
	@PostMapping(LOGIN)
	public ResponseEntity<Map<String, Object>>login(@RequestBody @Valid LoginModel loginModel) {
		
		return new ResponseEntity<>(userService.login(loginModel), HttpStatus.OK);
	}
	
	@GetMapping("/roles-check")
	public String roleCheck() {
		return userService.roleCheck();
	}
	
}
