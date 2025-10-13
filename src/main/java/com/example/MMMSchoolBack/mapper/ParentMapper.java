package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.models.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component

public class ParentMapper {

    @Lazy
    private final EleveMapper eleveMapper;

    public ParentMapper(@Lazy EleveMapper eleveMapper) {
        this.eleveMapper = eleveMapper;
    }


    public Parent toEntity(ParentReqDTO parentReqDTO){
        Parent parent = new Parent();
        parent.setNom(parentReqDTO.getNom());
        parent.setPrenom(parentReqDTO.getPrenom());
        parent.setEmail(parentReqDTO.getEmail());
        parent.setTelephone(parentReqDTO.getTelephone());
        parent.setAdresse(parentReqDTO.getAdresse());
        parent.setProfession(parentReqDTO.getProfession());

        return parent;
    }

    public ParentRespDTO toDto(Parent parent){
        ParentRespDTO parentRespDTO = new ParentRespDTO();
        parentRespDTO.setParentId(parent.getParentId());
        parentRespDTO.setNom(parent.getNom());
        parentRespDTO.setPrenom(parent.getPrenom());
        parentRespDTO.setEmail(parent.getEmail());
        parentRespDTO.setAdresse(parent.getAdresse());
        parentRespDTO.setTelephone(parent.getTelephone());
        parentRespDTO.setProfession(parent.getProfession());

        // CORRECTION CRUCIALE : Mappage des élèves
        if (parent.getEleves() != null) {
            parentRespDTO.setEleves(
                    parent.getEleves().stream()
                            .map(eleveMapper::toSimpleDto) // Suppose que EleveMapper a une méthode toSimpleDto
                            .collect(Collectors.toList())
            );
        }

        return parentRespDTO;
    }

    public ParentRespDTO toSimpleDto(Parent parent) {
        if (parent == null) {
            return null;
        }

        ParentRespDTO parentRespDTO = new ParentRespDTO();
        parentRespDTO.setParentId(parent.getParentId());
        parentRespDTO.setNom(parent.getNom());
        parentRespDTO.setPrenom(parent.getPrenom());
        parentRespDTO.setEmail(parent.getEmail());
        parentRespDTO.setAdresse(parent.getAdresse());
        parentRespDTO.setProfession(parent.getProfession());
        parentRespDTO.setTelephone(parent.getTelephone());

        return parentRespDTO;
    }
}
