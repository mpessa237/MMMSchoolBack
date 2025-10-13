package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.EnseignantReqDTO;
import com.example.MMMSchoolBack.dto.EnseignantRespDTO;
import com.example.MMMSchoolBack.mapper.EnseignantMapper;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.models.Enseignant;
import com.example.MMMSchoolBack.repositories.CourRepo;
import com.example.MMMSchoolBack.repositories.EnseignantRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<EnseignantRespDTO> getAllEnseignants(){
        List<Enseignant> enseignants = enseignantRepo.findAll();

        return enseignants.stream()
                .map(enseignantMapper::toDto)
                .collect(Collectors.toList());
    }

    public Enseignant updateEnseignant(Enseignant enseignant,Long enseignantId){

        Optional<Enseignant> enseignantOptional = enseignantRepo.findById(enseignantId);

        if (enseignantOptional.isEmpty()){
            throw new EntityNotFoundException("enseignant not found!");
        }

        if (enseignant.getNom()!=null)
            enseignantOptional.get().setNom(enseignant.getNom());
        if (enseignant.getPrenom()!=null)
            enseignantOptional.get().setPrenom(enseignant.getPrenom());
        if (enseignant.getAdresse()!=null)
            enseignantOptional.get().setAdresse(enseignant.getAdresse());
        if (enseignant.getTelephone()!=null)
            enseignantOptional.get().setTelephone(enseignant.getTelephone());

        return this.enseignantRepo.saveAndFlush(enseignantOptional.get());
    }

    @Transactional(readOnly = true)
    public EnseignantRespDTO getEnseignantById(Long enseignantId){
        Enseignant enseignant = enseignantRepo.findById(enseignantId)
                .orElseThrow(()-> new NoSuchElementException("Enseignant non trouvée avec ID: " + enseignantId));

        return enseignantMapper.toDto(enseignant);
    }


    @Transactional
    public void softDelete(Long enseignantId){
        Enseignant enseignant = enseignantRepo.findInactiveById(enseignantId)
                .orElseThrow(() ->new NoSuchElementException("enseignant active non trouvee avec ID:" + enseignantId));

        if (!enseignant.isActive()){
            return;
        }
        enseignant.setActive(false);
        enseignantRepo.save(enseignant);
    }

    @Transactional
    public void reactiveEnseignant(Long enseignantId){
        Enseignant enseignant = enseignantRepo.findInactiveById(enseignantId)
                .orElseThrow(()->new NoSuchElementException("enseignant desactivee non trouvee avec ID:" +enseignantId));

        if (enseignant.isActive()){
            throw new IllegalStateException("la enseignant avec ID" + enseignantId + "est deja active");
        }
        enseignant.setActive(true);
        enseignantRepo.save(enseignant);
    }
}
