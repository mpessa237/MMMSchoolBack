package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.services.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<ParentRespDTO> save(@RequestBody @Validated ParentReqDTO parentReqDTO){

        ParentRespDTO createParent = parentService.create(parentReqDTO);
        return new ResponseEntity<>(createParent,HttpStatus.CREATED);
    }
}
