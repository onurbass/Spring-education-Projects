package com.trendyol.bootcampemployeemanagement.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String BEARER_TOKEN = "Bearer b40c3428-83f2-4b26-8046-cdbc4db540f6";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF korumasını devre dışı bırak
                .authorizeRequests()
                .antMatchers("/api/v1/employees/**", "/").permitAll() // Herkese açık
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/api/authentication/basic/**").authenticated() // Sadece kimlik doğrulanmış kullanıcılar
                .antMatchers("/api/authentication/bearer/**").authenticated() // Sadece kimlik doğrulanmış kullanıcılar
                .anyRequest().permitAll() // Diğer tüm istekler için kimlik doğrulama yapma
                .and()
                .addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                .formLogin().disable(); // Form tabanlı login'i devre dışı bırak
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails doruk = User.builder()
                .username("doruk")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(doruk, admin);
    }

    @Bean
    public Filter customFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                String authorization = request.getHeader("Authorization");

                if (request.getRequestURI().startsWith("/api/v1/employees") || request.getRequestURI().startsWith("/api/authentication/basic/basic-authentication") || request.getRequestURI().startsWith("/swagger") ||request.getRequestURI().startsWith("/v2/api-docs")) {
                    // Bu patika için yetkilendirme yapma
                    filterChain.doFilter(request, response);
                    return;
                }

                if (BEARER_TOKEN.equals(authorization)) {
                } else {
                    // Token eşleşmedi, yetkilendirme başarısız.
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    log.warn("Unauthorized request to {}", request.getRequestURI());
                }
            }
        };
    }


}
