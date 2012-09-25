// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.FamilleMatiere;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect FamilleMatiere_Roo_Entity {
    
    declare @type: FamilleMatiere: @Entity;
    
    public static long FamilleMatiere.countFamilleMatieres() {
        return entityManager().createQuery("SELECT COUNT(o) FROM FamilleMatiere o", Long.class).getSingleResult();
    }
    
    public static List<FamilleMatiere> FamilleMatiere.findAllFamilleMatieres() {
        return entityManager().createQuery("SELECT o FROM FamilleMatiere o", FamilleMatiere.class).getResultList();
    }
    
    public static FamilleMatiere FamilleMatiere.findFamilleMatiere(Long id) {
        if (id == null) return null;
        return entityManager().find(FamilleMatiere.class, id);
    }
    
    public static List<FamilleMatiere> FamilleMatiere.findFamilleMatiereEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM FamilleMatiere o", FamilleMatiere.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
