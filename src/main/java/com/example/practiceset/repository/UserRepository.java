package com.example.practiceset.repository;
import com.example.practiceset.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


    public interface UserRepository extends JpaRepository<User,Long>{

        Optional<User> findByEmail(String Email);
        Boolean existsByEmail(String Email);
    }

