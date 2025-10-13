package com.example.MMMSchoolBack.controller;

import com.example.MMMSchoolBack.dto.NoteReqDTO;
import com.example.MMMSchoolBack.dto.NoteRespDTO;
import com.example.MMMSchoolBack.models.Note;
import com.example.MMMSchoolBack.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<NoteRespDTO> create(@RequestBody NoteReqDTO noteReqDTO){
        NoteRespDTO noteRespDTO = noteService.create(noteReqDTO);
        return new ResponseEntity<>(noteRespDTO,HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/eleve/{eleveId}")
    public ResponseEntity<List<NoteRespDTO>> getNotesByEleve(@PathVariable Long eleveId){
        try {
            List<NoteRespDTO> notes = noteService.getNotesByEleveId(eleveId);
            return ResponseEntity.ok(notes);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<Note> update(@RequestBody Note note,@PathVariable Long noteId){
        return ResponseEntity.ok(this.noteService.updateNote(note, noteId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> delete(@PathVariable Long noteId){
        return ResponseEntity.ok().body("note deleted successfully!!");
    }
}
