package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Pays extends SchoolBaseEntity{

    private String libelle;

    private String paysKey;
    
    @PostPersist
    public void postPersit() {
        paysKey = NumberGenerator.getNumber("PA-", getId(), 4);
    }
    
    public static void initCountrys(){
    	long count = Pays.countPayses();
    	if (count < 1) {
    		Pays pays = new Pays();
        	
        	pays.setLibelle("CAMEROON");
        	pays.persist();
		}
    	
    }
}
