package com.example.MMMSchoolBack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty
    private boolean active = true;

    List<EleveSimpleDTO> eleves;
}
