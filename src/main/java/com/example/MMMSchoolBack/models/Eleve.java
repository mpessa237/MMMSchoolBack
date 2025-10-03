package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eleves")
public class Eleve {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eleveId;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String matricule;
    @Enumerated(EnumType.STRING)
    private Genre genre;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @OneToMany(mappedBy = "eleve",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Bulletin> bulletins = new ArrayList<>();

    @OneToMany(mappedBy = "eleve",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
