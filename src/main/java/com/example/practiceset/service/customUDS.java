package com.example.practiceset.service;

import com.example.practiceset.entity.User;
import com.example.practiceset.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUDS implements UserDetailsService {
    private UserRepository userRepository;
public customUDS(UserRepository userRepository){
    this.userRepository= userRepository;
}
@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
            .orElseThrow(()->
                    new UsernameNotFoundException( "user not found re"));

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password((user.getPassword()))
            .authorities("ROLE_USER")
            .build();
}
}
