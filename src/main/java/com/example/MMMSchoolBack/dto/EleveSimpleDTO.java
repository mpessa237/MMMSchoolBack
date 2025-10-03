package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EleveSimpleDTO {
    private Long eleveId;
    private String nom;
    private String prenom;
}
