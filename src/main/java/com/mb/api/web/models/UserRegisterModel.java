package com.mb.api.web.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegisterModel
{
	@NotBlank(message = "This field must be required!")
	private String firstName;
	
	@NotBlank(message = "This field must be required!")
	private String lastName;
	
	@NotBlank(message = "This field must be required!")
	@Email(message = "please enter valid email")
	private String email;
	
	@NotBlank(message = "This field must be required!")
	private String password;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
	
}
