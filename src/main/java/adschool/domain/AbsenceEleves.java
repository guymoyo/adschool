package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Evaluation;
import javax.persistence.ManyToOne;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import adschool.utils.NumberGenerator;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class AbsenceEleves extends SchoolBaseEntity{

    private String absenceKey;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private Inscription inscriptionEleve;

    @ManyToOne
    private Matiere matiere;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateAbsence;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateSaisie;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date heureDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date heureFin;

    private int duree;

    private Boolean justified;
    
    @PostPersist
    public void postPersit() {
        absenceKey = NumberGenerator.getNumber("AB-", getId(), 4);
    }
}
