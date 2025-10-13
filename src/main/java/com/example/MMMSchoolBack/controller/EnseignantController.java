package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.EnseignantReqDTO;
import com.example.MMMSchoolBack.dto.EnseignantRespDTO;
import com.example.MMMSchoolBack.models.Enseignant;
import com.example.MMMSchoolBack.services.EnseignantService;
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
@RequestMapping("/api/enseignants")
public class EnseignantController {
    private final EnseignantService enseignantService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<EnseignantRespDTO> create(@RequestBody @Validated EnseignantReqDTO enseignantReqDTO){
        EnseignantRespDTO createenseignant = enseignantService.create(enseignantReqDTO);
        return new ResponseEntity<>(createenseignant, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EnseignantRespDTO>> getAllEnseignants(){
        List<EnseignantRespDTO> enseignantRespDTOS = enseignantService.getAllEnseignants();
        return ResponseEntity.ok(enseignantRespDTOS);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{enseignantId}")
    public ResponseEntity<Enseignant> update(@RequestBody Enseignant enseignant,@PathVariable Long enseignantId){
        return ResponseEntity.ok(this.enseignantService.updateEnseignant(enseignant, enseignantId));
    }

    @DeleteMapping("/{enseignantId}")
    public ResponseEntity<Void> softDelete(@PathVariable Long enseignantId){
        try {
            enseignantService.softDelete(enseignantId);
            return ResponseEntity.notFound().build();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{enseignantId}/reactivate")
    public ResponseEntity<Void> reactive(@PathVariable Long enseignantId){
        try {
            enseignantService.reactiveEnseignant(enseignantId);
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
