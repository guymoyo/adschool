package adschool.domain;


import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import adschool.domain.Pays;
import javax.persistence.ManyToOne;
import adschool.domain.Genre;
import adschool.utils.FileUploadService;
import adschool.utils.NumberGenerator;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity(mappedSuperclass = true)
public abstract class Personne extends SchoolBaseEntity {

	
    protected String matricule="";

    private String nom="";

    private String lieuNaissance="";

    private String cni="";

    private String telephone="";

    private String email="";

    private String boitePostale="";

    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(style = "M-")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateNaissance = new Date();
    
	

    @ManyToOne
    private Pays nationalite = new Pays();

    @Enumerated
    private Genre genre;

    private String pathPhoto = "";
    
    private String password;
    
    @Transient
    private MultipartFile userImage ;
    
    public MultipartFile getUserImage() {
		return userImage;
	}

	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}

	@PostPersist
    public void postPersit() {
       internalPostPersist();
       password = matricule;
    }
	

	
	
	
	public void internalPostPersist(){
		 matricule = NumberGenerator.getNumber("PE-", getId(), 6);
	}
	
	public void validate(BindingResult bindingResult) {
        if (!FileUploadService.isValidFileFormat(userImage)) {
            ObjectError error = new ObjectError("userImage", "the user Image format must be "+FileUploadService.getValidFileFormat());
            bindingResult.addError(error);
        }
    }
}
