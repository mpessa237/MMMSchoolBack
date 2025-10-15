package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.mapper.ParentMapper;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.models.Parent;
import com.example.MMMSchoolBack.repositories.EleveRepo;
import com.example.MMMSchoolBack.repositories.ParentRepo;
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
public class ParentService {

    private final ParentRepo parentRepo;
    private final EleveRepo eleveRepo;
    private final ParentMapper parentMapper;

    @Transactional
    public ParentRespDTO create(ParentReqDTO parentReqDTO){

        Parent parent = parentMapper.toEntity(parentReqDTO);

        parent.setActive(true);

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

    public List<ParentRespDTO> getAllParents(){
        List<Parent> parents = parentRepo.findAll();

        return parents.stream()
                .map(parentMapper::toDto)
                .collect(Collectors.toList());
    }

    public ParentRespDTO getParentById(Long parentId){
        Parent parent = parentRepo.findById(parentId)
                .orElseThrow(()->new EntityNotFoundException("parent not found!!"));
        return parentMapper.toDto(parent);
    }

    public Parent updateParent(Parent parent,Long parentId){
        Optional<Parent> parentOptional = parentRepo.findById(parentId);

        if (parentOptional.isEmpty()){
            throw new EntityNotFoundException("parent not found!!");
        }
        if (parent.getNom()!=null)
            parentOptional.get().setNom(parent.getNom());
        if (parent.getPrenom()!=null)
            parentOptional.get().setPrenom(parent.getPrenom());
        if (parent.getAdresse()!=null)
            parentOptional.get().setAdresse(parent.getAdresse());
        if (parent.getTelephone()!=null)
            parentOptional.get().setTelephone(parent.getTelephone());
        if (parent.getProfession()!=null)
            parentOptional.get().setProfession(parent.getProfession());
        if (parent.getEmail()!=null)
            parentOptional.get().setEmail(parent.getEmail());

        return this.parentRepo.saveAndFlush(parentOptional.get());
    }

    @Transactional
    public void  softDelete(Long parentId){
        Parent parent = parentRepo.findById(parentId)
                .orElseThrow(()-> new NoSuchElementException("Parent non trouvé avec l'ID: " + parentId));
        if (!parent.isActive()){
            throw new IllegalStateException("Le parent est déjà inactif.");
        }
        parent.setActive(false);
        parentRepo.save(parent);
    }

    @Transactional
    public void reactiveParent(Long parentId){
        Parent parent = parentRepo.findById(parentId)
                .orElseThrow(()-> new NoSuchElementException("parent not found!!"));
        if (parent.isActive()){
            throw new IllegalStateException("le parent avec ID" + parentId + "est deja active");
        }

        parent.setActive(true);
        parentRepo.save(parent);
    }

}
