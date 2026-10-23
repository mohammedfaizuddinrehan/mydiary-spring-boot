package com.example.practiceset.service;

import com.example.practiceset.entity.User;
import com.example.practiceset.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncoder;
    }
    public String registerUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
    return "email already exists re";
        }

        String hashedPassword  = passwordEncode.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return "registerd hurray";
    }
}
