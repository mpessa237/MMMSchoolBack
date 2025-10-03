package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepo extends JpaRepository<Eleve,Long> {

    Optional<Eleve> findEleveByNom(String nom);
}
