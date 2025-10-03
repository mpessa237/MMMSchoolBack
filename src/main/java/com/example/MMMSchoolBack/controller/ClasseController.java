package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.ClasseReqDTO;
import com.example.MMMSchoolBack.dto.ClasseRespDTO;
import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.services.ClasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    @PostMapping
    public ResponseEntity<ClasseRespDTO> saveClasse(@RequestBody ClasseReqDTO classeDTO){
        ClasseRespDTO createClasse = classeService.creerClasse(classeDTO);
        return new ResponseEntity<>(createClasse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClasseRespDTO>> getAllClasses(){
        List<ClasseRespDTO> classes = classeService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{classeId}")
    public ResponseEntity<ClasseRespDTO> getClasse(@PathVariable Long classeId) {
        try {
            ClasseRespDTO classe = classeService.getClasseById(classeId);
            return ResponseEntity.ok(classe);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{classeId}")
    public ResponseEntity<Classe> updateClasse(@RequestBody Classe classe,@PathVariable Long classeId){
        classe.setClasseId(classeId);
        Classe updateClasse = classeService.updateClasse(classe, classeId);

        if (
                updateClasse==null
        ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateClasse);
    }

    @DeleteMapping("/{classeId}")
    public ResponseEntity<String> delete(@PathVariable Long classeId){

        classeService.delete(classeId);
        return ResponseEntity.ok("class deleted successfully!!");
    }

}
