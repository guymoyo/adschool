package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import adschool.domain.Classe;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Regime;
import adschool.utils.NumberGenerator;
import adschool.domain.Etablissement;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooEntity
@RooJson
public class ConfigPension extends SchoolBaseEntity {

    private String configKey;

    @ManyToOne
    private AnneeScolaire anneeScolaire;

    @ManyToOne
    private Classe classe;

    private BigInteger montant = BigInteger.ZERO;

    private int tranche = 1;

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

    void copier_configuration(AnneeScolaire anneeD, AnneeScolaire anneeF, Etablissement etab) {
        List<ConfigPension> liste = search(anneeD, null, null, etab,null);
        for (ConfigPension con : liste) {
            ConfigPension cf = new ConfigPension();
            cf.setAnneeScolaire(con.getAnneeScolaire());
            cf.setClasse(con.getClasse());
            cf.setDateLimitePaiement(con.getDateLimitePaiement());
            cf.setEtablissement(con.getEtablissement());
            cf.setMontant(con.getMontant());
            cf.setRegime(con.getRegime());
            cf.setTranche(con.getTranche());
            cf.persist();
        }
    }

    void update_configuration(AnneeScolaire annee, int tranche, Date datelimite, Etablissement etab) {
        List<ConfigPension> liste = search(annee, null, null, etab,null);
        for (ConfigPension con : liste) {
            if (con.getTranche() == 1) {
                ConfigPension cf = new ConfigPension();
                cf.setClasse(con.getClasse());
                cf.setDateLimitePaiement(datelimite);
                cf.setEtablissement(con.getEtablissement());
                cf.setMontant(con.getMontant());
                cf.setRegime(con.getRegime());
                cf.setTranche(con.getTranche());
                cf.persist();
            }
        }
    }

    public static List<ConfigPension> search(AnneeScolaire annee, Classe classe, Regime regime, Etablissement etablissement, BigInteger tranche) {
        StringBuilder searchQuery = new StringBuilder("SELECT o FROM ConfigPension AS o WHERE o.id IS NOT NULL ");
        //if (annee == null || classe == null || regime == null || etablissement == null) throw new IllegalArgumentException("The nom argument is required");
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
        
        if (tranche != null) {
            searchQuery.append(" AND o.tranche =:tranche ");
        }
        TypedQuery<ConfigPension> q = entityManager().createQuery(searchQuery.toString(), ConfigPension.class);
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
        if (tranche != null) {
            q.setParameter("tranche", tranche.intValue());
        }
        return q.getResultList();
    }
    
    public static List<ConfigPension> search(AnneeScolaire annee, Classe classe, Regime regime, Etablissement etablissement) {
       
        return search(annee, classe, regime, etablissement,null);
    }
}
