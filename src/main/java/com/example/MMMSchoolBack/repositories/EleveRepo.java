package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepo extends JpaRepository<Eleve,Long> {

    Optional<Eleve> findEleveByNom(String nom);


    //methode pour le soft delete
    @Query(value = "SELECT * FROM eleves WHERE eleve_id = :eleveId",
    nativeQuery = true)
    Optional<Eleve> findInactiveEleveById(@Param("eleveId")Long eleveId);

    //methode pour le matricule
    @Query(value = "SELECT MAX(CAST(SUBSTR(e.matricule, 6) AS INTEGER)) " + // <-- SUBSTR au lieu de SUBSTRING
            "FROM Eleve e " +
            "WHERE SUBSTR(e.matricule, 1, 4) = :annee") // <-- SUBSTR au lieu de SUBSTRING
    Integer findLastSequenceNumberByAnnee(@Param("annee") String annee);
}
