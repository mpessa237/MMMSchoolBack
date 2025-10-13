package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cours")
public class Cour {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courId;
    private String nom;
    private Double coefficient;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @OneToMany(mappedBy = "cour",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();


    @ManyToMany(mappedBy = "cours")
    private Set<Classe> classes = new HashSet<>();

}
