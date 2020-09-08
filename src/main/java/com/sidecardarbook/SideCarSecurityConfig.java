package com.sidecardarbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sidecardarbook.security.SideCarBookJWTAuthenticationFilter;
import com.sidecardarbook.security.SideCarBookJWTAuthorizationFilter;
import com.sidecardarbook.security.SideCarBookSecurity;

@Configuration
@EnableWebSecurity
public class SideCarSecurityConfig {

	@Order(1)
    @Configuration
	public class SideCarAPIConfiguration  extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		

		@Bean
		public AuthenticationProvider SiteAuthProvider() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
			provider.setPasswordEncoder(bCryptPasswordEncoder);
			return provider;
		}
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.antMatcher(SideCarBookSecurity.API_URL.getStringValue()).cors().and().csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new SideCarBookJWTAuthenticationFilter(authenticationManager()))
                .addFilter(new SideCarBookJWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }
	    
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	      return source;
	    }
	}
	
	@Order(2)
	public class SideCarSiteConfiguration  extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
	
		@Bean
		public AuthenticationProvider authProvider() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
			provider.setPasswordEncoder(bCryptPasswordEncoder);
			return provider;
		}
		
		@Override
        protected void configure(HttpSecurity http) throws Exception {
            http.exceptionHandling().accessDeniedPage("/403");
            http  
            .authorizeRequests()  
            .antMatchers(HttpMethod.GET
            		,SideCarBookSecurity.HOME_URL.getStringValue()
            		,SideCarBookSecurity.DELETE_URL.getStringValue()).authenticated() 
            .antMatchers(HttpMethod.POST
            		,SideCarBookSecurity.ADD_URL.getStringValue()
            		,SideCarBookSecurity.UPDATE_URL.getStringValue()).authenticated() 
            .antMatchers(HttpMethod.POST
            		,SideCarBookSecurity.SIGN_UP_URL.getStringValue()).permitAll()
            .and()  
            .formLogin()  
            .and()  
            .httpBasic();   
        }

	    
	}
}
