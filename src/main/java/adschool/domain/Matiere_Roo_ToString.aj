// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect Matiere_Roo_ToString {
    
    public String Matiere.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actif: ").append(getActif()).append(", ");
        sb.append("Classe: ").append(getClasse()).append(", ");
        sb.append("CodeMatiere: ").append(getCodeMatiere()).append(", ");
        sb.append("Coefficient: ").append(getCoefficient()).append(", ");
        sb.append("Credit: ").append(getCredit()).append(", ");
        sb.append("Disposition: ").append(getDisposition()).append(", ");
        sb.append("Famille: ").append(getFamille()).append(", ");
        sb.append("Groupe: ").append(getGroupe()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Intitule: ").append(getIntitule()).append(", ");
        sb.append("MatiereKey: ").append(getMatiereKey()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}