package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.dto.CourSimpleDTO;
import com.example.MMMSchoolBack.models.Cour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourMapper {

    private final ClasseMapper classeMapper;


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

        if (cour.getClasses()!=null){
            courRespDTO.setClasses(
                    cour.getClasses().stream()
                            .map(classeMapper::toDto)
                            .collect(Collectors.toList())
            );
        }
        return courRespDTO;
    }

    public CourSimpleDTO toSimpleDTO(Cour cour){
        if (cour==null){
            return null;
        }
        CourSimpleDTO dto = new CourSimpleDTO();
        dto.setCourId(cour.getCourId());
        dto.setNom(cour.getNom());

        return dto;
    }
}
