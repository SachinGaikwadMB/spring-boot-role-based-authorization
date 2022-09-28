package com.mb.api.web.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginModel
{
	@NotBlank(message = "This field must be required!")
	@Email(message = "Please enter valid email")
	private String email;

	@NotBlank
	private String password;

}
