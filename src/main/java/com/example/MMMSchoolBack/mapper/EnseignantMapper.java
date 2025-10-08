package com.example.MMMSchoolBack.mapper;

import com.example.MMMSchoolBack.dto.EnseignantReqDTO;
import com.example.MMMSchoolBack.dto.EnseignantRespDTO;
import com.example.MMMSchoolBack.models.Enseignant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnseignantMapper {

    private final CourMapper courMapper;

    public Enseignant toEntity(EnseignantReqDTO enseignantReqDTO){
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(enseignantReqDTO.getNom());
        enseignant.setPrenom(enseignantReqDTO.getPrenom());
        enseignant.setAdresse(enseignantReqDTO.getAdresse());
        enseignant.setTelephone(enseignantReqDTO.getTelephone());

        return enseignant;
    }

    public EnseignantRespDTO toDto(Enseignant enseignant){
        EnseignantRespDTO enseignantRespDTO = new EnseignantRespDTO();
        enseignantRespDTO.setEnseignantId(enseignant.getEnseignantId());
        enseignantRespDTO.setNom(enseignant.getNom());
        enseignantRespDTO.setPrenom(enseignant.getPrenom());
        enseignantRespDTO.setAdresse(enseignant.getAdresse());
        enseignantRespDTO.setTelephone(enseignant.getTelephone());

        // CORRECTION : Mappage des cours
        if (enseignant.getCours() != null) {
            enseignantRespDTO.setCours(
                    enseignant.getCours().stream()
                            .map(courMapper::toDto) // Utilisez le CourMapper injecté
                            .collect(Collectors.toList())
            );
        }

        return enseignantRespDTO;
    }

    public EnseignantRespDTO toSimple(Enseignant enseignant){
        if (enseignant == null){
            return null;
        }
        EnseignantRespDTO enseignantRespDTO = new EnseignantRespDTO();
        enseignantRespDTO.setEnseignantId(enseignant.getEnseignantId());
        enseignantRespDTO.setNom(enseignant.getNom());

        return enseignantRespDTO;
    }
}
