package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EleveReqDTO {
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String genre;

    private Long classeId;
    private Long parentId;
}
