package com.library_a3.library_a3.infra.security;

import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null) {
            System.out.println("TOKEN");
            System.out.println(token);
            String subject = this.tokenService.validateToken(token);
            System.out.println("subject");
            System.out.println(subject);
            UserDetails credential = this.credentialsRepository.findByEmail(subject);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credential, null, credential.getAuthorities());
            System.out.println("authentication");
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest) {
        var authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
