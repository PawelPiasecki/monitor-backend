package com.monitor.security;

import com.monitor.security.jwt.JWTAuthenticationFilter;
import com.monitor.security.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class MultiHttpSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/*@Configuration
	@Order(1)
	public class WebSecurityConfigForViews extends WebSecurityConfigurerAdapter {




		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.antMatcher("/view*//**")
					.authorizeRequests()
					.anyRequest().authenticated()
					.and()
					.formLogin()
					.loginPage("/view/loginxd")
					.permitAll()
					.and()
					.logout().logoutUrl("/view/logout")
					.permitAll();
		}





	}*/




		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// disable caching
			http.headers().cacheControl();
			http.headers().frameOptions().disable();

			//http.cors().and();
			http.csrf().disable() // disable csrf for our requests.
					.authorizeRequests()

					.antMatchers("/test").permitAll()
					.antMatchers("/registration").permitAll()
					.antMatchers("/websockets*").permitAll()
					.antMatchers("/broker").permitAll()
					.antMatchers("/view/**").permitAll()
					.antMatchers("/systems").permitAll()
					.antMatchers("/").permitAll()
					.antMatchers(HttpMethod.POST, "/login").permitAll()
					.anyRequest().authenticated()


					.and()
					// We filter the api/login requests
					.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
					// And filter other requests to check the presence of JWT in header
					.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);




  /*  @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost","http://localhost:8100"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*", configuration);
		 return source;
		 }*/
	}
}
