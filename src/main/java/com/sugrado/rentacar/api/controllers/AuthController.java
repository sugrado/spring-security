package com.sugrado.rentacar.api.controllers;

import com.sugrado.rentacar.business.abstracts.AuthService;
import com.sugrado.rentacar.business.dtos.requests.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", "abc");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 10);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request) {
        String refreshToken = getCookie(request, "refreshToken");
        return authService.refreshToken(refreshToken);
    }

    private String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
