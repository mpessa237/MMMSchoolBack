package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepo extends JpaRepository<Classe,Long> {

    Optional<Classe> findByNom(String nom);

    @Query(value = "SELECT * FROM classes WHERE classe_id = :classeId",
            nativeQuery = true) // <-- C'est l'élément clé !
    Optional<Classe> findInactiveById(@Param("classeId") Long classeId);

    @Query(value = "SELECT c FROM Classe c")
    List<Classe> findAllIncludingInactive();
}
