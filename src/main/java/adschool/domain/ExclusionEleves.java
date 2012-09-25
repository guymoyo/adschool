package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import adschool.domain.Inscription;
import adschool.utils.NumberGenerator;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class ExclusionEleves extends SchoolBaseEntity{

    private String exclusionKey;

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Inscription inscriptionEleve;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datedebut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datefin = new Date();

    private Boolean actif = true;

    private int duree = 0;
    
    @PostPersist
    public void postPersit() {
        exclusionKey = NumberGenerator.getNumber("EX-", getId(), 4);
    }
}
