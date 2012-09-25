package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean

@RooEntity
public class Document extends SchoolBaseEntity{

	private String documentkey = "";
	
    private String libelle;

    private Boolean actif;
    
    @PostPersist
    public void postPersit() {
    	documentkey = NumberGenerator.getNumber("DC-", getId(), 4);
    }

    public static void initDocument() {
        long count = Document.countDocuments();
        if (count < 1) {
            Document doc = new Document();
            doc.setLibelle("Bulletin");
            doc.setActif(true);
            doc.persist();
        }
    }

	@Override
	public String toString() {
		return libelle;
	}
    
    

}
