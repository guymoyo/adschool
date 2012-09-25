// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Eleve;
import adschool.domain.Notes;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Notes_Roo_Finder {
    
    public static TypedQuery<Notes> Notes.findNotesesByEleve(Eleve eleve) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Notes.entityManager();
        TypedQuery<Notes> q = em.createQuery("SELECT o FROM Notes AS o WHERE o.eleve = :eleve", Notes.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
}