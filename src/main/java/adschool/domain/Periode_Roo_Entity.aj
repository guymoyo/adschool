// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Periode;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect Periode_Roo_Entity {
    
    declare @type: Periode: @Entity;
    
    public static long Periode.countPeriodes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Periode o", Long.class).getSingleResult();
    }
    
    public static List<Periode> Periode.findAllPeriodes() {
        return entityManager().createQuery("SELECT o FROM Periode o", Periode.class).getResultList();
    }
    
    public static Periode Periode.findPeriode(Long id) {
        if (id == null) return null;
        return entityManager().find(Periode.class, id);
    }
    
    public static List<Periode> Periode.findPeriodeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Periode o", Periode.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
