package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepo extends JpaRepository<Classe,Long> {

    Optional<Classe> findByNom(String nom);
}
