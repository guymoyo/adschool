// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.PointageEnseignants;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect PointageEnseignants_Roo_Entity {
    
    declare @type: PointageEnseignants: @Entity;
    
    public static long PointageEnseignants.countPointageEnseignantses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PointageEnseignants o", Long.class).getSingleResult();
    }
    
    public static List<PointageEnseignants> PointageEnseignants.findAllPointageEnseignantses() {
        return entityManager().createQuery("SELECT o FROM PointageEnseignants o", PointageEnseignants.class).getResultList();
    }
    
    public static PointageEnseignants PointageEnseignants.findPointageEnseignants(Long id) {
        if (id == null) return null;
        return entityManager().find(PointageEnseignants.class, id);
    }
    
    public static List<PointageEnseignants> PointageEnseignants.findPointageEnseignantsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PointageEnseignants o", PointageEnseignants.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
