package com.example.eshop.service;

import com.example.eshop.repo.UserRepo;
import com.example.eshop.token.TokenConfirmation;
import com.example.eshop.token.TokenConfirmationService;
import com.example.eshop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {


    private static final String USER_NOT_FOUND_MSG =
            "user with email %s is not found";
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenConfirmationService tokenConfirmationService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)
                        ));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepo.findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already used");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepo.save(user);

//create, saving and sending confirmation email after signup
        String token = UUID.randomUUID().toString();

        //creating and saving token
        TokenConfirmation tokenConfirmation = new TokenConfirmation(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        tokenConfirmationService.saveTokenConfirmation(
                tokenConfirmation);
        return token;
    }

    public int enableUser(String email) {
        return userRepo.enableUser(email);
    }
}
