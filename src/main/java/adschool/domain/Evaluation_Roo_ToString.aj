// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect Evaluation_Roo_ToString {
    
    public String Evaluation.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actif: ").append(getActif()).append(", ");
        sb.append("Datedebut: ").append(getDatedebut()).append(", ");
        sb.append("Datefin: ").append(getDatefin()).append(", ");
        sb.append("Etablissement: ").append(getEtablissement()).append(", ");
        sb.append("EvaluationKey: ").append(getEvaluationKey()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Libelle: ").append(getLibelle()).append(", ");
        sb.append("Periode: ").append(getPeriode()).append(", ");
        sb.append("Pourcentage: ").append(getPourcentage()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}