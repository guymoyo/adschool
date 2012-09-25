package adschool.domain;


import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.LevelDiplome;
import adschool.utils.NumberGenerator;

import javax.persistence.Enumerated;
import javax.persistence.PostPersist;

@RooJavaBean
@RooToString
@RooEntity
public class Diplome extends SchoolBaseEntity{

    private String diplomekey = "";

    private String libelle = "";

    @Enumerated
    private LevelDiplome niveauEtude;
    
    
    @PostPersist
    public void postPersit() {
        diplomekey = NumberGenerator.getNumber("DI-", getId(), 4);
    }
}
