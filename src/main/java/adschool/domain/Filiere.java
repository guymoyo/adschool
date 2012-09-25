package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Filiere extends SchoolBaseEntity{

    private String filiereKey;

    private String libelle = "";
    
    
    @PostPersist
    public void postPersist() {
        filiereKey = NumberGenerator.getNumber("FI-", getId(), 6);
    }
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(getLibelle());
        
        return sb.toString();
    }
}
