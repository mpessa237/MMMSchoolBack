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
@Table(name = "enseignants")
public class Enseignant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enseignantId;
    private String nom;
    private String prenom;
    private String adresse;
    private String specialite;
    private String telephone;

    @OneToMany(mappedBy = "enseignant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Cour> cours = new ArrayList<>();
}
