package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.domain.Evaluation;
import javax.persistence.ManyToOne;
import adschool.domain.Inscription;
import adschool.utils.NumberGenerator;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class ConsigneEleves extends SchoolBaseEntity{

    private String consigneKey;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private Inscription inscriptionEleve;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateConsigne;
    
    @PostPersist
    public void postPersit() {
        consigneKey = NumberGenerator.getNumber("CO-", getId(), 4);
    }
}
