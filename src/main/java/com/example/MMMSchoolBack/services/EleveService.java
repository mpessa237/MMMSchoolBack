package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.EleveReqDTO;
import com.example.MMMSchoolBack.dto.EleveRespDTO;
import com.example.MMMSchoolBack.mapper.EleveMapper;
import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.models.Parent;
import com.example.MMMSchoolBack.repositories.ClasseRepo;
import com.example.MMMSchoolBack.repositories.EleveRepo;
import com.example.MMMSchoolBack.repositories.ParentRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EleveService {
    private final EleveRepo eleveRepo;
    private final ClasseRepo classeRepo;
    private final ParentRepo parentRepo;
    private final EleveMapper eleveMapper;

    private static final AtomicLong matriculeCounter = new AtomicLong(0);



    @Transactional
    public EleveRespDTO creerEleve(EleveReqDTO eleveReqDTO) {
        Eleve eleve = eleveMapper.toEntity(eleveReqDTO);

        String nouveauMatricule = generateUniqueMatricule();
        eleve.setMatricule(nouveauMatricule);

        Classe classe = classeRepo.findById(eleveReqDTO.getClasseId())
                .orElseThrow(() -> new NoSuchElementException("Classe non trouvée avec ID: " + eleveReqDTO.getClasseId()));
        eleve.setClasse(classe);

        if (eleveReqDTO.getParentId() != null) {
            Optional<Parent> parentOptional = parentRepo.findById(eleveReqDTO.getParentId());
            if (parentOptional.isPresent()) {
                eleve.setParent(parentOptional.get());
            } else {
                throw new NoSuchElementException("Parent non trouvé avec ID: " + eleveReqDTO.getParentId());
            }
        }

        Eleve savedEleve = eleveRepo.save(eleve);
        return eleveMapper.toDto(savedEleve);
    }

    private String generateUniqueMatricule() {

        String annee = String.valueOf(Year.now());
        long nextId = matriculeCounter.incrementAndGet();
        String numeroSequence = String.format("%04d", nextId);
        return annee + "-" + numeroSequence;
    }

    public EleveRespDTO findEleveByNom(String nom){
        Eleve eleve = eleveRepo.findEleveByNom(nom)
                .orElseThrow(()-> new EntityNotFoundException("eleve not found!!"));
        return eleveMapper.toDto(eleve);

    }

    public List<EleveRespDTO> getAllEleves(){

        List<Eleve> eleves = eleveRepo.findAll();

        return eleves.stream()
                .map(eleveMapper::toDto)
                .collect(Collectors.toList());
    }




}
