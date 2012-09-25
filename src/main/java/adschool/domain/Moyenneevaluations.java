package adschool.domain;

import java.util.List;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import adschool.domain.Periode;
import adschool.domain.Evaluation;
import adschool.domain.Inscription;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findMoyenneevaluationsesByEvaluation" })
public class Moyenneevaluations {

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Periode periode;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private Inscription inscription;

    private Long rang;

    private Double valeur;

    private String mention;

    private String decision;
    
    
    public static void calculeMoyenne(Evaluation evaluation){
    	
    	List<Classe> listeClasse = Classe.findAllClasses();
    	
    	for (Classe classe : listeClasse) {
			
    		calculeMoyenneEvaluationClasse(evaluation, classe);
		}
    	
    	
    }
    

    public static void calculeMoyenneEvaluationClasse(Evaluation evaluation, Classe classe) {
        List<Inscription> listeInscription = Inscription.getListeInscription(classe, evaluation.getPeriode().getAnnee());
        for (Inscription ins : listeInscription) {
            List<Notes> listeNote = Notes.getListeNoteEleve(ins, evaluation).getResultList();
            double moyenne = calculemoyenne(listeNote);
            Moyenneevaluations moy = new Moyenneevaluations();
            moy.setAnnee(evaluation.getPeriode().getAnnee());
            moy.setEvaluation(evaluation);
            moy.setInscription(ins);
            moy.setPeriode(evaluation.getPeriode());
            moy.setValeur(moyenne);
            
            moy.setMention(Mentions.getMention(moyenne));
            moy.persist();
        }
        
        List<Moyenneevaluations> listeEv = findMoyenneevaluationsesByEvaluation(evaluation, classe).getResultList();
        
        for (int i = 0; i < listeEv.size(); i++) {
			
        	listeEv.get(i).setRang(new Long(i+1));
        	
        	listeEv.get(i).merge();
        	
        	
		}
        
        
    }

    public static double calculemoyenne(List<Notes> listeNotes) {
        double total = 0;
        double coef = 0;
        for (Notes n : listeNotes) {
            total += n.getValeur() * n.getCoefficient();
            coef += n.getCoefficient();
        }
        return total / coef;
    }
    
    
    public static TypedQuery<Moyenneevaluations> findMoyenneevaluationsesByEvaluation(Evaluation evaluation, Classe classe) {
        if (evaluation == null || classe == null) throw new IllegalArgumentException("The evaluation argument is required");
        EntityManager em = Moyenneevaluations.entityManager();
        TypedQuery<Moyenneevaluations> q = em.createQuery("SELECT o FROM Moyenneevaluations AS o WHERE o.evaluation = :evaluation And o.inscription.classe=:classe Order By o.valeur", Moyenneevaluations.class);
        q.setParameter("evaluation", evaluation);
        q.setParameter("classe", classe);
        return q;
    }
}
