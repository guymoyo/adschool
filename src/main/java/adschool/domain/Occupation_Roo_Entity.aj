// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Occupation;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect Occupation_Roo_Entity {
    
    declare @type: Occupation: @Entity;
    
    public static long Occupation.countOccupations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Occupation o", Long.class).getSingleResult();
    }
    
    public static List<Occupation> Occupation.findAllOccupations() {
        return entityManager().createQuery("SELECT o FROM Occupation o", Occupation.class).getResultList();
    }
    
    public static Occupation Occupation.findOccupation(Long id) {
        if (id == null) return null;
        return entityManager().find(Occupation.class, id);
    }
    
    public static List<Occupation> Occupation.findOccupationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Occupation o", Occupation.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
