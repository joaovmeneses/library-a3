package com.library_a3.library_a3.infra.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.library_a3.library_a3.shared.enums.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private  SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/credentials/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/credentials/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/students").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.POST, "/students").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.POST, "/books").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.GET, "/books").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.POST, "/borrows").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.GET, "/borrows").hasAnyAuthority(Role.EMPLOYEE.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.PATCH, "/borrows/{id}/return").hasAuthority(Role.EMPLOYEE.toString())
                                .requestMatchers(HttpMethod.DELETE, "/books/{id}/delete").hasAuthority(Role.EMPLOYEE.toString())
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
