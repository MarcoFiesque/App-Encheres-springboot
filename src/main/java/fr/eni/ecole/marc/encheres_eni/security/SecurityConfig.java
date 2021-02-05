package fr.eni.ecole.marc.encheres_eni.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () { 
		return new BCryptPasswordEncoder();
	}
	
	/*@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
		
		//System.out.println("Password Encoded BCRYPT :******************** "); 
		//System.out.println(passwordEncoder.encode("123"));
		
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/createArticle").hasAnyRole("ADMIN", "MEMBER");
		http.authorizeRequests().antMatchers("/saveArticle").hasAnyRole("ADMIN", "MEMBER");
		http.authorizeRequests().antMatchers("/supprimerArticle").hasAnyRole("ADMIN", "MEMBER");
		http.authorizeRequests().antMatchers("/modifierArticle", "/updateArticle").hasAnyRole("ADMIN", "MEMBER");
		http.authorizeRequests().antMatchers("/webjars/**", "/profil/**").permitAll(); 
		http.authorizeRequests().antMatchers("/", "/login", "/register", "/saveUser", "/css/**", "/showUtilisateurs", "/show_user**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();

		http.formLogin().loginPage("/login")
						.usernameParameter("username")
						.defaultSuccessUrl("/", true);
        http.exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	
}
//System.out.println("Password Encoded BCRYPT :******************** "); 
		//System.out.println(passwordEncoder.encode("123"));
		
		/*
		auth.jdbcAuthentication()
      .dataSource(dataSource)
		.usersByUsernameQuery("select username , password , enabled from user where username =?")
		.authoritiesByUsernameQuery(
		"SELECT u.username, r.role " + 
		"FROM user_role ur, user u , role r "+ 
		"WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = ?")
      .passwordEncoder(passwordEncoder)
      .rolePrefix("ROLE_");	*/

		//auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
		//auth.inMemoryAuthentication().withUser("marc").password(passwordEncoder.encode("123")).roles("MEMBER","USER");
		//auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("123")).roles("USER");
	
