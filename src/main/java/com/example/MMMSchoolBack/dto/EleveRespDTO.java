package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EleveRespDTO {
    private Long eleveId;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String matricule;
    private String genre;

    private ClasseReqDTO classe;
    private ParentRespDTO parent;
}
