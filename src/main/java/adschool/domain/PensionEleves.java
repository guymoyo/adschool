package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Inscription;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.utils.NumberGenerator;
import adschool.domain.Regime;
import adschool.domain.Eleve;

import adschool.domain.PensionEleves;
import java.lang.String;


@RooJavaBean
@RooToString
@RooEntity(finders = { "findPensionElevesesByPensionKeyLike", "findPensionElevesesByEleve" })
public class PensionEleves extends SchoolBaseEntity {

    private String pensionKey;

    @ManyToOne
    private Inscription inscriptionEleve;

    private int tranche = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateLimitePaiement;

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Classe classe;

    private int montant = 0;

    private int reduction = 0;

    private int avance = 0;

    private Boolean solder = false;

    @ManyToOne
    private Regime regime;

    @ManyToOne
    private Eleve eleve;
    
    @Transient
    private int netAPayer = 0;
    
    @PostLoad
    public void postLoad(){
    	
    	netAPayer = getNetAPayer();
    }

    @PostPersist
    public void postPersit() {
        pensionKey = NumberGenerator.getNumber("PS-", getId(), 6);
    }

    public int getMontantPension() {
        return montant - reduction;
    }

    public int getNetAPayer() {
        return solder ? 0 : (montant - reduction - avance) > 0 ? (montant - reduction - avance) : 0;
    }

    public void avancer(int av) {
        avance += av;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPensionKey()).append(", ");
        sb.append(getMontantPension()).append(", ");
        sb.append(getTranche()).append(", ");
        sb.append(getEleve());
        
        return sb.toString();
    }
    
    
    
   
    
    public static TypedQuery<PensionEleves> findPensionElevesesByEleveNom(String eleve) {
        if (eleve == null || eleve.length() == 0) throw new IllegalArgumentException("The eleve argument is required");
        eleve = eleve.replace('*', '%');
        if (eleve.charAt(0) != '%') {
            eleve = "%" + eleve;
        }
        if (eleve.charAt(eleve.length() - 1) != '%') {
            eleve = eleve + "%";
        }
        EntityManager em = PensionEleves.entityManager();
        TypedQuery<PensionEleves> q = em.createQuery("SELECT o FROM PensionEleves AS o WHERE LOWER(o.eleve.nom) LIKE LOWER(:eleve)", PensionEleves.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
    
    public static long getSoldeEleve(Eleve eleve) {
       
        
        List<PensionEleves> l = findPensionElevesPayable(eleve).getResultList();
        
        long solde = 0;
        
        for (PensionEleves p : l){
        	
        	solde += p.getNetAPayer();
        	
        }
        
        return solde;
    }
    
    public static TypedQuery<PensionEleves> findPensionElevesPayable(Eleve eleve) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = PensionEleves.entityManager();
        TypedQuery<PensionEleves> q = em.createQuery("SELECT o FROM PensionEleves AS o WHERE o.eleve = :eleve And o.solder = false And (o.montant-o.reduction-o.avance) > 0 ORDER BY o.tranche ASC ", PensionEleves.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
    public static TypedQuery<PensionEleves> findPensionEleve(Eleve eleve) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = PensionEleves.entityManager();
        TypedQuery<PensionEleves> q = em.createQuery("SELECT o FROM PensionEleves AS o WHERE o.eleve = :eleve ORDER BY o.tranche ASC ", PensionEleves.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
    public static TypedQuery<PensionEleves> findPensionElevesByInscription(Inscription inscription) {
        if (inscription == null) throw new IllegalArgumentException("The inscription argument is required");
        EntityManager em = PensionEleves.entityManager();
        TypedQuery<PensionEleves> q = em.createQuery("SELECT o FROM PensionEleves AS o WHERE o.inscriptionEleve = :inscription ORDER BY o.tranche ASC ", PensionEleves.class);
        q.setParameter("inscription", inscription);
        return q;
    }
    
    public static TypedQuery<PensionEleves> findPensionElevesPayable(Eleve eleve, AnneeScolaire annee) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = PensionEleves.entityManager();
        TypedQuery<PensionEleves> q = em.createQuery("SELECT o FROM PensionEleves AS o WHERE o.eleve = :eleve And o.annee = :annee And o.solder = false And (o.montant-o.reduction-o.avance) > 0 ORDER BY o.tranche ASC ", PensionEleves.class);
        
        q.setParameter("eleve", eleve).setParameter("annee", annee);
        
        return q;
    }

    
    
    public static List<PensionEleves> getListepension() {
        EntityManager em = PensionEleves.entityManager();
        return em.createQuery("SELECT o FROM PensionEleves AS o Order By o.annee, o.eleve.matricule, o.eleve.nom, o.tranche ", PensionEleves.class).getResultList();
       
    }
    
     
}
