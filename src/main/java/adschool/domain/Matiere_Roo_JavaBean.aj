// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Classe;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

privileged aspect Matiere_Roo_JavaBean {
    
    public String Matiere.getMatiereKey() {
        return this.matiereKey;
    }
    
    public void Matiere.setMatiereKey(String matiereKey) {
        this.matiereKey = matiereKey;
    }
    
    public String Matiere.getCodeMatiere() {
        return this.codeMatiere;
    }
    
    public void Matiere.setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }
    
    public String Matiere.getIntitule() {
        return this.intitule;
    }
    
    public void Matiere.setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public Boolean Matiere.getActif() {
        return this.actif;
    }
    
    public void Matiere.setActif(Boolean actif) {
        this.actif = actif;
    }
    
    public double Matiere.getCoefficient() {
        return this.coefficient;
    }
    
    public void Matiere.setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
    
    public Classe Matiere.getClasse() {
        return this.classe;
    }
    
    public void Matiere.setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public int Matiere.getDisposition() {
        return this.disposition;
    }
    
    public void Matiere.setDisposition(int disposition) {
        this.disposition = disposition;
    }
    
    public FamilleMatiere Matiere.getFamille() {
        return this.famille;
    }
    
    public void Matiere.setFamille(FamilleMatiere famille) {
        this.famille = famille;
    }
    
    public GroupeMatiere Matiere.getGroupe() {
        return this.groupe;
    }
    
    public void Matiere.setGroupe(GroupeMatiere groupe) {
        this.groupe = groupe;
    }
    
    public Integer Matiere.getCredit() {
        return this.credit;
    }
    
    public void Matiere.setCredit(Integer credit) {
        this.credit = credit;
    }
    
}