package com.example.MMMSchoolBack.dto;

import com.example.MMMSchoolBack.models.Cycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRespDTO {
    private Long classeId;
    private String nom;
    private Cycle cycle;

    private List<EleveSimpleDTO> eleves;
}
