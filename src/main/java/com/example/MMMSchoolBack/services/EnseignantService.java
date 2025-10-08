package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.EnseignantReqDTO;
import com.example.MMMSchoolBack.dto.EnseignantRespDTO;
import com.example.MMMSchoolBack.mapper.EnseignantMapper;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.models.Enseignant;
import com.example.MMMSchoolBack.repositories.CourRepo;
import com.example.MMMSchoolBack.repositories.EnseignantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EnseignantService {

    private final EnseignantRepo enseignantRepo;
    private final EnseignantMapper enseignantMapper;
    private final CourRepo courRepo;


    @Transactional
    public EnseignantRespDTO create(EnseignantReqDTO enseignantReqDTO){

        Enseignant enseignant = enseignantMapper.toEntity(enseignantReqDTO);

        List<Cour> courList = new ArrayList<>();

        for (Long courId : enseignantReqDTO.getCourIds()){
            Cour cour = courRepo.findById(courId)
                    .orElseThrow(()->new NoSuchElementException("cour non trouve avec cet ID:" + courId));
            cour.setEnseignant(enseignant);

            courList.add(cour);
        }

        enseignant.setCours(courList);
        Enseignant savedEnseignant = enseignantRepo.save(enseignant);
        return enseignantMapper.toDto(savedEnseignant);
    }
}
