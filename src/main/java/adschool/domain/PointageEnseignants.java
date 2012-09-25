package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import adschool.domain.Enseignant;
import adschool.domain.Matiere;
import adschool.domain.Classe;
import adschool.utils.NumberGenerator;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class PointageEnseignants extends SchoolBaseEntity {

    private String pointageKey;

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Matiere matiere;

    @ManyToOne
    private Classe classe;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datedebut;

    private int duree;
    
    @PostPersist
    public void postPersit() {
        pointageKey = NumberGenerator.getNumber("POE-", getId(), 6);
    }
}
