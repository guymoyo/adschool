// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Inscription;
import java.lang.String;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Inscription_Roo_Finder {
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByAnnee(AnneeScolaire annee) {
        if (annee == null) throw new IllegalArgumentException("The annee argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.annee = :annee", Inscription.class);
        q.setParameter("annee", annee);
        return q;
    }
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByClasse(Classe classe) {
        if (classe == null) throw new IllegalArgumentException("The classe argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.classe = :classe", Inscription.class);
        q.setParameter("classe", classe);
        return q;
    }
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByDateInscriptionBetween(Date minDateInscription, Date maxDateInscription) {
        if (minDateInscription == null) throw new IllegalArgumentException("The minDateInscription argument is required");
        if (maxDateInscription == null) throw new IllegalArgumentException("The maxDateInscription argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.dateInscription BETWEEN :minDateInscription AND :maxDateInscription", Inscription.class);
        q.setParameter("minDateInscription", minDateInscription);
        q.setParameter("maxDateInscription", maxDateInscription);
        return q;
    }
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByDateInscriptionEquals(Date dateInscription) {
        if (dateInscription == null) throw new IllegalArgumentException("The dateInscription argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.dateInscription = :dateInscription", Inscription.class);
        q.setParameter("dateInscription", dateInscription);
        return q;
    }
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByEleve(Eleve eleve) {
        if (eleve == null) throw new IllegalArgumentException("The eleve argument is required");
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE o.eleve = :eleve", Inscription.class);
        q.setParameter("eleve", eleve);
        return q;
    }
    
    public static TypedQuery<Inscription> Inscription.findInscriptionsByInscriptionKeyLike(String inscriptionKey) {
        if (inscriptionKey == null || inscriptionKey.length() == 0) throw new IllegalArgumentException("The inscriptionKey argument is required");
        inscriptionKey = inscriptionKey.replace('*', '%');
        if (inscriptionKey.charAt(0) != '%') {
            inscriptionKey = "%" + inscriptionKey;
        }
        if (inscriptionKey.charAt(inscriptionKey.length() - 1) != '%') {
            inscriptionKey = inscriptionKey + "%";
        }
        EntityManager em = Inscription.entityManager();
        TypedQuery<Inscription> q = em.createQuery("SELECT o FROM Inscription AS o WHERE LOWER(o.inscriptionKey) LIKE LOWER(:inscriptionKey)", Inscription.class);
        q.setParameter("inscriptionKey", inscriptionKey);
        return q;
    }
    
}