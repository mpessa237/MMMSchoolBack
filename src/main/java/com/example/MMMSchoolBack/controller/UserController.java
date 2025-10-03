package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.LoginRequest;
import com.example.MMMSchoolBack.dto.LoginResponse;
import com.example.MMMSchoolBack.dto.UserRequest;
import com.example.MMMSchoolBack.services.LoginService;
import com.example.MMMSchoolBack.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody UserRequest userRequest){
        registerService.register(userRequest);
        return ResponseEntity.ok("inscription reussie!!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Validated @RequestBody LoginRequest loginRequest){

        try {
            // Le service va lancer une AuthenticationException en cas d'échec
            LoginResponse response = loginService.authenticate(loginRequest);

            // Succès : retour de la réponse (HTTP 200 OK)
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // Échec d'authentification (mauvais identifiants, etc.)

            // 1. Log pour le serveur
            System.err.println("Échec de connexion par Bad Credentials: " + loginRequest.getEmail());

            // 2. Renvoi du statut HTTP correct (401 Unauthorized)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    // Utilisez un objet d'erreur simple pour le frontend
                    .body(new ErrorResponse("Bad credentials", "Email ou mot de passe incorrect."));

        } catch (Exception e) {
            // Pour toute autre exception non capturée (ex: DB, NPE)
            System.err.println("Erreur interne inattendue lors de la connexion: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Error", "Une erreur interne inattendue est survenue."));
        }
    }
}
// Note: Vous devrez peut-être créer une classe simple 'ErrorResponse' pour le corps de la réponse d'erreur.
class ErrorResponse {
    public String errorCode;
    public String message;

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
