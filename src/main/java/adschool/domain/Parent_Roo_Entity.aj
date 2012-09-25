// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Parent;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect Parent_Roo_Entity {
    
    declare @type: Parent: @Entity;
    
    declare @type: Parent: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
    public static long Parent.countParents() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Parent o", Long.class).getSingleResult();
    }
    
    public static List<Parent> Parent.findAllParents() {
        return entityManager().createQuery("SELECT o FROM Parent o", Parent.class).getResultList();
    }
    
    public static Parent Parent.findParent(Long id) {
        if (id == null) return null;
        return entityManager().find(Parent.class, id);
    }
    
    public static List<Parent> Parent.findParentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Parent o", Parent.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}