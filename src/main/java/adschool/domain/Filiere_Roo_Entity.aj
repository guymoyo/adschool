// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Filiere;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect Filiere_Roo_Entity {
    
    declare @type: Filiere: @Entity;
    
    public static long Filiere.countFilieres() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Filiere o", Long.class).getSingleResult();
    }
    
    public static List<Filiere> Filiere.findAllFilieres() {
        return entityManager().createQuery("SELECT o FROM Filiere o", Filiere.class).getResultList();
    }
    
    public static Filiere Filiere.findFiliere(Long id) {
        if (id == null) return null;
        return entityManager().find(Filiere.class, id);
    }
    
    public static List<Filiere> Filiere.findFiliereEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Filiere o", Filiere.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}