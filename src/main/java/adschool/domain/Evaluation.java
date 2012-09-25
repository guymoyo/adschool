package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import java.util.List;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Periode;
import adschool.utils.NumberGenerator;
import javax.persistence.ManyToOne;
import adschool.domain.Etablissement;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findEvaluationsByActif", "findEvaluationsByPeriode" })
public class Evaluation extends SchoolBaseEntity {

    private String evaluationKey;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datedebut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datefin;

    @ManyToOne
    private Periode periode;

    private Boolean actif = true;

    private int pourcentage = 0;

    private String libelle = "";

    @ManyToOne
    private Etablissement etablissement;

    @PostPersist
    public void postPersit() {
        evaluationKey = NumberGenerator.getNumber("EV-", getId(), 4);
    }

    public static Evaluation getCurrentEvaluation() {
        TypedQuery<Evaluation> t = findEvaluationsByActif(true);
        List<Evaluation> l = t.getResultList();
        if (l != null && !l.isEmpty()) {
            return l.get(0);
        } else {
            return null;
        }
    }
    
    
    public void cloture(){
    	
    	
    	if ( getActif()){
		 	
	 		 Moyenneevaluations.calculeMoyenne(this);
	 		 
	 		 Moyenneegenerales.calculeMoyenne(this);
	 		
		     setActif(false);
		        
		     merge();
			
	 	}
    }
}
