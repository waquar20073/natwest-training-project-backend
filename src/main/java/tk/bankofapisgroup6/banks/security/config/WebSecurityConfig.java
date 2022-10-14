package tk.bankofapisgroup6.banks.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.AccountService;
import tk.bankofapisgroup6.banks.accounts.CustomUserDetailsService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtAuthenticationFilter jwtFilter;
	@Autowired
	AccountService accountService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .cors().and()
                .authorizeRequests()
                    .antMatchers("/api/v*/jwt/token")
                    .permitAll()
                .anyRequest()
                .authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService); 
	}
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
//  @Bean
//  CorsConfigurationSource corsConfigurationSource() 
//  {
//    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOrigins(Arrays.asList("*"));
//    configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//  }
    
}
