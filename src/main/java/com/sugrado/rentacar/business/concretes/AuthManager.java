package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.AuthService;
import com.sugrado.rentacar.business.dtos.requests.LoginRequest;
import com.sugrado.rentacar.business.messages.AuthMessages;
import com.sugrado.rentacar.core.services.JwtService;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        }
        return jwtService.generateToken(loginRequest.getEmail());
    }
}