package com.example.MMMSchoolBack.services;

import com.example.MMMSchoolBack.dto.NoteReqDTO;
import com.example.MMMSchoolBack.dto.NoteRespDTO;
import com.example.MMMSchoolBack.mapper.CourMapper;
import com.example.MMMSchoolBack.mapper.EleveMapper;
import com.example.MMMSchoolBack.mapper.NoteMapper;
import com.example.MMMSchoolBack.models.Cour;
import com.example.MMMSchoolBack.models.Eleve;
import com.example.MMMSchoolBack.models.Note;
import com.example.MMMSchoolBack.repositories.CourRepo;
import com.example.MMMSchoolBack.repositories.EleveRepo;
import com.example.MMMSchoolBack.repositories.NoteRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final CourRepo courRepo;
    private final EleveRepo eleveRepo;
    private final NoteRepo noteRepo;
    private final NoteMapper noteMapper;


    @Transactional
    public NoteRespDTO create(NoteReqDTO noteReqDTO){

        Eleve eleve = eleveRepo.findById(noteReqDTO.getEleveId())
                .orElseThrow(()->new NoSuchElementException(
                        "Impossible de créer la note : Élève non trouvé avec ID " + noteReqDTO.getEleveId())
                );

        Cour cour = courRepo.findById(noteReqDTO.getCourId())
                .orElseThrow(()-> new NoSuchElementException( "Impossible de créer la note : Cours non trouvé avec ID " + noteReqDTO.getCourId()));

        /* 3. Validation de l'existence de l'Enseignant (Optionnel mais recommandé)
        Enseignant enseignant = null;
        if (noteReqDTO.getEnseignantId() != null) {
            enseignant = enseignantRepo.findById(noteReqDTO.getEnseignantId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "Impossible de créer la note : Enseignant non trouvé avec ID " + noteReqDTO.getEnseignantId()));
        }*/

        Note note = noteMapper.toEntity(noteReqDTO);

        note.setEleve(eleve);
        note.setCour(cour);

        Note savedNote = noteRepo.save(note);

        return noteMapper.toDto(savedNote);
    }

    @Transactional(readOnly = true)
    public List<NoteRespDTO> getNotesByEleveId(Long eleveId){
        if (!eleveRepo.existsById(eleveId)){
            throw new NoSuchElementException("Aucun élève trouvé avec l'ID : " + eleveId);
        }

        List<Note> notes = noteRepo.findByEleveEleveId(eleveId);

        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    public Note updateNote(Note note,Long noteId){
        Optional<Note> noteOptional = noteRepo.findById(noteId);

        if (noteOptional.isEmpty()){
            throw new EntityNotFoundException("note not found!!");
        }

        if (note.getValeur()!=null)
            noteOptional.get().setValeur(note.getValeur());
        if (note.getAppreciation()!=null)
            noteOptional.get().setAppreciation(note.getAppreciation());

        return this.noteRepo.saveAndFlush(noteOptional.get());

    }

    public void delete(Long noteId){
        noteRepo.findById(noteId)
                .orElseThrow(()->new EntityNotFoundException("note not found!!"));
        noteRepo.deleteById(noteId);
    }


}
