package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnseignantRepo extends JpaRepository<Enseignant,Long> {

    Optional<Enseignant> findByNom(String nom);


    @Query(value = "SELECT * FROM enseignants WHERE enseignant_id = :enseignantId",
            nativeQuery = true) // <-- C'est l'élément clé !
    Optional<Enseignant> findInactiveById(@Param("enseignantId") Long enseignantId);

}
