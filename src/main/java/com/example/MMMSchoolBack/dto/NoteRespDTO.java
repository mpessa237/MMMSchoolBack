package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRespDTO {
    private Long noteId;
    private Double valeur;
    private String appreciation;

    private EleveSimpleDTO eleve;
    private CourSimpleDTO cour;
    //private EnseignantSimpleDTO enseignant;
}
