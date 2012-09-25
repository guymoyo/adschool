package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class FamilleMatiere extends SchoolBaseEntity{

    private String familleKey;

    private String intitule = "";
    
    @PostPersist
    public void postPersit() {
        familleKey = NumberGenerator.getNumber("FA-", getId(), 4);
    }
}
