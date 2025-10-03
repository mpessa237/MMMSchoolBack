package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRespDTO {
    private Long parentId;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String profession;

    List<EleveReqDTO> eleveReqDTOS;
}
