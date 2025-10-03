package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends JpaRepository<Parent,Long> {
}
