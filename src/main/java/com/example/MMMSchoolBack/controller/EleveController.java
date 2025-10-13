package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.EleveReqDTO;
import com.example.MMMSchoolBack.dto.EleveRespDTO;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.services.EleveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eleves")
public class EleveController {

    private final EleveService eleveService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<EleveRespDTO> saveEleve(@RequestBody EleveReqDTO eleveReqDTO){

        try {
            EleveRespDTO nouvelEleve = eleveService.creerEleve(eleveReqDTO);
            return new ResponseEntity<>(nouvelEleve, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{nom}")
    public ResponseEntity<EleveRespDTO> findEleveByNom(@PathVariable String nom){
        return ResponseEntity.ok(this.eleveService.findEleveByNom(nom));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EleveRespDTO>> getAllEleves(){
        List<EleveRespDTO> eleveRespDTOS = eleveService.getAllEleves();
        return ResponseEntity.ok(eleveRespDTOS);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{eleveId}")
    public ResponseEntity<Eleve> update(@RequestBody Eleve eleve,@PathVariable Long eleveId){
        return ResponseEntity.ok(this.eleveService.updateEleve(eleve, eleveId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{eleveId}")
    public ResponseEntity<Void> softDelete(@PathVariable Long eleveId){
        try {
            eleveService.softDeleteEleve(eleveId);
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
    @PutMapping("/{eleveId}/reactivate")
    public ResponseEntity<Void> reactive(@PathVariable Long eleveId){

        try {
            eleveService.reactive(eleveId);
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalStateException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
