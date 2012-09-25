package adschool.domain;

import javax.persistence.PostPersist;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.utils.NumberGenerator;
import adschool.domain.Classe;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findGroupeMatieresByClasse" })
public class GroupeMatiere extends SchoolBaseEntity {

    private String groupeKey;

    private String intitule;

    private int disposition = 1;

    private Integer totalcredit;

    @ManyToOne
    private Classe classe;

    @PostPersist
    public void postPersit() {
        groupeKey = NumberGenerator.getNumber("GK-", getId(), 4);
    }
}
