package adschool.domain;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import java.util.List;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.utils.NumberGenerator;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findAnneeScolairesByActif" })
public class AnneeScolaire extends SchoolBaseEntity {

    private String anneeKey;

    private String libelle;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datedebut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datefin;

    private String objectif;

    private Boolean actif = false;

    @PostPersist
    public void postPersit() {
        anneeKey = NumberGenerator.getNumber("AN-", getId(), 4);
    }

    public static AnneeScolaire getCurrentAnneeScolaire() {
        TypedQuery<AnneeScolaire> t = findAnneeScolairesByActif(true);
        List<AnneeScolaire> l = t.getResultList();
        if (l != null && !l.isEmpty()) {
            return l.get(0);
        } else {
            return null;
        }
    }
    
    public static void initSchoolYears(){
    	long count = AnneeScolaire.countAnneeScolaires();
    	if (count < 1) {
    		AnneeScolaire year = new AnneeScolaire();
        	year.setActif(true);
        	year.setLibelle("2012-2013");
        	year.setDatedebut(new Date());
        	year.setDatefin(DateUtils.addYears(new Date(), 1));
        	year.persist();
		}
    	
    }

    @Transient
    private String state = "";


    @PostLoad
    public void postLoad() {
        state = getState();
    }

    public String getState() {
        return actif ? "En cours" : "Cloturé";
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
       
        sb.append(this.getLibelle());
        return sb.toString();
    }
    
    
    public void cloture(){
    	
    	List<Periode> findPeriodesByAnnee = Periode.findPeriodesByAnnee(this).getResultList();
    	
    	for (Periode periode : findPeriodesByAnnee) {
			
    		periode.cloturer();
		}
    	
    	setActif(false);
    	merge();
    	
    }
}
