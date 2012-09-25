// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.ConfigInscription;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect ConfigInscription_Roo_Entity {
    
    declare @type: ConfigInscription: @Entity;
    
    public static long ConfigInscription.countConfigInscriptions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ConfigInscription o", Long.class).getSingleResult();
    }
    
    public static List<ConfigInscription> ConfigInscription.findAllConfigInscriptions() {
        return entityManager().createQuery("SELECT o FROM ConfigInscription o", ConfigInscription.class).getResultList();
    }
    
    public static ConfigInscription ConfigInscription.findConfigInscription(Long id) {
        if (id == null) return null;
        return entityManager().find(ConfigInscription.class, id);
    }
    
    public static List<ConfigInscription> ConfigInscription.findConfigInscriptionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ConfigInscription o", ConfigInscription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
