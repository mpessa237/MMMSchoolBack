package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRepo extends JpaRepository<Cour,Long> {
}
