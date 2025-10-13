package com.example.MMMSchoolBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourRespDTO {
    private Long courId;
    private String nom;
    private Double coefficient;

    List<ClasseRespDTO> classes;
}
