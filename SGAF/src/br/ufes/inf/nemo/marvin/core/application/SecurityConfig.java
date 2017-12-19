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
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/sgafdb");
		driverManagerDataSource.setUsername("dwws");
		driverManagerDataSource.setPassword("1234");
		return driverManagerDataSource;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("rodo").password("1234").roles("USER");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource())
				.usersByUsernameQuery("select email as username, password, 1 from User where email=?")//
				.authoritiesByUsernameQuery(
						"select User.email as username, User_Role.roles_name as role from User,User_Role where User.id=User_Role.user_id and User.email=?")
				.passwordEncoder(encoder);

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()//
				.antMatchers("/core/installSystem/**").permitAll()//
				.antMatchers("/core/regUser/**").permitAll()//
				.antMatchers("/core/manageGenres/**").hasRole("ADMIN")//
				.antMatchers("/core/manageUsers/**").hasRole("ADMIN")//
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
