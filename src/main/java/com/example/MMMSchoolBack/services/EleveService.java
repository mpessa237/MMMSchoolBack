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
import org.springframework.transaction.annotation.Propagation;

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

    @Transactional
    public synchronized String generateUniqueMatricule() {

        String annee = String.valueOf(Year.now());

        // 1. Récupérer le dernier numéro de séquence pour l'année en cours
        Integer lastSequence = eleveRepo.findLastSequenceNumberByAnnee(annee);

        // 2. Calculer le prochain ID : si le résultat est null (nouvelle année ou première entrée), on commence à 1.
        long nextId = (lastSequence != null ? lastSequence.longValue() : 0) + 1;

        // 3. Formatter le numéro séquentiel
        String numeroSequence = String.format("%04d", nextId); // Ex: 0001, 0010, 0123

        // 4. Combiner et retourner
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

    public Eleve updateEleve(Eleve eleve,Long eleveId){

        Optional<Eleve> eleveOptional = eleveRepo.findById(eleveId);

        if (eleveOptional.isEmpty())
            throw new EntityNotFoundException("student not found!!");
        if (eleve.getNom()!=null)
            eleveOptional.get().setNom(eleve.getNom());
        if (eleve.getPrenom()!=null)
            eleveOptional.get().setPrenom(eleve.getPrenom());
        if (eleve.getDateDeNaissance()!=null)
            eleveOptional.get().setDateDeNaissance(eleve.getDateDeNaissance());
        if (eleve.getGenre()!=null)
            eleveOptional.get().setGenre(eleve.getGenre());

        return this.eleveRepo.saveAndFlush(eleveOptional.get());

    }

    @Transactional
    public void softDeleteEleve(Long eleveId){
        Eleve eleve = eleveRepo.findInactiveEleveById(eleveId)
                .orElseThrow(()-> new NoSuchElementException("eleve actif non trouve avec cet ID:" +eleveId));

        if (!eleve.isActive()){
            return;
        }

        eleve.setActive(false);
        eleveRepo.save(eleve);
    }

    @Transactional
    public void reactive(Long eleveId){
        Eleve eleve = eleveRepo.findInactiveEleveById(eleveId)
                .orElseThrow(()-> new NoSuchElementException("classe desactivee non trouvee avec ID:" +eleveId));
        if (eleve.isActive()){
            return;
        }

        eleve.setActive(true);
        eleveRepo.save(eleve);
    }



}
