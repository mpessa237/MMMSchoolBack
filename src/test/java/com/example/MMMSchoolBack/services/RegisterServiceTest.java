package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.UserRequest;
import com.example.MMMSchoolBack.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
     private RegisterService registerService;

        private UserRequest userRequest;
        private String rawPassword = "password123";
        private String encodedPassword = "hashed_password";

        @BeforeEach
    void setUp(){
            userRequest = new UserRequest(
                    "dupont",
                    "jean",
                    "jean@gmail.com",
                    rawPassword
            );
            when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        }




}