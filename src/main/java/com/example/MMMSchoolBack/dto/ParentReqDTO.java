package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentReqDTO {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String profession;

    private Long eleveId;
}
