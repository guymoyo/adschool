// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Options;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect Options_Roo_Entity {
    
    declare @type: Options: @Entity;
    
    public static long Options.countOptionses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Options o", Long.class).getSingleResult();
    }
    
    public static List<Options> Options.findAllOptionses() {
        return entityManager().createQuery("SELECT o FROM Options o", Options.class).getResultList();
    }
    
    public static Options Options.findOptions(Long id) {
        if (id == null) return null;
        return entityManager().find(Options.class, id);
    }
    
    public static List<Options> Options.findOptionsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Options o", Options.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
