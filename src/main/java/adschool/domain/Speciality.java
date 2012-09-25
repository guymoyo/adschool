package adschool.domain;

import java.util.List;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooEntity
public class Speciality extends SchoolBaseEntity {

	private String specialityKey;
	
    private String libelle;
    
    @PostPersist
    public void postPersit() {
    	specialityKey = NumberGenerator.getNumber("SP-", getId(), 4);
    }

	@Override
	public String toString() {
		return  libelle ;
	}
    
	
	@Transient
    int nbreclasse = 0;

    @PostLoad
    public void postLoad() {
        
    	List<Options> findOptionsesBySpecialite = Options.findOptionsesBySpecialite(this).getResultList();
    	
    	if (findOptionsesBySpecialite != null) {
			
    		for (Options options : findOptionsesBySpecialite) {
		
    			nbreclasse += Classe.findClassesByOptions(options).getResultList().size();
    			
			}
		}
    	
    }
    
    
}
