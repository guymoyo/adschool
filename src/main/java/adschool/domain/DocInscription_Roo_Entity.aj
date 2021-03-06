// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.DocInscription;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect DocInscription_Roo_Entity {
    
    declare @type: DocInscription: @Entity;
    
    public static long DocInscription.countDocInscriptions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DocInscription o", Long.class).getSingleResult();
    }
    
    public static List<DocInscription> DocInscription.findAllDocInscriptions() {
        return entityManager().createQuery("SELECT o FROM DocInscription o", DocInscription.class).getResultList();
    }
    
    public static DocInscription DocInscription.findDocInscription(Long id) {
        if (id == null) return null;
        return entityManager().find(DocInscription.class, id);
    }
    
    public static List<DocInscription> DocInscription.findDocInscriptionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DocInscription o", DocInscription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
