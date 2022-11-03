package lt.viko.eif.vytzab.expenseTrackerApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login", "/register").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("veeezeee").password("12345").authorities("admin")
			.and()
			.withUser("zeeeveee").password("12345").authorities("user")
			.and()
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

}
