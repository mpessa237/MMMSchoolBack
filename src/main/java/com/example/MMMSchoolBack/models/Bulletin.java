package com.example.MMMSchoolBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bulletins")
public class Bulletin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bulletinId;
    private String appreciation;
    private Double moyenne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id")
    private Eleve eleve;
}
