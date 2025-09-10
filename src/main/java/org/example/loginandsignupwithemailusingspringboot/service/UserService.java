package org.example.loginandsignupwithemailusingspringboot.service;

import org.example.loginandsignupwithemailusingspringboot.entity.User;
import org.example.loginandsignupwithemailusingspringboot.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // SingUp
    public User signup(String email, String password){
        if (userRepository.existsByEmail(email)){
            throw new RuntimeException("Email Already Registered");
        }

        String randomUsername = getUsername();

        User user = new User();
        user.setUsername(randomUsername);
        user.setEmail(email); // Add this line to set the user's email
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    // Login API CODE
    public User login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid Email or Passwords"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid Email Or Passwords");
        }
        return user;
    }


    // Create Random User Name Like User123, User234 etc
    private String getUsername(){ // Changed method name from geUsername() to getUsername()
        Random random = new Random();
        String username;
        do{
            username = "User" + random.nextInt(1000);
        }while (userRepository.existsByUsername(username));
        return username;
    }
}