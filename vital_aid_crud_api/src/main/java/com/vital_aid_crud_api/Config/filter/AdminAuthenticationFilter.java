package com.vital_aid_crud_api.Config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vital_aid_crud_api.Config.SecurityConstants;
import com.vital_aid_crud_api.Config.manager.CustomAuthenticationManager;
import com.vital_aid_crud_api.Entity.Admin;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private CustomAuthenticationManager authenticationManager;

    public AdminAuthenticationFilter(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            Admin admin = new ObjectMapper().readValue(request.getInputStream(), Admin.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    admin.getPersonEmail(),
                    admin.getLoginPassword());
            authentication.setDetails("/vital_aid/admin/login");
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String role = authResult.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"))
                .getAuthority();
        String adminToken = JWT.create()
                .withSubject(authResult.getName())
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + adminToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write the JSON object to the response
        response.getWriter().write(String.format(
                "{ \"token\": \"%s\", \"role\": \"%s\", \"email\": \"%s\" }",
                adminToken, role, authResult.getName()));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext(); // Clear the SecurityContext
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed: " + failed.getMessage());
        response.getWriter().flush();
    }
}