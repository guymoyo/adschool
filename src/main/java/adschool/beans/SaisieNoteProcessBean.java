package adschool.beans;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Etablissement;
import adschool.domain.Evaluation;
import adschool.domain.Matiere;

public class SaisieNoteProcessBean {
	
	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setInscription(AnneeScolaire inscription) {
		this.anneeScolaire = inscription;
	}
	
	public Etablissement getEtablissement() {
		return etablissement;
	}
	
	public void setEtablissement(Etablissement inscription) {
		this.etablissement = inscription;
	}

	public void setClasse(Classe inscription) {
		this.classe = inscription;
	}
	
	public Classe getClasse() {
		return classe;
	}

	public void setMatiere(Matiere inscription) {
		this.matiere = inscription;
	}
	
	public Matiere getMatiere() {
		return matiere;
	}


	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public Evaluation getEvaluation() {
		return evaluation;
	}


	
	

	private AnneeScolaire anneeScolaire ;
	
	private Etablissement etablissement ;
	
	private Classe classe ;
	
	private Matiere matiere ;
	
	
	
	private Evaluation evaluation ;

	

}
