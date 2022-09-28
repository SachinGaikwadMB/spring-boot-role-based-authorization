package com.mb.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.mb.api.jwt.AuthEntryPoint;
import com.mb.api.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//auth.inMemoryAuthentication().withUser("sachin").password("{noop}password").authorities("STUDENT");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception
	{
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/v1/test/home").permitAll()
		.antMatchers("/api/v1/auth/**").permitAll()
		.antMatchers("/api/v1/test/welcome").authenticated()
		//.antMatchers("/api/v1/students").hasAuthority("ADMIN")
		.antMatchers("/api/v1/test/admin").hasAuthority("ADMIN")
		.antMatchers("/api/v1/test/employee").hasAuthority("USER")
		.anyRequest().authenticated()
		
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		.and()
		.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
			
//		.and()
//		.formLogin()
//		.defaultSuccessUrl("/api/v1/test/welcome", true)
//		
//		.and()
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		;
	}
	
}
