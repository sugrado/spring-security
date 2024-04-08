package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.entities.concretes.RefreshToken;
import com.sugrado.rentacar.entities.concretes.User;

public interface RefreshTokenService {
    RefreshToken create(User user);

    RefreshToken verify(String token);

    RefreshToken rotate(RefreshToken token, String ipAddress);

    void revokeOldTokens(User user, String ipAddress);
}
