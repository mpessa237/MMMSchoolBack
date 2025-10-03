package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.models.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ParentMapper {


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

        return parentRespDTO;
    }
}
