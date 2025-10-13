package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.services.CourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cours")
public class CourController {

    private final CourService courService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CourRespDTO> create(@RequestBody @Validated CourReqDTO courReqDTO){
        CourRespDTO createCour = courService.create(courReqDTO);
        return new ResponseEntity<>(createCour, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourRespDTO>> getAllCours(){
        List<CourRespDTO> courRespDTOList = courService.getAllCours();
        return ResponseEntity.ok(courRespDTOList);
    }

    @PutMapping("/{courId}")
    public ResponseEntity<Cour> update(@RequestBody Cour cour,@PathVariable Long courId){
        return ResponseEntity.ok(this.courService.updateCour(cour, courId));
    }
    @GetMapping("/{courId}")
    public ResponseEntity<CourRespDTO> getCourById(@PathVariable Long courId){
        return ResponseEntity.ok(this.courService.getCourById(courId));
    }

    @DeleteMapping("/{courId}")
    public ResponseEntity<Void> softDelete(@PathVariable Long courId){
        try {
            courService.softDelete(courId);
            return ResponseEntity.notFound().build();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{courId}/reactivate")
    public ResponseEntity<Void> reactiveCour(@PathVariable Long courId){
        try {
            courService.reactive(courId);
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
