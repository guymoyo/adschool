// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect AvertissementEleves_Roo_ToString {
    
    public String AvertissementEleves.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AvertKey: ").append(getAvertKey()).append(", ");
        sb.append("DateAvertissement: ").append(getDateAvertissement()).append(", ");
        sb.append("DateSaisie: ").append(getDateSaisie()).append(", ");
        sb.append("Debut: ").append(getDebut()).append(", ");
        sb.append("Duree: ").append(getDuree()).append(", ");
        sb.append("Evaluation: ").append(getEvaluation()).append(", ");
        sb.append("Fin: ").append(getFin()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("InscriptionEleve: ").append(getInscriptionEleve()).append(", ");
        sb.append("Justified: ").append(getJustified()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
