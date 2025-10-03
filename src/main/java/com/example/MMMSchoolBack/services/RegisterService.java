package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.UserRequest;
import com.example.MMMSchoolBack.models.Role;
import com.example.MMMSchoolBack.models.User;
import com.example.MMMSchoolBack.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequest userRequest){

        //on verifie si email exist
        if (userRepo.findByEmail(userRequest.getEmail()).isPresent()){
            throw new IllegalArgumentException("cet email existe deja!!");
        }

        //entrer les informations du user
        User user = new User();
        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Set.of(Role.ADMIN));

        // enregistrer le user
         userRepo.save(user);

    }
}
