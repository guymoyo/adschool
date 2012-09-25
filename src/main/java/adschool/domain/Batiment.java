package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

import javax.persistence.Column;
import javax.persistence.PostPersist;

@RooJavaBean
@RooEntity
public class Batiment extends SchoolBaseEntity {

    @Override
	public String toString() {
		return nomBatiment;
	}

	@Column(unique = true)
    private String batimentKey;

    private String nomBatiment;

    private int nombreEtage = 1;
    
    @PostPersist
    public void postPersit() {
        batimentKey = NumberGenerator.getNumber("BA-", getId(), 4);
    }
    
    public static void initBatiments(){
    	long count = Batiment.countBatiments(); 
    	if (count < 1) {
    		Batiment batiment = new Batiment();
        	batiment.setNomBatiment("BATIMENT A");
        	batiment.setNombreEtage(4);
        	batiment.persist();
		}
    	
    }
    
    
    
}
