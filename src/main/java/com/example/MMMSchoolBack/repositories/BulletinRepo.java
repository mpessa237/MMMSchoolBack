package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinRepo extends JpaRepository<Bulletin,Long> {
}
