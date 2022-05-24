package com.example.eshop.service;

import com.example.eshop.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();
    
}
