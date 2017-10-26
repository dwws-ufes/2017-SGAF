package br.ufes.inf.nemo.marvin.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/sgafDB");
		driverManagerDataSource.setUsername("dwws");
		driverManagerDataSource.setPassword("1234");
		return driverManagerDataSource;
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("rodo").password("1234").roles("USER");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource())
				.usersByUsernameQuery("select email as username, password, 1 from user where email=?")//
				.authoritiesByUsernameQuery(
						"select user.email as username, userrole.role as role from userrole,user where user.id=userrole.user_id and user.email=?")
				.passwordEncoder(encoder);

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()//
				.antMatchers("/core/installSystem/**").permitAll()//
				.antMatchers("/core/regUser/**").permitAll()//
				.antMatchers("/core/**").authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/core/login/")//
				.permitAll()//
				.loginProcessingUrl("/appLogin").permitAll()//
				.usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/core/login/appLogin.faces").and().logout().permitAll();
	}

}
