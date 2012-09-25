package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Inscription;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;
import adschool.domain.Evaluation;
import adschool.domain.Etablissement;
import adschool.utils.NumberGenerator;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Eleve;
import adschool.domain.Matiere;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(finders = { "findNotesesByEleve" })
public class Notes extends SchoolBaseEntity {

    private String noteKey;

    private double valeur = 0;

    @ManyToOne
    private Inscription inscripionEleve;

    @ManyToOne
    private Evaluation evaluation;

    private double coefficient = 0;

    private double pourcentage = 0;

    @ManyToOne
    private Etablissement etablissement;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateSaisie = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateUpdate = new Date();
    
    @ManyToOne
    private Eleve eleve;

    @ManyToOne
    private Matiere matiere;
    
    private String agentSaisie = "";
    
    private String agentUpdate = "";
    

    @PostPersist
    public void postPersit() {
        noteKey = NumberGenerator.getNumber("NT-", getId(), 6);
    }

    public static TypedQuery<Notes> getListeNoteEleve(Eleve eleve, Evaluation evaluation, Matiere matiere) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT o FROM Notes AS o WHERE o.eleve = :eleve And o.matiere = :matiere And o.evaluation =:evaluation", Notes.class);
        q.setParameter("eleve", eleve).setParameter("matiere", matiere).setParameter("evaluation", evaluation);
        return q;
    }
    
    public static Notes findNoteEleve(Eleve eleve, Evaluation evaluation, Matiere matiere) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT o FROM Notes AS o WHERE o.eleve = :eleve And o.matiere = :matiere And o.evaluation =:evaluation", Notes.class);
        q.setParameter("eleve", eleve).setParameter("matiere", matiere).setParameter("evaluation", evaluation);
       
        if(q.getResultList() != null &&  ! q.getResultList().isEmpty()){
        	
        	q.getResultList().get(0);
        }
        
        return null;
    }
    
    
    
    public Notes getNotes(Matiere matiere, List<Notes> listeNotes){

       Notes note = new Notes();
       
       for(int i=0;i<listeNotes.size();i++){

           if(listeNotes.get(i).getMatiere() == matiere ){

               note = listeNotes.get(i);
           }
       }

        return note;

    }
    
    public static void saisieNote(Notes note){
    	
    	Notes n = findNoteEleve(note.getEleve(), note.getEvaluation(), note.getMatiere()) ;
    	
    	
    	if( n == null){
    		
    		note.persist();
    		
    	}else{
    		
    		n.setCoefficient(note.getCoefficient());
    		n.setPourcentage(note.getPourcentage());
    		n.setValeur(note.getValeur());
    		
    		n.merge();
    		
    		
    	}
    	
    	
    	
    }
    
    
    
    public static TypedQuery<Notes> getListeNoteClasse(Matiere matiere, Evaluation evaluation) {
        if (evaluation == null || matiere == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT o FROM Notes AS o WHERE o.matiere = :matiere And o.evaluation =:evaluation", Notes.class);
        q.setParameter("matiere", matiere).setParameter("evaluation", evaluation);
        return q;
    }
    

    public static TypedQuery<Notes> getListeNoteEleve(Inscription eleve, Evaluation evaluation) {
        if (evaluation == null || eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT o FROM Notes AS o WHERE o.inscripionEleve = :eleve And o.evaluation =:evaluation", Notes.class);
        q.setParameter("eleve", eleve).setParameter("evaluation", evaluation);
        return q;
    }
    
    public static Notes findNote(Matiere matiere, Evaluation evaluation, Inscription inscription) {
    	
    	System.out.println(matiere.getIntitule()+" "+matiere.getId()+" - "+evaluation.getLibelle()+" "+evaluation.getId()+" - Ins "+inscription.getId());
    	
        if (evaluation == null || matiere == null || inscription == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT n FROM Notes AS n WHERE n.matiere = :matiere And n.evaluation =:evaluation And n.inscripionEleve =:inscription", Notes.class);
        
        List<Notes> liste = q.setParameter("matiere", matiere).setParameter("evaluation", evaluation).setParameter("inscription", inscription).getResultList();
        
        return liste == null || liste.isEmpty() ? null : liste.get(0);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
