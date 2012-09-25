package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.AnneeScolaire;
import javax.persistence.ManyToOne;
import adschool.domain.Inscription;

@RooJavaBean
@RooToString
@RooEntity
public class Moyenneannuelles {

    @ManyToOne
    private AnneeScolaire annee;

    @ManyToOne
    private Inscription inscription;

    private Long rang;

    private Double valeur;

    private String mention;

    private String decision;
}
