package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.NoteReqDTO;
import com.example.MMMSchoolBack.dto.NoteRespDTO;
import com.example.MMMSchoolBack.models.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    private final EleveMapper eleveMapper;
    private final CourMapper courMapper;

    public NoteMapper(EleveMapper eleveMapper, CourMapper courMapper) {
        this.eleveMapper = eleveMapper;
        this.courMapper = courMapper;
    }

    public Note toEntity(NoteReqDTO noteReqDTO){
        Note note = new Note();
        note.setValeur(noteReqDTO.getValeur());
        note.setAppreciation(noteReqDTO.getAppreciation());

        return note;
    }

    public NoteRespDTO toDto(Note note){
        NoteRespDTO noteRespDTO = new NoteRespDTO();
        noteRespDTO.setNoteId(note.getNoteId());
        noteRespDTO.setValeur(note.getValeur());
        noteRespDTO.setAppreciation(note.getAppreciation());

        if (note.getEleve()!=null){
            noteRespDTO.setEleve(eleveMapper.toSimpleDto(note.getEleve()));
        }

        if (note.getCour()!=null){
            noteRespDTO.setCour(courMapper.toSimpleDTO(note.getCour()));
        }

        return noteRespDTO;

    }



}
