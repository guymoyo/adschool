// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect Periode_Roo_ToString {
    
    public String Periode.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actif: ").append(getActif()).append(", ");
        sb.append("Annee: ").append(getAnnee()).append(", ");
        sb.append("Datedebut: ").append(getDatedebut()).append(", ");
        sb.append("Datefin: ").append(getDatefin()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Libelle: ").append(getLibelle()).append(", ");
        sb.append("PeriodeKey: ").append(getPeriodeKey()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
