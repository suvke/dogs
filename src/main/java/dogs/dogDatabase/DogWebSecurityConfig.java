package dogs.dogDatabase;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dogs.dogDatabase.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class DogWebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		//määritellään mille sivuille kaikilla käyttäjillä on pääsy ja minne vain ADMIN-käyttäjillä
		http.
		authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/grouplist/**")).permitAll()
				.requestMatchers(antMatcher("/dogs/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/groups/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/dog/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/save/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/saveedit/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/add/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/edit/**")).hasAuthority("ADMIN")
				.requestMatchers(antMatcher("/delete/**")).hasAuthority("ADMIN")
				.anyRequest().authenticated()
		)
		.headers(headers -> headers
			    .frameOptions(frameOptions -> frameOptions  // for h2console
			        .disable()
			    )
		)
		//määritetään login-sivu sekä oletussivu, jolle ohjaudutaan sisäänkirjautumisen jälkeen
		.formLogin(formlogin -> formlogin
				.loginPage("/login")
				.defaultSuccessUrl("/doglist", true)
				.permitAll()
		)
		.logout(logout -> logout
				.permitAll())
				.csrf(csrf -> csrf.disable()
		);		
				
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
