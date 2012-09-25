package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import adschool.domain.Division;
import javax.persistence.Enumerated;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

import adschool.domain.TypeEtablissement;
import adschool.utils.FileUploadService;
import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Etablissement extends SchoolBaseEntity{

    private String etablissementKey;

    private String nomEtablissement;

    private String nomAbreger;

    private String nomResponsable;

    private String signatureResponsable;

    private String logoPath;

    private String adresse;

    private String boitePostale;

    private String ville;

    private String phoneMobile;

    private String phoneFixe;

    private String siteWeb;

    private String email;

    private String ministere;

    private String typeResponsable;
    
    @Transient
    private MultipartFile logoFile ;

    @Enumerated
    private Division division;

    @Enumerated
    private TypeEtablissement typeEtablissement;
    
    @PostPersist
    public void postPersit() {
        etablissementKey = NumberGenerator.getNumber("ETS-", getId(), 4);
    }
    
    public void validate(BindingResult bindingResult) {
        if (!FileUploadService.isValidFileFormat(logoFile)) {
            ObjectError error = new ObjectError("logoFile", "the logo file  format must be "+FileUploadService.getValidFileFormat());
            bindingResult.addError(error);
        }
    }
    
}
