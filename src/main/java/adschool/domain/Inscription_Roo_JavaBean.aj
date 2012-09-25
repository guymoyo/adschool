// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Document;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Regime;
import adschool.domain.StatutInscription;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import java.util.Set;

privileged aspect Inscription_Roo_JavaBean {
    
    public String Inscription.getInscriptionKey() {
        return this.inscriptionKey;
    }
    
    public void Inscription.setInscriptionKey(String inscriptionKey) {
        this.inscriptionKey = inscriptionKey;
    }
    
    public Date Inscription.getDateInscription() {
        return this.dateInscription;
    }
    
    public void Inscription.setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    public String Inscription.getAgentSaisie() {
        return this.agentSaisie;
    }
    
    public void Inscription.setAgentSaisie(String agentSaisie) {
        this.agentSaisie = agentSaisie;
    }
    
    public Date Inscription.getDateSaisie() {
        return this.dateSaisie;
    }
    
    public void Inscription.setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }
    
    public int Inscription.getMontantInscription() {
        return this.montantInscription;
    }
    
    public void Inscription.setMontantInscription(int montantInscription) {
        this.montantInscription = montantInscription;
    }
    
    public Etablissement Inscription.getEtablissement() {
        return this.etablissement;
    }
    
    public void Inscription.setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    public Boolean Inscription.getSolder() {
        return this.solder;
    }
    
    public void Inscription.setSolder(Boolean solder) {
        this.solder = solder;
    }
    
    public Boolean Inscription.getRedoublant() {
        return this.redoublant;
    }
    
    public void Inscription.setRedoublant(Boolean redoublant) {
        this.redoublant = redoublant;
    }
    
    public Boolean Inscription.getActif() {
        return this.actif;
    }
    
    public void Inscription.setActif(Boolean actif) {
        this.actif = actif;
    }
    
    public Boolean Inscription.getDemission() {
        return this.demission;
    }
    
    public void Inscription.setDemission(Boolean demission) {
        this.demission = demission;
    }
    
    public Date Inscription.getDateDemission() {
        return this.dateDemission;
    }
    
    public void Inscription.setDateDemission(Date dateDemission) {
        this.dateDemission = dateDemission;
    }
    
    public Boolean Inscription.getExclu() {
        return this.exclu;
    }
    
    public void Inscription.setExclu(Boolean exclu) {
        this.exclu = exclu;
    }
    
    public Date Inscription.getDateExclusion() {
        return this.dateExclusion;
    }
    
    public void Inscription.setDateExclusion(Date dateExclusion) {
        this.dateExclusion = dateExclusion;
    }
    
    public int Inscription.getAvance() {
        return this.avance;
    }
    
    public void Inscription.setAvance(int avance) {
        this.avance = avance;
    }
    
    public boolean Inscription.isImprimer() {
        return this.imprimer;
    }
    
    public void Inscription.setImprimer(boolean imprimer) {
        this.imprimer = imprimer;
    }
    
    public Classe Inscription.getClasse() {
        return this.classe;
    }
    
    public void Inscription.setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public AnneeScolaire Inscription.getAnnee() {
        return this.annee;
    }
    
    public void Inscription.setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }
    
    public Regime Inscription.getRegime() {
        return this.regime;
    }
    
    public void Inscription.setRegime(Regime regime) {
        this.regime = regime;
    }
    
    public StatutInscription Inscription.getStatut() {
        return this.statut;
    }
    
    public void Inscription.setStatut(StatutInscription statut) {
        this.statut = statut;
    }
    
    public Eleve Inscription.getEleve() {
        return this.eleve;
    }
    
    public void Inscription.setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
    
    public Set<Document> Inscription.getDocInscriptions() {
        return this.docInscriptions;
    }
    
    public void Inscription.setDocInscriptions(Set<Document> docInscriptions) {
        this.docInscriptions = docInscriptions;
    }
    
}