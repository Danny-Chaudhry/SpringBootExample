package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();//Remove before Deployment
		http.headers().frameOptions().disable();//Remove before Deployment
		//what URL's are restricted to what roles
		http.authorizeRequests()
			//we restrict URL's not HTML
			.antMatchers("/viewStudents").hasAnyRole("PROFESSOR", "STUDENT")
			.antMatchers("/addStudent").hasRole("PROFESSOR")
			.antMatchers("/edit/**").hasRole("PROFESSOR")
			.antMatchers("/delete/**").hasRole("PROFESSOR")
			//Add more antMatchers for restricted pages
			.antMatchers("/", "/images/**", "css/**", "/js/**", "/access-denied", "/**").permitAll()
			.anyRequest().authenticated()
			
		//Define our custom login page
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
		//define logout
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()
		//define unauthorized access
			.and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
