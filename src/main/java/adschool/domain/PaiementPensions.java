package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.PensionEleves;
import javax.persistence.ManyToOne;
import adschool.domain.Inscription;
import adschool.utils.NumberGenerator;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findPaiementPensionsesByDateVersementBetween" })
public class PaiementPensions extends SchoolBaseEntity {

    private String paiementKey;

    @ManyToOne
    private PensionEleves pension;

    @ManyToOne
    private Inscription inscriptionEleve;

    private int montantVersement = 0;

    private int montantTranche = 0;
    
    private boolean imprimer = false;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateVersement = new Date();

    @PostPersist
    public void postPersit() {
        paiementKey = NumberGenerator.getNumber("PY-", getId(), 4);
    }

    public static PaiementPensions PayerPension(PensionEleves pension, int montant) {
        PaiementPensions versement = new PaiementPensions();
        try {
            if (montant >= pension.getNetAPayer()) {
                versement.setMontantVersement((int) pension.getNetAPayer());
                versement.setMontantTranche((int) pension.getNetAPayer());
                pension.avancer((int) pension.getNetAPayer());
                pension.setSolder(true);
            } else if (montant < pension.getNetAPayer()) {
                versement.setMontantTranche((int) pension.getNetAPayer());
                pension.avancer(montant);
                versement.setMontantTranche(montant);
                if (pension.getNetAPayer() == 0) {
                    pension.setSolder(true);
                }
            }
            pension.merge();
            versement.merge();
            versement.setPension(pension);
            versement.setInscriptionEleve(pension.getInscriptionEleve());
            versement.persist();
        } catch (Exception e) {
        }
        return versement;
    }

    public static TypedQuery<PaiementPensions> getPaiementOnePension(PensionEleves pension) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<PaiementPensions> q = em.createQuery("SELECT o FROM PaiementPensions AS o WHERE o.pension= :pension", PaiementPensions.class);
        q.setParameter("pension", pension);
        return q;
    }
    
    
    public static TypedQuery<PaiementPensions> getPaiementEleve(Eleve eleve) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<PaiementPensions> q = em.createQuery("SELECT o FROM PaiementPensions AS o WHERE o.pension IN (SELECT p FROM PensionEleves AS p Where p.eleve = :eleve) ", PaiementPensions.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
    
    public static TypedQuery<PaiementPensions> getPaiementClasse(AnneeScolaire annee, Classe classe) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<PaiementPensions> q = em.createQuery("SELECT p FROM PaiementPensions AS p WHERE p.inscriptionEleve IN (SELECT i FROM Inscription AS i Where i.classe = :classe And i.annee =:annee) ", PaiementPensions.class);
        q.setParameter("classe", classe);
        q.setParameter("annee", annee);
        return q;
    }
    
    
    
}
