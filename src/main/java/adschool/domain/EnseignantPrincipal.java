package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;

import adschool.domain.Enseignant;
import adschool.domain.Matiere;
import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class EnseignantPrincipal extends SchoolBaseEntity {

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Matiere matiere;

    private String valueKey = "";
    
    @PostPersist
    public void postPersit() {
        valueKey = NumberGenerator.getNumber("EM-", getId(), 4);
    }
}
