// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Classe;
import adschool.domain.Occupation;
import adschool.domain.Salle;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Occupation_Roo_Finder {
    
    public static TypedQuery<Occupation> Occupation.findOccupationsByClasse(Classe classe) {
        if (classe == null) throw new IllegalArgumentException("The classe argument is required");
        EntityManager em = Occupation.entityManager();
        TypedQuery<Occupation> q = em.createQuery("SELECT o FROM Occupation AS o WHERE o.classe = :classe", Occupation.class);
        q.setParameter("classe", classe);
        return q;
    }
    
    public static TypedQuery<Occupation> Occupation.findOccupationsBySalle(Salle salle) {
        if (salle == null) throw new IllegalArgumentException("The salle argument is required");
        EntityManager em = Occupation.entityManager();
        TypedQuery<Occupation> q = em.createQuery("SELECT o FROM Occupation AS o WHERE o.salle = :salle", Occupation.class);
        q.setParameter("salle", salle);
        return q;
    }
    
}