package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "active = true")
@Entity
@Table(name = "classes")
public class Classe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classeId;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Cycle cycle;

    // pour le soft delete
    private boolean active = true;

    @OneToMany(mappedBy = "classe",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Eleve> eleves = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "classe_cours", // Table de jointure
            joinColumns = @JoinColumn(name = "classe_id"),
            inverseJoinColumns = @JoinColumn(name = "cour_id")
    )
    private Set<Cour> cours = new HashSet<>();
}
