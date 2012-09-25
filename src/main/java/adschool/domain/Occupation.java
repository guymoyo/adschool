package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Classe;
import javax.persistence.ManyToOne;
import adschool.domain.Salle;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Jours;
import adschool.utils.NumberGenerator;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findOccupationsBySalle", "findOccupationsByClasse" })
public class Occupation extends SchoolBaseEntity {

    private String occupationKey;

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private Salle salle;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:MM")
    private Date heureStart;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:MM")
    private Date heureStop;

    @Enumerated(EnumType.STRING)
    private Jours jour;

    @PostPersist
    public void postPersit() {
        occupationKey = NumberGenerator.getNumber("OC-", getId(), 4);
    }
    
    
    public static TypedQuery<Occupation> findOccupationByBatiment(Batiment batiment) {
    	
    	EntityManager em = Classe.entityManager();
        TypedQuery<Occupation> q = em.createQuery("SELECT o FROM Occupation AS o WHERE o.salle IN (Select s From Salle As s Where s.batiment =:batiment) Order By o.salle.nomsalle  ", Occupation.class);
        q.setParameter("batiment", batiment);
        return q;
    }
}
