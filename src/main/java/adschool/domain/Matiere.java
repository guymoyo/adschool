package adschool.domain;

import java.util.List;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Classe;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import adschool.utils.NumberGenerator;

@RooJavaBean
@RooJson
@RooToString
@RooEntity(finders = { "findMatieresByClasse", "findMatieresByGroupe", "findMatieresByFamille" })
public class Matiere extends SchoolBaseEntity {

    private String matiereKey;

    private String codeMatiere;

    private String intitule;

    private Boolean actif = true;

    private double coefficient = 1;

    @ManyToOne
    private Classe classe;

    private int disposition = 1;

    @ManyToOne
    private FamilleMatiere famille;

    @ManyToOne
    private GroupeMatiere groupe;

    private Integer credit;

    @PostPersist
    public void postPersit() {
        matiereKey = NumberGenerator.getNumber("MA-", getId(), 4);
    }

    public static List<Matiere> getListeMatiere(Classe classe) {
        EntityManager em = Salle.entityManager();
        TypedQuery<Matiere> q = em.createQuery("SELECT m FROM Matiere AS m WHERE m.classe = :classe Order By m.disposition, m.intitule", Matiere.class);
        q.setParameter("classe", classe);
        return q.getResultList();
    }
}
