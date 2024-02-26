package com.example.ProductManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean authenticate(String email , String password)
    {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password , user.getPassword());
    }
}
