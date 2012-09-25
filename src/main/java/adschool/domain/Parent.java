package adschool.domain;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.utils.NumberGenerator;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean

@RooEntity(inheritanceType = "TABLE_PER_CLASS", finders = { "findParentsByNomLike", "findParentsByMatriculeLike", "findParentsByNomPereLikeOrNomMereLike" })
@RooJson
public class Parent extends Personne {

    private String nomPere;

    private String nomMere;

    private String telephonePere;

    private String telephoneMere;

    private String EmailPere;

    private String EmailMere;

    
    
    @Override
	public String toString() {
		
    	return this == null ?"": "";
	}

	@Override
    public void internalPostPersist() {
        matricule = NumberGenerator.getNumber("PA-", getId(), 6);
    }

    public static TypedQuery<Parent> findParentsById(String matricule) {
        if (matricule == null || matricule.length() == 0) throw new IllegalArgumentException("The nom argument is required");
        matricule = matricule.replace('*', '%');
        if (matricule.charAt(0) != '%') {
            matricule = "%" + matricule;
        }
        if (matricule.charAt(matricule.length() - 1) != '%') {
            matricule = matricule + "%";
        }
        EntityManager em = Parent.entityManager();
        TypedQuery<Parent> q = em.createQuery("SELECT o FROM Parent AS o WHERE LOWER(o.matricule) LIKE LOWER(:matricule)", Parent.class);
        q.setParameter("matricule", matricule);
        return q;
    }

	public static TypedQuery<Parent> findParentsByNomPereLikeOrNomMereLike(String nomPere, String nomMere) {
		 if (nomPere == null || nomPere.length() == 0) {
		        
	        	nomPere = "%%";
	        }else{
	        	
	        	nomPere = nomPere.replace('*', '%');
	            if (nomPere.charAt(0) != '%') {
	            	nomPere = "%" + nomPere;
	            }
	            if (nomPere.charAt(nomPere.length() - 1) != '%') {
	            	nomPere = nomPere + "%";
	            }
	        	
	        }
        
        if (nomMere == null || nomMere.length() == 0) {
        
        	nomMere = "%%";
        }else{
        	
        	nomMere = nomMere.replace('*', '%');
            if (nomMere.charAt(0) != '%') {
            	nomMere = "%" + nomMere;
            }
            if (nomMere.charAt(nomMere.length() - 1) != '%') {
            	nomMere = nomMere + "%";
            }
        	
        }
        
        
        EntityManager em = Parent.entityManager();
        TypedQuery<Parent> q = em.createQuery("SELECT o FROM Parent AS o WHERE LOWER(o.nomPere) LIKE LOWER(:nomPere) And LOWER(o.nomMere) LIKE LOWER(:nomMere)", Parent.class);
        q.setParameter("nomPere", nomPere).setParameter("nomMere", nomMere);
        return q;
	}
}
