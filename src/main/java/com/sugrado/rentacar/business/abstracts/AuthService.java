package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.business.dtos.requests.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
