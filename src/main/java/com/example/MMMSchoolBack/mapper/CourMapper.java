package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.models.Cour;
import org.springframework.stereotype.Component;

@Component
public class CourMapper {


    public Cour toEntity(CourReqDTO courReqDTO){

        Cour cour = new Cour();
        cour.setNom(courReqDTO.getNom());
        cour.setCoefficient(courReqDTO.getCoefficient());
        return cour;
    }

    public CourRespDTO toDto(Cour cour){
        CourRespDTO courRespDTO = new CourRespDTO();
        courRespDTO.setCourId(cour.getCourId());
        courRespDTO.setNom(cour.getNom());
        courRespDTO.setCoefficient(cour.getCoefficient());

        return courRespDTO;
    }
}
