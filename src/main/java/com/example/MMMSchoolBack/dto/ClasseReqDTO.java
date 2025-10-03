package com.example.MMMSchoolBack.dto;

import com.example.MMMSchoolBack.models.Cycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseReqDTO {
    private String nom;
    private Cycle cycle;
}
