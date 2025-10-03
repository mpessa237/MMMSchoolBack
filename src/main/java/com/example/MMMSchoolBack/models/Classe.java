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
@Table(name = "classes")
public class Classe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classeId;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Cycle cycle;

    @OneToMany(mappedBy = "classe",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Eleve> eleves = new ArrayList<>();
}
