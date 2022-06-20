package com.example.eshop.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenConfirmationService {

    private final TokenConfirmationRepo tokenConfirmationRepo;

    public void saveTokenConfirmation(TokenConfirmation token) {
        tokenConfirmationRepo.save(token);
    }

    public Optional<TokenConfirmation> getToken(String token) {
        return tokenConfirmationRepo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenConfirmationRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
