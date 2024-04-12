package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.AuthService;
import com.sugrado.rentacar.business.abstracts.RefreshTokenService;
import com.sugrado.rentacar.business.abstracts.UserService;
import com.sugrado.rentacar.business.dtos.requests.LoginRequest;
import com.sugrado.rentacar.business.dtos.responses.LoggedInResponse;
import com.sugrado.rentacar.business.dtos.responses.RefreshedTokenResponse;
import com.sugrado.rentacar.business.messages.AuthMessages;
import com.sugrado.rentacar.core.services.JwtService;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.entities.concretes.RefreshToken;
import com.sugrado.rentacar.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public LoggedInResponse login(LoginRequest loginRequest, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        }
        User user = userService.findByUsername(loginRequest.getEmail());
        refreshTokenService.revokeOldTokens(user, ipAddress);
        RefreshToken refreshToken = refreshTokenService.create(user);
        String accessToken = generateJwt(user);
        return new LoggedInResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress) {
        RefreshToken token = refreshTokenService.verify(refreshToken);
        RefreshToken newToken = refreshTokenService.rotate(token, ipAddress);
        refreshTokenService.revokeOldTokens(token.getUser(), ipAddress);
        String accessToken = generateJwt(token.getUser());
        return new RefreshedTokenResponse(accessToken, newToken.getToken());
    }

    private String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        return jwtService.generateToken(user.getEmail(), claims);
    }
}