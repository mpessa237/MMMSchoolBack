package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.ParentReqDTO;
import com.example.MMMSchoolBack.dto.ParentRespDTO;
import com.example.MMMSchoolBack.models.Parent;
import com.example.MMMSchoolBack.services.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping
    public ResponseEntity<List<ParentRespDTO>> getAllParents(){
        List<ParentRespDTO> parentRespDTOS = parentService.getAllParents();
        return ResponseEntity.ok(parentRespDTOS);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<ParentRespDTO> getParentById(@PathVariable Long parentId){
        return ResponseEntity.ok(this.parentService.getParentById(parentId));
    }

    @PatchMapping("/{parentId}")
    public ResponseEntity<Parent> update(@RequestBody Parent parent,@PathVariable Long parentId){
        return ResponseEntity.ok(this.parentService.updateParent(parent, parentId));
    }

    @DeleteMapping("/{parentId}")
    public ResponseEntity<Void> softDelete(@PathVariable Long parentId){
        try {

            parentService.softDelete(parentId);
            return ResponseEntity.notFound().build();
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{parentId}/reactivate")
    public ResponseEntity<Void> reactivateParent(@PathVariable Long parentId){
        try {
            parentService.reactiveParent(parentId);
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
