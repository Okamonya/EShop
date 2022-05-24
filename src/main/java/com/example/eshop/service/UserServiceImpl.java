package com.example.eshop.service;

import com.example.eshop.repo.UserRepo;
import com.example.eshop.service.UserService;
import com.example.eshop.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
