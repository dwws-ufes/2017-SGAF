package br.ufes.inf.nemo.marvin.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ro").password("123").roles("USER");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http
        .authorizeRequests()//	
            .anyRequest().authenticated()// 
            .and()//	
        .formLogin()//                      
        .loginPage("/core/login/index.faces")//
        .permitAll()//
        .usernameParameter("email")//
        .passwordParameter("password")//
        .and().logout().permitAll();
	}

}
