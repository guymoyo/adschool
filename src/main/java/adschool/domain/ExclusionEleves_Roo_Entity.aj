// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.ExclusionEleves;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect ExclusionEleves_Roo_Entity {
    
    declare @type: ExclusionEleves: @Entity;
    
    public static long ExclusionEleves.countExclusionEleveses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ExclusionEleves o", Long.class).getSingleResult();
    }
    
    public static List<ExclusionEleves> ExclusionEleves.findAllExclusionEleveses() {
        return entityManager().createQuery("SELECT o FROM ExclusionEleves o", ExclusionEleves.class).getResultList();
    }
    
    public static ExclusionEleves ExclusionEleves.findExclusionEleves(Long id) {
        if (id == null) return null;
        return entityManager().find(ExclusionEleves.class, id);
    }
    
    public static List<ExclusionEleves> ExclusionEleves.findExclusionElevesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ExclusionEleves o", ExclusionEleves.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}