package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.configuration.JwtService;
import com.example.MMMSchoolBack.dto.LoginRequest;
import com.example.MMMSchoolBack.dto.LoginResponse;
import com.example.MMMSchoolBack.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginResponse authenticate(LoginRequest loginRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new IllegalArgumentException("user not found!!"));


        //gerer le token
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);
        return loginResponse;
    }
}
