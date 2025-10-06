package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.ClasseReqDTO;
import com.example.MMMSchoolBack.dto.ClasseRespDTO;
import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.services.ClasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ClasseRespDTO> saveClasse(@RequestBody ClasseReqDTO classeDTO){
        ClasseRespDTO createClasse = classeService.creerClasse(classeDTO);
        return new ResponseEntity<>(createClasse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClasseRespDTO>> getAllClasses(){
        List<ClasseRespDTO> classes = classeService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/nom/{nom}")
    public ResponseEntity<ClasseRespDTO> getClasseByNom(@PathVariable String nom){
        return ResponseEntity.ok(this.classeService.getClasseByNom(nom));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{classeId}")
    public ResponseEntity<ClasseRespDTO> getClasse(@PathVariable Long classeId) {
        try {
            ClasseRespDTO classe = classeService.getClasseById(classeId);
            return ResponseEntity.ok(classe);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{classeId}")
    public ResponseEntity<Void> softDelete(@PathVariable Long classeId){
        try {
            classeService.softDeleteClasse(classeId);
            return ResponseEntity.notFound().build();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{classeId}/reactivate")
    public ResponseEntity<Void> reactivateClasse(@PathVariable Long classeId) {
        try {
            classeService.reactiveClasse(classeId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
