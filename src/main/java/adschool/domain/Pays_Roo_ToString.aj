// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect Pays_Roo_ToString {
    
    public String Pays.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Libelle: ").append(getLibelle()).append(", ");
        sb.append("PaysKey: ").append(getPaysKey()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
