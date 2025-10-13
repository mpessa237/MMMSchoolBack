package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourRepo extends JpaRepository<Cour,Long> {

    Optional<Cour> findCourByNom(String nom);

    @Query(value = "SELECT * FROM cours WHERE cour_id = :courId",
    nativeQuery = true)
    Optional<Cour> findInactiveById(@Param("courId") Long courId);
}
