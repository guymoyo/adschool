package adschool.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.utils.NumberGenerator;
import adschool.domain.Etablissement;
import adschool.domain.Options;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(finders = { "findClassesByActif", "findClassesByNomClasseLike", "findClassesByNomClasseNotEquals", "findClassesByOptions", "findClassesByFiliere" })
public class Classe extends SchoolBaseEntity {

    private String classeKey;

    private String nomClasse;

    private String nomAbreger;

    private int nombreplace = 50;

    private Boolean actif = true;

    @ManyToOne
    private Cycles cycles;

    @Enumerated
    private Niveau niveau;

    private String codeFixe;

    @Transient
    private boolean copy_to_matiere;

    @ManyToOne
    private Filiere filiere;

    @ManyToOne
    private Etablissement etablissement;

    @ManyToOne
    private Options options;

    @PostPersist
    public void postPersit() {
        classeKey = NumberGenerator.getNumber("CL-", getId(), 4);
    }

    public static List<Classe> findClasseByName(String nom) {
        if (nom == null || nom.length() == 0) throw new IllegalArgumentException("The nom argument is required");
        nom = "%" + nom + "%";
        EntityManager em = Classe.entityManager();
        TypedQuery<Classe> q = em.createQuery("SELECT o FROM Classe AS o WHERE LOWER(o.nomClasse) LIKE LOWER(:nom) And o.actif=:actif order By  o.nomClasse ASC ", Classe.class);
        q.setParameter("nom", nom).setParameter("actif", true);
        return q.getResultList();
    }

    Classe copierClasse(Classe classe, boolean copier_matiere) {
        Classe classe_nouvelle = new Classe();
        classe_nouvelle.setActif(classe.getActif());
        classe_nouvelle.setCycles(classe.getCycles());
        classe_nouvelle.setNiveau(classe.getNiveau());
        classe_nouvelle.setNomAbreger(classe.getNomAbreger());
        classe_nouvelle.setNomClasse(classe.getNomClasse());
        classe_nouvelle.setNombreplace(classe.getNombreplace());
        if (copier_matiere) {
            save_matiere(classe);
        }
        classe_nouvelle.persist();
        classe_nouvelle.flush();
        return classe_nouvelle;
    }

    void save_matiere(Classe classe) {
        List<Matiere> listeMatiere = new ArrayList<Matiere>();
        listeMatiere = Matiere.getListeMatiere(classe);
        for (int i = 0; i < listeMatiere.size(); i++) {
            listeMatiere.get(i).setClasse(classe);
            listeMatiere.get(i).persist();
        }
    }

    /**
     * return la liste des classe de meme niveau que la classe passee en parametres
     * 
     */
    public static List<Classe> getListeClasseToNiveau(Classe classe) {
        List<Classe> listeClasse = new ArrayList<Classe>();
        for (Classe cl : Classe.findAllClasses()) {
            System.out.println(" cl " + cl.getNiveau().ordinal() + " -- " + classe.getNiveau().ordinal());
            System.out.println(" aa " + cl.getClasseKey() + " -- " + classe.getClasseKey());
            if (cl.getNiveau().ordinal() == classe.getNiveau().ordinal() && !cl.getClasseKey().equalsIgnoreCase(classe.getClasseKey())) {
                listeClasse.add(cl);
            }
        }
        return listeClasse;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNomAbreger());
        return sb.toString();
    }

    public static TypedQuery<Classe> findClassesByNomClasseEquals(String nomClasse, String nomAbreger) {
        if (nomClasse == null || nomClasse.length() == 0) throw new IllegalArgumentException("The nomClasse argument is required");
        EntityManager em = Classe.entityManager();
        TypedQuery<Classe> q = em.createQuery("SELECT o FROM Classe AS o WHERE o.nomClasse = :nomClasse And o.nomAbreger = :nomAbreger ", Classe.class);
        q.setParameter("nomClasse", nomClasse);
        q.setParameter("nomAbreger", nomAbreger);
        return q;
    }
}
