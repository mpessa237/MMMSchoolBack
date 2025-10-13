package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteReqDTO {


    private Double valeur;
    private String appreciation;

    private Long eleveId;
    private Long courId;
   // private Long enseignantId;
}
