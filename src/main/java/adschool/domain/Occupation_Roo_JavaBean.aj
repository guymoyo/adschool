// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Classe;
import adschool.domain.Jours;
import adschool.domain.Salle;
import java.lang.String;
import java.util.Date;

privileged aspect Occupation_Roo_JavaBean {
    
    public String Occupation.getOccupationKey() {
        return this.occupationKey;
    }
    
    public void Occupation.setOccupationKey(String occupationKey) {
        this.occupationKey = occupationKey;
    }
    
    public Classe Occupation.getClasse() {
        return this.classe;
    }
    
    public void Occupation.setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public Salle Occupation.getSalle() {
        return this.salle;
    }
    
    public void Occupation.setSalle(Salle salle) {
        this.salle = salle;
    }
    
    public Date Occupation.getHeureStart() {
        return this.heureStart;
    }
    
    public void Occupation.setHeureStart(Date heureStart) {
        this.heureStart = heureStart;
    }
    
    public Date Occupation.getHeureStop() {
        return this.heureStop;
    }
    
    public void Occupation.setHeureStop(Date heureStop) {
        this.heureStop = heureStop;
    }
    
    public Jours Occupation.getJour() {
        return this.jour;
    }
    
    public void Occupation.setJour(Jours jour) {
        this.jour = jour;
    }
    
}
