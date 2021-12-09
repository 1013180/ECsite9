package jp.winschool.spring.site;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()    
        .loginPage("/login").permitAll()
        .and()
        .logout()
		.logoutSuccessUrl("/")
        ;   
	}
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.withDefaultPasswordEncoder()
	            .username("win").password("password").roles("USER").build();
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(user);
	        return manager;
	    }
	
}