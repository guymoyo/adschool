// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import java.lang.String;

privileged aspect Etablissement_Roo_ToString {
    
    public String Etablissement.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adresse: ").append(getAdresse()).append(", ");
        sb.append("BoitePostale: ").append(getBoitePostale()).append(", ");
        sb.append("Division: ").append(getDivision()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("EtablissementKey: ").append(getEtablissementKey()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("LogoFile: ").append(getLogoFile()).append(", ");
        sb.append("LogoPath: ").append(getLogoPath()).append(", ");
        sb.append("Ministere: ").append(getMinistere()).append(", ");
        sb.append("NomAbreger: ").append(getNomAbreger()).append(", ");
        sb.append("NomEtablissement: ").append(getNomEtablissement()).append(", ");
        sb.append("NomResponsable: ").append(getNomResponsable()).append(", ");
        sb.append("PhoneFixe: ").append(getPhoneFixe()).append(", ");
        sb.append("PhoneMobile: ").append(getPhoneMobile()).append(", ");
        sb.append("SignatureResponsable: ").append(getSignatureResponsable()).append(", ");
        sb.append("SiteWeb: ").append(getSiteWeb()).append(", ");
        sb.append("TypeEtablissement: ").append(getTypeEtablissement()).append(", ");
        sb.append("TypeResponsable: ").append(getTypeResponsable()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Ville: ").append(getVille());
        return sb.toString();
    }
    
}