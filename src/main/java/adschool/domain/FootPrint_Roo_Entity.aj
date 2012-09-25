// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.FootPrint;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect FootPrint_Roo_Entity {
    
    declare @type: FootPrint: @Entity;
    
    public static long FootPrint.countFootPrints() {
        return entityManager().createQuery("SELECT COUNT(o) FROM FootPrint o", Long.class).getSingleResult();
    }
    
    public static List<FootPrint> FootPrint.findAllFootPrints() {
        return entityManager().createQuery("SELECT o FROM FootPrint o", FootPrint.class).getResultList();
    }
    
    public static FootPrint FootPrint.findFootPrint(Long id) {
        if (id == null) return null;
        return entityManager().find(FootPrint.class, id);
    }
    
    public static List<FootPrint> FootPrint.findFootPrintEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM FootPrint o", FootPrint.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}