package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.business.dtos.requests.LoginRequest;
import com.sugrado.rentacar.business.dtos.responses.LoggedInResponse;
import com.sugrado.rentacar.business.dtos.responses.RefreshedTokenResponse;

public interface AuthService {
    LoggedInResponse login(LoginRequest loginRequest, String ipAddress);

    RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress);
}
