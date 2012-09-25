package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import java.util.List;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.AnneeScolaire;
import adschool.utils.NumberGenerator;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findPeriodesByAnnee" })
public class Periode extends SchoolBaseEntity {

    private String periodeKey;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datedebut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datefin;

    @ManyToOne
    private AnneeScolaire annee;

    private Boolean actif;

    private String libelle;

    @PostPersist
    public void postPersit() {
        periodeKey = NumberGenerator.getNumber("PE-", getId(), 4);
    }

    public void cloturer() {
        setActif(false);
        List<Evaluation> findEvaluationsByPeriode = Evaluation.findEvaluationsByPeriode(this).getResultList();
        for (Evaluation evaluation : findEvaluationsByPeriode) {
            evaluation.setActif(false);
            evaluation.merge();
        }
        merge();
    }
}
