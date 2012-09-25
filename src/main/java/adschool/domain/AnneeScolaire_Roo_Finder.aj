// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AnneeScolaire;
import java.lang.Boolean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect AnneeScolaire_Roo_Finder {
    
    public static TypedQuery<AnneeScolaire> AnneeScolaire.findAnneeScolairesByActif(Boolean actif) {
        if (actif == null) throw new IllegalArgumentException("The actif argument is required");
        EntityManager em = AnneeScolaire.entityManager();
        TypedQuery<AnneeScolaire> q = em.createQuery("SELECT o FROM AnneeScolaire AS o WHERE o.actif = :actif", AnneeScolaire.class);
        q.setParameter("actif", actif);
        return q;
    }
    
}
