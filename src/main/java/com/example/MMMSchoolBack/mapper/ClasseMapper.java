package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.ClasseReqDTO;
import com.example.MMMSchoolBack.dto.ClasseRespDTO;
import com.example.MMMSchoolBack.dto.EleveSimpleDTO;
import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.models.Cycle;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClasseMapper {

    @Lazy
    private final EleveMapper eleveMapper;

    public ClasseMapper(@Lazy EleveMapper eleveMapper) {
        this.eleveMapper = eleveMapper;
    }

    public Classe toEntity(ClasseReqDTO classeReqDTO){
        Classe classe = new Classe();
        classe.setNom(classeReqDTO.getNom());
        classe.setCycle(classeReqDTO.getCycle());

        return classe;
    }

    public ClasseRespDTO toDto(Classe classe){
        ClasseRespDTO classeRespDTO = new ClasseRespDTO();
        classeRespDTO.setClasseId(classe.getClasseId());
        classeRespDTO.setNom(classe.getNom());

        if (classe.getCycle() != null) {
            classeRespDTO.setCycle(Cycle.valueOf(classe.getCycle().name()));
        }

        // Mappage de la liste : Utilise le DTO simple
        List<EleveSimpleDTO> eleveDtos = classe.getEleves().stream()
                .map(eleveMapper::toSimpleDto) // <--- C'est la clé
                .collect(Collectors.toList());

        classeRespDTO.setEleves(eleveDtos);

        return classeRespDTO;
    }

    public ClasseReqDTO toSimpleDto(Classe classe) {
        if (classe == null) {
            return null;
        }

        ClasseReqDTO classeDTO = new ClasseReqDTO();
        classeDTO.setNom(classe.getNom());

        // CORRECTION CRUCIALE : Mappage de l'énumération 'Cycle'
        if (classe.getCycle() != null) {
            // Conversion de l'énumération (Enum) en sa valeur de nom (String)
            classeDTO.setCycle(Cycle.valueOf(classe.getCycle().name()));
        } else {
            // Gère le cas où le champ est null dans l'entité
            classeDTO.setCycle(null);
        }

        return classeDTO;
    }


}
