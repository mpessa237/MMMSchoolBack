package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.CourReqDTO;
import com.example.MMMSchoolBack.dto.CourRespDTO;
import com.example.MMMSchoolBack.services.CourService;
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
@RequestMapping("/api/cours")
public class CourController {

    private final CourService courService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CourRespDTO> create(@RequestBody @Validated CourReqDTO courReqDTO){
        CourRespDTO createCour = courService.create(courReqDTO);
        return new ResponseEntity<>(createCour, HttpStatus.CREATED);
    }
}
