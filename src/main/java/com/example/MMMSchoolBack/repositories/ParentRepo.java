package com.example.MMMSchoolBack.repositories;

import com.example.MMMSchoolBack.models.Classe;
import com.example.MMMSchoolBack.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepo extends JpaRepository<Parent,Long> {

    @Query(value = "SELECT * FROM parents WHERE parent_id = :parentId",
            nativeQuery = true) // <-- C'est l'élément clé !
    Optional<Parent> findInactiveById(@Param("parentId") Long parentId);

}
