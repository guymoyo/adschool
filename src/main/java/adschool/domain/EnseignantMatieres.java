package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Enseignant;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import adschool.domain.Matiere;
import adschool.domain.AnneeScolaire;
import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class EnseignantMatieres extends SchoolBaseEntity {

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Matiere matiere;

    @ManyToOne
    private AnneeScolaire annee;

    private String valueKey = "";

      
    @PostPersist
    public void postPersit() {
        valueKey = NumberGenerator.getNumber("EM-", getId(), 4);
    }
}
