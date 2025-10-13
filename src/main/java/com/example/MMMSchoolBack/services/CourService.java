package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.mapper.CourMapper;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.repositories.CourRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CourRespDTO> getAllCours(){

        List<Cour> cours = courRepo.findAll();

        return cours.stream()
                .map(courMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourRespDTO getCourById(Long courId){
        Cour cour = courRepo.findById(courId)
                .orElseThrow(()->new NoSuchElementException("cour non trouvee avec cet ID:" + courId));
        return courMapper.toDto(cour);
    }

    public Cour updateCour(Cour cour,Long courId){
        Optional<Cour> courOptional = courRepo.findById(courId);

        if (courOptional.isEmpty()){
            throw new EntityNotFoundException("cour not found!!");
        }

        if (cour.getNom()!=null)
            courOptional.get().setNom(cour.getNom());
        if (cour.getCoefficient()!=null)
            courOptional.get().setCoefficient(cour.getCoefficient());

        return this.courRepo.saveAndFlush(courOptional.get());
    }

    @Transactional(readOnly = true)
    public void softDelete(Long courId){
        Cour cour = courRepo.findInactiveById(courId)
                .orElseThrow(()->new NoSuchElementException("cour active non trouvee avec ID:" +courId));
        if (!cour.isActive()){
            return;
        }
        cour.setActive(false);
        courRepo.save(cour);
    }

    @Transactional(readOnly = true)
    public void reactive(Long courId){
        Cour cour = courRepo.findInactiveById(courId)
                .orElseThrow(()->new NoSuchElementException(""));

        if (cour.isActive()){
            throw new IllegalStateException("le cour avec ID" + courId + "est deja active");
        }

        cour.setActive(true);
        courRepo.save(cour);
    }


}
