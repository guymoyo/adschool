// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Etablissement;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect Etablissement_Roo_Entity {
    
    declare @type: Etablissement: @Entity;
    
    public static long Etablissement.countEtablissements() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Etablissement o", Long.class).getSingleResult();
    }
    
    public static List<Etablissement> Etablissement.findAllEtablissements() {
        return entityManager().createQuery("SELECT o FROM Etablissement o", Etablissement.class).getResultList();
    }
    
    public static Etablissement Etablissement.findEtablissement(Long id) {
        if (id == null) return null;
        return entityManager().find(Etablissement.class, id);
    }
    
    public static List<Etablissement> Etablissement.findEtablissementEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Etablissement o", Etablissement.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
