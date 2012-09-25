package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import adschool.domain.Classe;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Regime;
import adschool.utils.NumberGenerator;
import adschool.domain.Etablissement;

@RooJavaBean
@RooToString
@RooEntity
@RooJson
public class ConfigInscription extends SchoolBaseEntity {

    private String configKey;

    @ManyToOne
    private AnneeScolaire anneeScolaire;

    @ManyToOne
    private Classe classe;

    private BigInteger montant = BigInteger.ZERO;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateLimitePaiement = new Date();

    @ManyToOne
    private Regime regime;

    @ManyToOne
    private Etablissement etablissement;

    @Transient
    private boolean appied_others;

    @PostPersist
    public void postPersit() {
        configKey = NumberGenerator.getNumber("CF-", getId(), 4);
    }

    public static ConfigInscription search(AnneeScolaire annee, Classe classe, Regime regime, Etablissement etablissement) {
        StringBuilder searchQuery = new StringBuilder("SELECT o FROM ConfigInscription AS o WHERE o.id IS NOT NULL ");
       // if (annee == null || classe == null || regime == null || etablissement == null) throw new IllegalArgumentException("The nom argument is required");
        if (annee != null) {
            searchQuery.append(" AND o.anneeScolaire =:annee ");
        }
        if (regime != null) {
            searchQuery.append(" AND o.regime =:regime ");
        }
        if (classe != null) {
            searchQuery.append(" AND o.classe=:classe ");
        }
        if (etablissement != null) {
            searchQuery.append(" AND o.etablissement =:etablissement ");
        }
        TypedQuery<ConfigInscription> q = entityManager().createQuery(searchQuery.toString(), ConfigInscription.class);
        if (annee != null) {
        	  q.setParameter("annee", annee);
        }
        if (regime != null) {
            q.setParameter("regime", regime);
        }
        if (classe != null) {
            q.setParameter("classe", classe);
        }
        if (etablissement != null) {
            q.setParameter("etablissement", etablissement);
        }
        List<ConfigInscription> list = q.getResultList();
          return   list.isEmpty() ?null:list.iterator().next();
    }
    
    
    public static List<ConfigInscription> search2(AnneeScolaire annee, Classe classe, Regime regime, Etablissement etablissement) {
        StringBuilder searchQuery = new StringBuilder("SELECT o FROM ConfigInscription AS o WHERE o.id IS NOT NULL ");
       // if (annee == null || classe == null || regime == null || etablissement == null) throw new IllegalArgumentException("The nom argument is required");
        if (annee != null) {
            searchQuery.append(" AND o.anneeScolaire =:annee ");
        }
        if (regime != null) {
            searchQuery.append(" AND o.regime =:regime ");
        }
        if (classe != null) {
            searchQuery.append(" AND o.classe=:classe ");
        }
        if (etablissement != null) {
            searchQuery.append(" AND o.etablissement =:etablissement ");
        }
        TypedQuery<ConfigInscription> q = entityManager().createQuery(searchQuery.toString(), ConfigInscription.class);
        if (annee != null) {
        	  q.setParameter("annee", annee);
        }
        if (regime != null) {
            q.setParameter("regime", regime);
        }
        if (classe != null) {
            q.setParameter("classe", classe);
        }
        if (etablissement != null) {
            q.setParameter("etablissement", etablissement);
        }
        List<ConfigInscription> list = q.getResultList();
          return   list;
    }
    
}
