package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.mapper.ParentMapper;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.models.Parent;
import com.example.MMMSchoolBack.repositories.EleveRepo;
import com.example.MMMSchoolBack.repositories.ParentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepo parentRepo;
    private final EleveRepo eleveRepo;
    private final ParentMapper parentMapper;

    @Transactional
    public ParentRespDTO create(ParentReqDTO parentReqDTO){

        Parent parent = parentMapper.toEntity(parentReqDTO);

        List<Eleve> eleveList = new ArrayList<>();

        for (Long eleveId : parentReqDTO.getEleveIds()){

            Eleve eleve = eleveRepo.findById(eleveId)
                    .orElseThrow(()-> new NoSuchElementException("eleve non trouve avec cet iD:" +eleveId));
            eleve.setParent(parent);

            eleveList.add(eleve);
        }
        parent.setEleves(eleveList);
        Parent savedParent = parentRepo.save(parent);
        return parentMapper.toDto(savedParent);

    }

}
