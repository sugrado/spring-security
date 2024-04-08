package com.sugrado.rentacar.dataAccess.abstracts;

import com.sugrado.rentacar.entities.concretes.RefreshToken;
import com.sugrado.rentacar.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUserAndRevokedDateIsNullAndExpirationDateBefore(User user, LocalDateTime expirationDate);
}
