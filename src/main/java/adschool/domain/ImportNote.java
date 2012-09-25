package adschool.domain;

import adschool.domain.Classe;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.web.multipart.MultipartFile;
import adschool.domain.Matiere;
import adschool.domain.Evaluation;
import adschool.domain.Etablissement;

@RooJavaBean

public class ImportNote {

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private Matiere matiere;

    @Transient
    private MultipartFile fichier;

    @Transient
    private String chemin;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private Etablissement etablissement;

    public MultipartFile getFichier() {
        return fichier;
    }

    public void setFichier(MultipartFile userImage) {
        this.fichier = userImage;
    }
}
