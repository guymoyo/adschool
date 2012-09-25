package adschool.domain;

import java.util.List;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.beans.factory.annotation.Value;
import adschool.domain.Batiment;
import adschool.utils.NumberGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;

@RooJavaBean
@RooEntity(finders = { "findSallesByActif", "findSallesByBatiment" })
public class Salle extends SchoolBaseEntity {

    private String salleKey;

    private String nomsalle;

    private int nombrePlace;

    @Value("true")
    private Boolean disponible;

    @Value("true")
    private Boolean actif;

    private int longueur;

    private int largeur;

    @ManyToOne
    private Batiment batiment;

    List<Salle> getListeSalleActif() {
        TypedQuery<Salle> t = findSallesByActif(true);
        return t.getResultList();
    }

    @PostPersist
    public void postPersit() {
        salleKey = NumberGenerator.getNumber("SA-", getId(), 4);
    }

    @Override
    public String toString() {
        return nomsalle;
    }
}
