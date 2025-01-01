package com.vital_aid_crud_api.Config.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vital_aid_crud_api.Config.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
        try {
            var decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                    .build()
                    .verify(token);
            Date expiresAt = decodedJWT.getExpiresAt();
            if (expiresAt.before(new Date())) {
                // Format the expiration date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));

                String formattedErrorMessage = String.format(
                        "Invalid token: The Token has expired.\n" +
                                "Date of Expiration: %s\n" +
                                "Time of Expiration: %s",
                        dateFormat.format(expiresAt),
                        timeFormat.format(expiresAt));

                // Throw exception with the custom error message
                throw new JWTVerificationException(formattedErrorMessage);
            }

            String email = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").asString();

            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    email, null, List.of(authority));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        catch (JWTVerificationException e) { // Match the exception type you throw
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            // Write the error message in JSON format
            response.getWriter().write("Invalid or expired token. Please login again.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
