// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Enseignant;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect Enseignant_Roo_Entity {
    
    declare @type: Enseignant: @Entity;
    
    declare @type: Enseignant: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
    public static long Enseignant.countEnseignants() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Enseignant o", Long.class).getSingleResult();
    }
    
    public static List<Enseignant> Enseignant.findAllEnseignants() {
        return entityManager().createQuery("SELECT o FROM Enseignant o", Enseignant.class).getResultList();
    }
    
    public static Enseignant Enseignant.findEnseignant(Long id) {
        if (id == null) return null;
        return entityManager().find(Enseignant.class, id);
    }
    
    public static List<Enseignant> Enseignant.findEnseignantEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Enseignant o", Enseignant.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
