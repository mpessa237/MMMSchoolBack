package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.ClasseReqDTO;
import com.example.MMMSchoolBack.dto.ClasseRespDTO;
import com.example.MMMSchoolBack.mapper.ClasseMapper;
import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.repositories.ClasseRepo;
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
public class ClasseService {

    private final ClasseRepo classeRepo;
    private final ClasseMapper classeMapper;

    public ClasseRespDTO creerClasse(ClasseReqDTO classeDTO){
        Classe classe = classeMapper.toEntity(classeDTO);
        Classe savedClasse = classeRepo.save(classe);
        return classeMapper.toDto(savedClasse);

    }

    @Transactional(readOnly = true)
    public List<ClasseRespDTO> getAllClasses(){
        List<Classe> classes = classeRepo.findAll();

        return classes.stream()
                .map(classeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClasseRespDTO getClasseById(Long classeId){
        Classe classe = classeRepo.findById(classeId)
                .orElseThrow(()-> new NoSuchElementException("Classe non trouvée avec ID: " + classeId));
        return classeMapper.toDto(classe);
    }


    public Classe updateClasse(Classe classe,Long classeId){

        Optional<Classe> classeOptional = classeRepo.findById(classeId);

        if (classeOptional.isEmpty())
            throw new EntityNotFoundException("classe not found!!");
        if (classe.getNom()!=null)
            classeOptional.get().setNom(classe.getNom());
        if (classe.getCycle()!=null)
            classeOptional.get().setCycle(classe.getCycle());

        return this.classeRepo.saveAndFlush(classeOptional.get());
    }

    public void  delete(Long classeId){

        classeRepo.findById(classeId)
                .orElseThrow(()-> new EntityNotFoundException("class not found!"));
        classeRepo.deleteById(classeId);
    }

}
