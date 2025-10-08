package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.EnseignantReqDTO;
import com.example.MMMSchoolBack.dto.EnseignantRespDTO;
import com.example.MMMSchoolBack.services.EnseignantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
