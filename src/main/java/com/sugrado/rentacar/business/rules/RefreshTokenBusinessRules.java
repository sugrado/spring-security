package com.sugrado.rentacar.business.rules;

import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.entities.concretes.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenBusinessRules {
    public void refreshTokenShouldBeExist(Optional<RefreshToken> refreshToken) {
        if (refreshToken.isEmpty()) {
            throw new BusinessException("Refresh token not found");
        }
    }

    public void refreshTokenShouldNotBeExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Refresh token expired.");
        }
    }

    public void refreshTokenShouldNotBeRevoked(RefreshToken refreshToken) {
        if (refreshToken.getRevokedDate() != null) {
            throw new BusinessException("Refresh token revoked.");
        }
    }
}
