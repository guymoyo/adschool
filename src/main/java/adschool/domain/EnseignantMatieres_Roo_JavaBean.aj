// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AnneeScolaire;
import adschool.domain.Enseignant;
import adschool.domain.Matiere;
import java.lang.String;

privileged aspect EnseignantMatieres_Roo_JavaBean {
    
    public Enseignant EnseignantMatieres.getEnseignant() {
        return this.enseignant;
    }
    
    public void EnseignantMatieres.setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    
    public Matiere EnseignantMatieres.getMatiere() {
        return this.matiere;
    }
    
    public void EnseignantMatieres.setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public AnneeScolaire EnseignantMatieres.getAnnee() {
        return this.annee;
    }
    
    public void EnseignantMatieres.setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }
    
    public String EnseignantMatieres.getValueKey() {
        return this.valueKey;
    }
    
    public void EnseignantMatieres.setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }
    
}
