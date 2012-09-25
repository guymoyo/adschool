package adschool.domain;

import java.util.List;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Etablissement;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import adschool.domain.Parent;
import adschool.utils.NumberGenerator;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(inheritanceType = "TABLE_PER_CLASS", finders = { "findElevesByMatriculeLike", "findElevesByNomLike", "findElevesByEtablissement" })
public class Eleve extends Personne {

    @ManyToOne
    private Etablissement etablissement = new Etablissement();

    @ManyToOne
    private Parent parent = new Parent();

    @Transient
    private Long parentId;

    private String prenom;

    private String emailExterne;

    public Long getParentId() {
        return parentId;
    }

    @Transient
    private long solde = 0;
    
    @Transient
    private String fullName = "";

    public void save() {
        this.persist();
    }

    @PostLoad
    void loadsolde() {
        solde = PensionEleves.getSoldeEleve(this);
        
        fullName = getFullName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom() + " " + this.getPrenom());
        return sb.toString();
    }
    
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom() + " " + this.getPrenom());
        return sb.toString();
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public void internalPostPersist() {
        matricule = matricule == null || matricule.isEmpty() ? NumberGenerator.getNumber("EL-", getId(), 6) : matricule;
    }

    public static TypedQuery<Eleve> findElevesByNomLikeOrMatricule(String nom, String matricule) {
        if (nom == null || nom.length() == 0) {
            nom = "%%";
        } else {
            nom = nom.replace('*', '%');
            if (nom.charAt(0) != '%') {
                nom = "%" + nom;
            }
            if (nom.charAt(nom.length() - 1) != '%') {
                nom = nom + "%";
            }
        }
        if (matricule == null || matricule.length() == 0) {
            matricule = "%%";
        } else {
            matricule = matricule.replace('*', '%');
            if (matricule.charAt(0) != '%') {
                matricule = "%" + matricule;
            }
            if (matricule.charAt(matricule.length() - 1) != '%') {
                matricule = matricule + "%";
            }
        }
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT o FROM Eleve AS o WHERE LOWER(o.nom) LIKE LOWER(:nom) And LOWER(o.matricule) LIKE LOWER(:matricule)", Eleve.class);
        q.setParameter("nom", nom).setParameter("matricule", matricule);
        return q;
    }

    public static TypedQuery<Eleve> findElevesByNomLikeAndMatricule(String nom, String matricule, AnneeScolaire annee) {
        
    	if (nom == null ) nom = "";
        
    	if (matricule == null ) matricule = "";
        
        
        nom = nom.replace('*', '%');
        if (nom.charAt(0) != '%') {
            nom = "%" + nom;
        }
        
        if (nom.charAt(nom.length() - 1) != '%') {
            nom = nom + "%";
        }
        matricule = matricule.replace('*', '%');
        if (matricule.charAt(0) != '%') {
            matricule = "%" + matricule;
        }
        if (matricule.charAt(matricule.length() - 1) != '%') {
            matricule = matricule + "%";
        }
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT e FROM Eleve AS e, Inscription AS i WHERE e.matricule = i.eleve.matricule And i.annee.anneeKey = annee.anneeKey And LOWER(o.nom) LIKE LOWER(:nom) Or LOWER(o.matricule) LIKE LOWER(:matricule)", Eleve.class);
        q.setParameter("nom", nom).setParameter("matricule", matricule);
        return q;
    }

    public static TypedQuery<Eleve> getListeElevesNotInscrit(String matricule, String anneeKey) {
        if (matricule == null ) matricule = "";
        matricule = matricule.replace('*', '%');
        if (matricule.charAt(0) != '%') {
            matricule = "%" + matricule;
        }
        if (matricule.charAt(matricule.length() - 1) != '%') {
            matricule = matricule + "%";
        }
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT e FROM Eleve AS e WHERE LOWER(o.nom) LIKE LOWER(:matricule) And e Not In (Select m.eleve FROM Inscription AS m Where m.annee.anneeKey = :anneeKey )  ", Eleve.class);
        q.setParameter("matricule", matricule);
        q.setParameter("anneeKey", anneeKey);
        return q;
    }

    public static List<Eleve> getListeElevesInscrit(Classe classe, AnneeScolaire annee) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("Select DISTINCT(i.eleve) FROM Inscription AS i  Where i.classe =:classe AND i.annee =:annee ", Eleve.class);
        q.setParameter("classe", classe);
        q.setParameter("annee", annee);
        return q.getResultList();
    }

    public static Eleve getEleve(String matricule) {
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("Select e FROM Eleve AS e Where e.matricule =:matricule ", Eleve.class);
        q.setParameter("matricule", matricule);
        List<Eleve> liste = q.getResultList();
        return liste == null || liste.isEmpty() ? null : liste.get(0);
    }
    
    public static TypedQuery<Eleve> findElevesByNomLike(String nom) {
        
    	nom = format(nom);
    	
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT o FROM Eleve AS o WHERE LOWER(o.nom) LIKE LOWER(:nom)", Eleve.class);
        q.setParameter("nom", nom);
        return q;
    }
    
    public static TypedQuery<Eleve> findElevesByMatriculeLike(String matricule) {
      
    	matricule = format(matricule);
    	
        EntityManager em = Eleve.entityManager();
        TypedQuery<Eleve> q = em.createQuery("SELECT o FROM Eleve AS o WHERE LOWER(o.matricule) LIKE LOWER(:matricule)", Eleve.class);
        q.setParameter("matricule", matricule);
        return q;
    }
    
    
    
    public static String format(String text_in){
    	
    	String text_out = "";
    	
    	if (text_in == null || text_in == "") return text_out;
        
    	text_in = text_in.replace('*', '%');
        if (text_in.charAt(0) != '%') {
        	text_in = "%" + text_in;
        }
        if (text_in.charAt(text_in.length() - 1) != '%') {
        	text_in = text_in + "%";
        }
    	
        text_out = text_in;
    	
    	return text_out;
    }
    
}
