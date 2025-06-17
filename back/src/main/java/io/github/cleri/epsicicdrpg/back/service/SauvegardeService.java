package io.github.cleri.epsicicdrpg.back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.cleri.epsicicdrpg.back.model.Sauvegarde;
import io.github.cleri.epsicicdrpg.back.repository.SauvegardeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SauvegardeService {

    @Autowired
    private final SauvegardeRepository sauvegardeRepository ;

    public int createSauvegarde(){
        Sauvegarde sauvegarde = sauvegardeRepository.save(new Sauvegarde());
        return sauvegarde.getId().intValue();
        
    }


    public Sauvegarde getSauvegardeById(Long id) {
        return sauvegardeRepository.findById(id).orElse(null);
    }
    

    public void updateSauvegardeById(Long id, int pv, int nbSalle) {
        Sauvegarde sauvegarde = sauvegardeRepository.findById(id).orElse(null);
        if (sauvegarde != null) {
            sauvegarde.setPv(pv);
            sauvegarde.setNbSalle(nbSalle);
            sauvegardeRepository.save(sauvegarde);
        }
    }
}

