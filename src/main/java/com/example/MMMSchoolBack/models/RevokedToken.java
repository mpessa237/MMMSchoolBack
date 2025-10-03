package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revoked_tokens")
public class RevokedToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revokedTokenId;
    private String token;
    private Long expirationTime;
}
