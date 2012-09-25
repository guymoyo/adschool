package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import adschool.domain.Speciality;
import adschool.utils.NumberGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

@RooJavaBean
@RooEntity(finders = { "findOptionsesBySpecialite" })
public class Options extends SchoolBaseEntity {

    private String optionsKey;

    @ManyToOne
    private Speciality specialite;

    private String libelle;

    @PostPersist
    public void postPersit() {
        optionsKey = NumberGenerator.getNumber("OP-", getId(), 4);
    }

    @Transient
    int nbreclasse = 0;

    @PostLoad
    public void postLoad() {
        nbreclasse = Classe.findClassesByOptions(this).getResultList().size();
    }

    @Override
    public String toString() {
        return "" + specialite.getLibelle() + " - " + libelle;
    }
}
