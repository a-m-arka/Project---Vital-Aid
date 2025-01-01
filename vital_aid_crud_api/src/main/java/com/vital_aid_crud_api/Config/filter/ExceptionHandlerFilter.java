package com.vital_aid_crud_api.Config.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vital_aid_crud_api.Exception.BadCredentialsException;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }catch(ResourceNotFoundException e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("No user with this email found");
            response.getWriter().flush();
        }catch (BadCredentialsException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
        }catch(RuntimeException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Bad request");
            response.getWriter().flush();
        }catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred while processing the request");
            response.getWriter().flush();
        }
        
    }

    

} 
