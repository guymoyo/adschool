// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Classe;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Matiere_Roo_Finder {
    
    public static TypedQuery<Matiere> Matiere.findMatieresByClasse(Classe classe) {
        if (classe == null) throw new IllegalArgumentException("The classe argument is required");
        EntityManager em = Matiere.entityManager();
        TypedQuery<Matiere> q = em.createQuery("SELECT o FROM Matiere AS o WHERE o.classe = :classe", Matiere.class);
        q.setParameter("classe", classe);
        return q;
    }
    
    public static TypedQuery<Matiere> Matiere.findMatieresByFamille(FamilleMatiere famille) {
        if (famille == null) throw new IllegalArgumentException("The famille argument is required");
        EntityManager em = Matiere.entityManager();
        TypedQuery<Matiere> q = em.createQuery("SELECT o FROM Matiere AS o WHERE o.famille = :famille", Matiere.class);
        q.setParameter("famille", famille);
        return q;
    }
    
    public static TypedQuery<Matiere> Matiere.findMatieresByGroupe(GroupeMatiere groupe) {
        if (groupe == null) throw new IllegalArgumentException("The groupe argument is required");
        EntityManager em = Matiere.entityManager();
        TypedQuery<Matiere> q = em.createQuery("SELECT o FROM Matiere AS o WHERE o.groupe = :groupe", Matiere.class);
        q.setParameter("groupe", groupe);
        return q;
    }
    
}
