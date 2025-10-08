package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.mapper.CourMapper;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.repositories.CourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourService {
    private final CourRepo courRepo;
    private final CourMapper courMapper;


    public CourRespDTO create(CourReqDTO courReqDTO){
        Cour cour = courMapper.toEntity(courReqDTO);
        Cour savedCour = courRepo.save(cour);
        return this.courMapper.toDto(savedCour);
    }
}
