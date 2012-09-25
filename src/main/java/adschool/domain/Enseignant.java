package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Diplome;
import adschool.utils.NumberGenerator;

import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;

@RooJavaBean
@RooToString
@RooEntity(inheritanceType = "TABLE_PER_CLASS")
public class Enseignant extends Personne {

    private int volumeHoraire = 0;

    private int coutHoraire = 0;

    private int salaireBase = 0;

    @ManyToOne
    private Diplome diplome;
    
    @Override
    public void internalPostPersist() {
        matricule = NumberGenerator.getNumber("EN-", getId(), 6);
    }
}
