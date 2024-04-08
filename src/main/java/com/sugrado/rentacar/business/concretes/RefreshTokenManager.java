package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.RefreshTokenService;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.dataAccess.abstracts.RefreshTokenRepository;
import com.sugrado.rentacar.entities.concretes.RefreshToken;
import com.sugrado.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RefreshTokenManager implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken create(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken("abc"); // TODO: refactor
        refreshToken.setExpirationDate(LocalDateTime.now().plusDays(10));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verify(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException("Refresh token not found"));

        // TODO: Tüm şartlar sağlanıyorsa
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Refresh token expired.");
        }

        if (refreshToken.getRevokedDate() != null) {
            throw new BusinessException("Refresh token revoked.");
        }
        return refreshToken;
    }

    private void revokeAllTokens(User user) {
        // TODO: revoke all tokens
    }
}
