package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Etablissement;
import javax.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Value;
import adschool.domain.AnneeScolaire;
import adschool.domain.Regime;
import adschool.domain.StatutInscription;
import javax.persistence.Enumerated;
import adschool.domain.Eleve;
import adschool.utils.NumberGenerator;
import java.util.Set;
import adschool.domain.DocInscription;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJson
@RooJavaBean
@RooToString
@RooEntity(finders = { "findInscriptionsByEleve", "findInscriptionsByDateInscriptionEquals", "findInscriptionsByDateInscriptionBetween", "findInscriptionsByInscriptionKeyLike", "findInscriptionsByClasse", "findInscriptionsByAnnee" })
public class Inscription extends SchoolBaseEntity {

    @Column(unique = true)
    private String inscriptionKey;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInscription = new Date();

    private String agentSaisie = "";

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateSaisie = new Date();

    private int montantInscription = 0;

    @ManyToOne
    private Etablissement etablissement;

    @Value("false")
    private Boolean solder = false;

    @Value("false")
    private Boolean redoublant = false;

    @Value("true")
    private Boolean actif;

    @Value("false")
    private Boolean demission;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateDemission = new Date();

    @Value("false")
    private Boolean exclu = false;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateExclusion = new Date();

    private int avance = 0;

    private boolean imprimer = false;

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Regime regime;

    @Enumerated
    private StatutInscription statut;

    @ManyToOne
    private Eleve eleve;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Document> docInscriptions = new HashSet<Document>();

    @PostPersist
    public void postPersit() {
        inscriptionKey = NumberGenerator.getNumber("INS-", getId(), 6);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.inscriptionKey);
        return sb.toString();
    }

    public static List<Eleve> findInscription(Classe classe, AnneeScolaire annee, Etablissement etab) {
        if (classe == null || etab == null || annee == null) throw new IllegalArgumentException("The nom argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT o.eleve FROM Inscription AS o WHERE o.annee = :annee And o.classe =:classe And o.etablissement =:etab ", Eleve.class);
        return q.setParameter("classe", classe).setParameter("annee", annee).setParameter("etab", etab).getResultList();
    }

    public static Inscription findInscription(Classe classe, Eleve eleve, AnneeScolaire annee, Etablissement etab) {
        if (classe == null || eleve == null || etab == null || annee == null) throw new IllegalArgumentException("The nom argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.eleve.matricule = eleve.matricule  And o.annee.anneeKey = annee.anneeKey And o.classe.classeKey = classe.classeKey And o.etablissement.etablissementKey = etablissement.etablissementKey ", Inscription.class);
        List<Inscription> l = q.getResultList();
        if (l != null && !l.isEmpty()) {
            return l.get(0);
        }
        return null;
    }

    public static Inscription findInscription(Eleve eleve, AnneeScolaire annee) {
        if (eleve == null || annee == null) throw new IllegalArgumentException("The nom argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.eleve =:eleve And o.annee =:annee", Inscription.class);
        q.setParameter("eleve", eleve).setParameter("annee", annee);
        List<Inscription> l = q.getResultList();
        if (l != null && !l.isEmpty()) {
            return l.get(0);
        }
        return null;
    }

    public static Inscription inscrire(Classe classe, Eleve eleve, AnneeScolaire annee, Etablissement etab, Regime regime) {
        if (classe == null || eleve == null || etab == null || regime == null) throw new IllegalArgumentException("The nom argument is required");
        if (findInscription(classe, eleve, annee, etab) != null) {
            throw new IllegalArgumentException(" Cet eleve est deja inscrit");
        } else {
            Inscription inscription = new Inscription();
            inscription.setActif(true);
            inscription.setAnnee(annee);
            inscription.setAvance(0);
            inscription.setClasse(classe);
            inscription.setEleve(eleve);
            inscription.setStatut(StatutInscription.ANCIEN);
            inscription.setRegime(regime);
            inscription.setRedoublant(false);
            inscription.persist();
        }
        return null;
    }

    public static List<Inscription> getListeInscription(Classe classe, AnneeScolaire annee) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<Inscription> q = em.createQuery("Select i FROM Inscription AS i  Where i.classe =:classe AND i.annee =:annee Order By i.eleve.nom, i.eleve.prenom", Inscription.class);
        q.setParameter("classe", classe);
        q.setParameter("annee", annee);
        return q.getResultList();
    }

    public static List<Eleve> getListeEleveByClasse(AnneeScolaire annee, Classe classe) {
        if (annee == null || classe == null) throw new IllegalArgumentException("The inscriptionKey argument is required");
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT o.eleve FROM Inscription AS o WHERE o.classe = :classe And o.annee =:annee)", Eleve.class);
        q.setParameter("annee", annee);
        q.setParameter("classe", classe);
        return q.getResultList();
    }
}
