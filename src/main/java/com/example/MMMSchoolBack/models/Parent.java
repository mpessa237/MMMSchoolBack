package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parents")
public class Parent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String profession;
    private String adresse;

    private boolean active = true;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Eleve> eleves = new ArrayList<>();
}
