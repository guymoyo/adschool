package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

import adschool.domain.Classe;
import javax.persistence.ManyToOne;
import java.util.Set;
import adschool.domain.Document;
import adschool.utils.NumberGenerator;

import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.PostPersist;

@RooJavaBean
@RooEntity(finders = { "findDocInscriptionsByActif", "findDocInscriptionsByClasse" })
public class DocInscription extends SchoolBaseEntity {

    private String docKey;

    @ManyToOne
    private Classe classe;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Document> listeDocuments = new HashSet<Document>();

    @Override
    public String toString() {
        return "[" + classe + listeDocuments + "]";
    }
    
    @PostPersist
    public void postPersit() {
    	docKey = NumberGenerator.getNumber("DCI-", getId(), 4);
    }
}
