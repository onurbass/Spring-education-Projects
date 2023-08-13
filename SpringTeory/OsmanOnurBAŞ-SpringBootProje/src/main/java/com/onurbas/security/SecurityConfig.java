/*
package com.onurbas.security;

import com.onurbas.model.enums.EUserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeHttpRequests(x->x.requestMatchers(HttpMethod.GET,"/users").hasRole(EUserType.USER.toString())
                                           .requestMatchers(HttpMethod.GET,"/users/**").hasRole(EUserType.USER.toString())
                                           .requestMatchers(HttpMethod.POST, "/users").hasRole(EUserType.ADMIN.toString())
                                           .requestMatchers(HttpMethod.PUT, "/users").hasRole(EUserType.ADMIN.toString())
                                           .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole(EUserType.ADMIN.toString())

                                      );

    // use HTTP Basic authentication
    httpSecurity.httpBasic(Customizer.withDefaults());

    // disable Cross Site Request Forgery (CSRF)
    // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
    httpSecurity.csrf(csrf -> csrf.disable());

    return httpSecurity.build();

  }
}
*/
