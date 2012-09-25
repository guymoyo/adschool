package adschool.domain;

import java.util.List;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import adschool.domain.Classe;
import adschool.domain.Periode;
import adschool.domain.Evaluation;

@RooJavaBean
@RooToString
@RooEntity
public class Moyenneegenerales {

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private Periode periode;

    @ManyToOne
    private Evaluation evaluation;

    private Double valeur;
    
    
    
 public static void calculeMoyenne(Evaluation evaluation){
    	
    	List<Classe> listeClasse = Classe.findAllClasses();
    	
    	for (Classe classe : listeClasse) {
			
    		calculeMoyenneGeneraleClasse(evaluation, classe);
		}
    	
    	
    }
    


    public static void calculeMoyenneGeneraleClasse(Evaluation evaluation, Classe classe) {
        
    	List<Moyenneevaluations> listeMoyenneevaluations = Moyenneevaluations.findMoyenneevaluationsesByEvaluation(evaluation, classe).getResultList();
        
    	if( listeMoyenneevaluations != null){
    	
    		double moyenne = calculemoyenne(listeMoyenneevaluations);
        	
        	Moyenneegenerales moy = new Moyenneegenerales();
        	
        	moy.setAnnee(evaluation.getPeriode().getAnnee());
        	moy.setClasse(classe);
        	moy.setEvaluation(evaluation);
        	moy.setPeriode(evaluation.getPeriode());
        	moy.setValeur(moyenne);
        	
        	moy.persist();
     
    		
    	}
    	
    	   	
        
    }
    
    
    
    
    
    
    
    public static double calculemoyenne(List<Moyenneevaluations> listeMoyenneevaluations) {
        double total = 0;
        
        for (Moyenneevaluations n : listeMoyenneevaluations) {
        
        	total += n.getValeur();
            
        }
        return total / listeMoyenneevaluations.size();
    }
    
   
    
    
    
}
