// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AbsenceEleves;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect AbsenceEleves_Roo_Entity {
    
    declare @type: AbsenceEleves: @Entity;
    
    public static long AbsenceEleves.countAbsenceEleveses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AbsenceEleves o", Long.class).getSingleResult();
    }
    
    public static List<AbsenceEleves> AbsenceEleves.findAllAbsenceEleveses() {
        return entityManager().createQuery("SELECT o FROM AbsenceEleves o", AbsenceEleves.class).getResultList();
    }
    
    public static AbsenceEleves AbsenceEleves.findAbsenceEleves(Long id) {
        if (id == null) return null;
        return entityManager().find(AbsenceEleves.class, id);
    }
    
    public static List<AbsenceEleves> AbsenceEleves.findAbsenceElevesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AbsenceEleves o", AbsenceEleves.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
