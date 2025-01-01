
package com.vital_aid_crud_api.Config.manager;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.vital_aid_crud_api.Entity.Admin;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.BadCredentialsException;
import com.vital_aid_crud_api.Payloads.UserDTO;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.PersonRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String personEmail = authentication.getName();
        String loginPassword = authentication.getCredentials().toString();

        // Check if the request is for a user or admin login
        String loginUrl = (String) authentication.getDetails();

        if ("/vital_aid/user/login".equals(loginUrl)) {
            return authenticateUser(personEmail, loginPassword);
        } else if ("/vital_aid/admin/login".equals(loginUrl)) {
            return authenticateAdmin(personEmail, loginPassword);
        }

        throw new BadCredentialsException("Invalid login URL");
    }

    private Authentication authenticateUser(String email, String password) {
        User user = userRepository.findByPersonEmail(email).orElse(null);

        if (user == null) {
            throw new BadCredentialsException("No User with this email found.");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getLoginPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }
        // long personId = user.getPersonId();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getPersonRole()));
        return new UsernamePasswordAuthenticationToken(email, password,authorities);
    }

    private Authentication authenticateAdmin(String email, String password) {
        Admin admin = adminRepository.findByPersonEmail(email).orElse(null);

        if (admin == null) {
            throw new BadCredentialsException("No Admin with this email found.");
        }

        if (!bCryptPasswordEncoder.matches(password, admin.getLoginPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(admin.getPersonRole()));
        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
