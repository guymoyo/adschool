// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AnneeScolaire;
import adschool.domain.Inscription;
import adschool.domain.Periode;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;

privileged aspect Moyenneperiodes_Roo_JavaBean {
    
    public AnneeScolaire Moyenneperiodes.getAnnee() {
        return this.annee;
    }
    
    public void Moyenneperiodes.setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }
    
    public Periode Moyenneperiodes.getPeriode() {
        return this.periode;
    }
    
    public void Moyenneperiodes.setPeriode(Periode periode) {
        this.periode = periode;
    }
    
    public Inscription Moyenneperiodes.getInscription() {
        return this.inscription;
    }
    
    public void Moyenneperiodes.setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
    
    public Long Moyenneperiodes.getRang() {
        return this.rang;
    }
    
    public void Moyenneperiodes.setRang(Long rang) {
        this.rang = rang;
    }
    
    public Double Moyenneperiodes.getValeur() {
        return this.valeur;
    }
    
    public void Moyenneperiodes.setValeur(Double valeur) {
        this.valeur = valeur;
    }
    
    public String Moyenneperiodes.getMention() {
        return this.mention;
    }
    
    public void Moyenneperiodes.setMention(String mention) {
        this.mention = mention;
    }
    
    public String Moyenneperiodes.getDecision() {
        return this.decision;
    }
    
    public void Moyenneperiodes.setDecision(String decision) {
        this.decision = decision;
    }
    
}