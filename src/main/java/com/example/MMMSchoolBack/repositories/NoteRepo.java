package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
}
