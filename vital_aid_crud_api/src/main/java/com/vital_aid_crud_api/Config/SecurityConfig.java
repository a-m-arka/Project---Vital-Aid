package com.vital_aid_crud_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vital_aid_crud_api.Config.filter.AdminAuthenticationFilter;
import com.vital_aid_crud_api.Config.filter.ExceptionHandlerFilter;
import com.vital_aid_crud_api.Config.filter.JWTAuthorizationFilter;
import com.vital_aid_crud_api.Config.filter.UserAuthenticationFilter;
import com.vital_aid_crud_api.Config.manager.CustomAuthenticationManager;

@Configuration
public class SecurityConfig {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(customAuthenticationManager);
        userAuthenticationFilter.setFilterProcessesUrl("/vital_aid/user/login");

        AdminAuthenticationFilter adminAuthenticationFilter = new AdminAuthenticationFilter(customAuthenticationManager);
        adminAuthenticationFilter.setFilterProcessesUrl("/vital_aid/admin/login");
        http
                .csrf(csrf -> csrf.disable())
                
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers(HttpMethod.POST, SecurityConstants.USER_REGISTER_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_REGISTER_PATH).permitAll()

                    .requestMatchers(HttpMethod.POST, SecurityConstants.USER_LOGIN_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_LOGIN_PATH).permitAll()

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_ADMIN_GET_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_USER_GET_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.SINGLE_USER_VIEW_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.USER_PROFILE_DETAILS).hasAuthority(
                        "ROLE_USER")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.ADMIN_PROFILE_DETAILS).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.PUT, SecurityConstants.USER_PROFILE_PHOTO_UPLOAD_PATH).hasAuthority("ROLE_USER")
                    
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.USER_PROFILE_UPDATE).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.ADMIN_PROFILE_UPDATE).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.PUT, SecurityConstants.USER_CHANGE_PASSWORD).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.ADMIN_CHANGE_PASSWORD).hasAuthority("ROLE_ADMIN")
        

                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.USER_DELETE_PATH).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.ADMIN_DELETE_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.POST, SecurityConstants.USER_FORGET_PASSWORD_SEND_CODE_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.USER_VALIDATE_OTP_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.USER_RESET_PASSWORD_PATH).permitAll()

                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_FORGET_PASSWORD_SEND_CODE_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_VALIDATE_OTP_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_RESET_PASSWORD_PATH).permitAll()

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_DOCTOR_GET_PATH).permitAll()
                    .requestMatchers(HttpMethod.GET, SecurityConstants.SINGLE_DOCTOR_VIEW_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.DOCTOR_REGISTER_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.DOCTOR_UPDATE_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.DOCTOR_DELETE_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.POST, SecurityConstants.HOSPITAL_REGISTER_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_HOSPITAL_GET_PATH).permitAll()
                    .requestMatchers(HttpMethod.GET, SecurityConstants.SINGLE_HOSPITAL_VIEW_PATH).permitAll()
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.HOSPITAL_UPDATE_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.HOSPITAL_DELETE_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET,SecurityConstants.ALL_APPOINTMENTS_LIST_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_APPOINTMENT_BY_TOKEN_PATH).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_USER_ALL_APPOINTMENTS_PATH).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_DOCTOR_ALL_APPOINTMENTS_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, SecurityConstants.MAKE_APPOINTMENT_PATH).hasAuthority("ROLE_USER")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_AMBULANCES_LIST_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_AMBULANCE_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_AMBULANCE_BY_NUM_PLATE_PATH).permitAll()
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_AMBULANCE_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_AMBULANCE_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_PRODUCTS_LIST_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, SecurityConstants.ADD_PRODUCT_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_PRODUCT_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_PRODUCT_PATH).hasAuthority("ROLE_ADMIN")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_ORDERS_LIST_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_ORDER_BY_ID_PATH).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_USER_ALL_ORDERS_PATH).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.POST, SecurityConstants.MAKE_ORDER_PATH).hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_ORDER_PATH).hasAuthority("ROLE_USER")

                    .requestMatchers(HttpMethod.GET, SecurityConstants.ALL_CALLS_LIST_PATH).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, SecurityConstants.VIEW_CALL_BY_ID_PATH).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, SecurityConstants.MAKE_CALL_PATH).hasAuthority("ROLE_USER")
                    
                    .anyRequest().authenticated())
            .addFilterBefore(new ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilter(userAuthenticationFilter)
            .addFilter(adminAuthenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
    }

}
