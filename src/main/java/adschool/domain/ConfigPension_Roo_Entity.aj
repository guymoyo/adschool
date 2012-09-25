// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.ConfigPension;
import java.lang.Long;
import java.util.List;
import javax.persistence.Entity;

privileged aspect ConfigPension_Roo_Entity {
    
    declare @type: ConfigPension: @Entity;
    
    public static long ConfigPension.countConfigPensions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ConfigPension o", Long.class).getSingleResult();
    }
    
    public static List<ConfigPension> ConfigPension.findAllConfigPensions() {
        return entityManager().createQuery("SELECT o FROM ConfigPension o", ConfigPension.class).getResultList();
    }
    
    public static ConfigPension ConfigPension.findConfigPension(Long id) {
        if (id == null) return null;
        return entityManager().find(ConfigPension.class, id);
    }
    
    public static List<ConfigPension> ConfigPension.findConfigPensionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ConfigPension o", ConfigPension.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}