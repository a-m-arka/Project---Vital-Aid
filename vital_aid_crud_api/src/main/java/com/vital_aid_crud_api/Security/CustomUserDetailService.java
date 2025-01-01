// package com.vital_aid_crud_api.Security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.vital_aid_crud_api.Entity.User;
// import com.vital_aid_crud_api.repository.UserRepository;

// public class CustomUserDetailService implements UserDetailsService{

//     @Autowired
//     private UserRepository userRepository;
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = this.userRepository.findByUserEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//     }


// }
