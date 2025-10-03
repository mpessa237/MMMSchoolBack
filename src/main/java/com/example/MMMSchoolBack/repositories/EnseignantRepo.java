package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepo extends JpaRepository<Enseignant,Long> {
}
