// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Evaluation;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;

privileged aspect AbsenceEleves_Roo_JavaBean {
    
    public String AbsenceEleves.getAbsenceKey() {
        return this.absenceKey;
    }
    
    public void AbsenceEleves.setAbsenceKey(String absenceKey) {
        this.absenceKey = absenceKey;
    }
    
    public Evaluation AbsenceEleves.getEvaluation() {
        return this.evaluation;
    }
    
    public void AbsenceEleves.setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
    
    public Inscription AbsenceEleves.getInscriptionEleve() {
        return this.inscriptionEleve;
    }
    
    public void AbsenceEleves.setInscriptionEleve(Inscription inscriptionEleve) {
        this.inscriptionEleve = inscriptionEleve;
    }
    
    public Matiere AbsenceEleves.getMatiere() {
        return this.matiere;
    }
    
    public void AbsenceEleves.setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public Date AbsenceEleves.getDateAbsence() {
        return this.dateAbsence;
    }
    
    public void AbsenceEleves.setDateAbsence(Date dateAbsence) {
        this.dateAbsence = dateAbsence;
    }
    
    public Date AbsenceEleves.getDateSaisie() {
        return this.dateSaisie;
    }
    
    public void AbsenceEleves.setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }
    
    public Date AbsenceEleves.getHeureDebut() {
        return this.heureDebut;
    }
    
    public void AbsenceEleves.setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public Date AbsenceEleves.getHeureFin() {
        return this.heureFin;
    }
    
    public void AbsenceEleves.setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
    
    public int AbsenceEleves.getDuree() {
        return this.duree;
    }
    
    public void AbsenceEleves.setDuree(int duree) {
        this.duree = duree;
    }
    
    public Boolean AbsenceEleves.getJustified() {
        return this.justified;
    }
    
    public void AbsenceEleves.setJustified(Boolean justified) {
        this.justified = justified;
    }
    
}