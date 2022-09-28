package com.mb.api.business.services;

import java.util.Map;
import com.mb.api.web.models.LoginModel;
import com.mb.api.web.models.UserRegisterModel;

public interface UserService
{
	String registerUser(UserRegisterModel userRegisterModel);

	Map<String, Object >login(LoginModel loginModel);
	
	String roleCheck();
}
