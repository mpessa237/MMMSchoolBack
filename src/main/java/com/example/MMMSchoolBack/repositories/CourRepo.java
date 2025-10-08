package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourRepo extends JpaRepository<Cour,Long> {

    Optional<Cour> findCourByNom(String nom);
}
