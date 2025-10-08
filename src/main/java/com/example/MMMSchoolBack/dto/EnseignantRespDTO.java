package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnseignantRespDTO {
    private Long enseignantId;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;

    List<CourRespDTO> cours;
}
