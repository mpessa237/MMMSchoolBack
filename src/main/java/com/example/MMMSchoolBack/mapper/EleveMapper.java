package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.EleveReqDTO;
import com.example.MMMSchoolBack.dto.EleveRespDTO;
import com.example.MMMSchoolBack.dto.EleveSimpleDTO;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EleveMapper {


    private final ParentMapper parentMapper;
    private final ClasseMapper classeMapper;

    public EleveMapper(ParentMapper parentMapper, ClasseMapper classeMapper) {
        this.parentMapper = parentMapper;
        this.classeMapper = classeMapper;
    }

    public Eleve toEntity(EleveReqDTO eleveReqDTO){
        Eleve eleve = new Eleve();
        eleve.setNom(eleveReqDTO.getNom());
        eleve.setPrenom(eleveReqDTO.getPrenom());
        eleve.setDateDeNaissance(eleveReqDTO.getDateDeNaissance());
        eleve.setGenre(Genre.valueOf(eleveReqDTO.getGenre().toUpperCase()));

        return eleve;
    }

    public EleveRespDTO toDto(Eleve eleve) {
        EleveRespDTO eleveRespDTO = new EleveRespDTO();
        eleveRespDTO.setEleveId(eleve.getEleveId());
        eleveRespDTO.setNom(eleve.getNom());
        eleveRespDTO.setPrenom(eleve.getPrenom());
        eleveRespDTO.setDateDeNaissance(eleve.getDateDeNaissance());
        eleveRespDTO.setMatricule(eleve.getMatricule());
        eleveRespDTO.setGenre(eleve.getGenre().name());

        if (eleve.getClasse() != null) {
            eleveRespDTO.setClasse(classeMapper.toSimpleDto(eleve.getClasse()));
        }
        if (eleve.getParent() != null) {
            eleveRespDTO.setParent(parentMapper.toSimpleDto(eleve.getParent()));
        }
        return eleveRespDTO;
    }

    public EleveSimpleDTO toSimpleDto(Eleve eleve){
        EleveSimpleDTO dto = new EleveSimpleDTO();
        dto.setEleveId(eleve.getEleveId());
        dto.setNom(eleve.getNom());
        dto.setPrenom(eleve.getPrenom());
        return dto;
    }
}