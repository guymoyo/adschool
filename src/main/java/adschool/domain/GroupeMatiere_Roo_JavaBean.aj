// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Classe;
import java.lang.Integer;
import java.lang.String;

privileged aspect GroupeMatiere_Roo_JavaBean {
    
    public String GroupeMatiere.getGroupeKey() {
        return this.groupeKey;
    }
    
    public void GroupeMatiere.setGroupeKey(String groupeKey) {
        this.groupeKey = groupeKey;
    }
    
    public String GroupeMatiere.getIntitule() {
        return this.intitule;
    }
    
    public void GroupeMatiere.setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public int GroupeMatiere.getDisposition() {
        return this.disposition;
    }
    
    public void GroupeMatiere.setDisposition(int disposition) {
        this.disposition = disposition;
    }
    
    public Integer GroupeMatiere.getTotalcredit() {
        return this.totalcredit;
    }
    
    public void GroupeMatiere.setTotalcredit(Integer totalcredit) {
        this.totalcredit = totalcredit;
    }
    
    public Classe GroupeMatiere.getClasse() {
        return this.classe;
    }
    
    public void GroupeMatiere.setClasse(Classe classe) {
        this.classe = classe;
    }
    
}