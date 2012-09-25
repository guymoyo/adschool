package adschool.security;

import adschool.domain.SchoolBaseEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import adschool.domain.Genre;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Set;
import adschool.domain.Etablissement;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import adschool.domain.RoleNames;
import adschool.utils.NumberGenerator;

import javax.persistence.ElementCollection;

@RooJavaBean
@RooToString
@RooEntity(inheritanceType = "TABLE_PER_CLASS", finders = { "findAdSchoolUsersByFullNameEquals", "findAdSchoolUsersByUserNameEquals" })
public class AdSchoolUser extends SchoolBaseEntity {

    private String userName;

    private String userNumber;

    private String firstName;

    private String lastName;

    private String fullName;

    private String password;

    private String phoneNumber;
    
    // @Pattern(regexp = "[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")

    @Enumerated
    private Genre genre;

    private Boolean diseableLogin;

    private Boolean accounlLocked;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date credentialExpiration = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date accountExpiration = new Date();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etablissement> etablissement = new HashSet<Etablissement>();


    @PostPersist
    public void postPersit() {
    	userNumber = NumberGenerator.getNumber("USER-", getId(), 7);
    }
    
    
    @ElementCollection
    private Set<RoleNames> roleName = new HashSet<RoleNames>();

    private void changePasswordInternal(String newPassword) {
        this.password = encodePassword(newPassword);
    }

    private String encodePassword(String input) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        md5PasswordEncoder.setEncodeHashAsBase64(false);
        return md5PasswordEncoder.encodePassword(input, PASSWORD_SALT);
    }
    
    @PrePersist
    public void prePersist() {
        this.password = encodePassword(password);
        
        fullName = firstName + " "+lastName;
    }
    
    @PreUpdate
    public void preUpdate() {
        
        fullName = firstName + " "+lastName;
    }

    public boolean checkExistingPasword(String input) {
        return StringUtils.equals(encodePassword(input), password);
    }

    public void changePassword(String newPassword) {
        changePasswordInternal(newPassword);
        super.merge();
    }

    public static final String PASSWORD_SALT = "ace6b4f53";
    
    
    public static TypedQuery<AdSchoolUser> findAdSchoolUsersByUserNameEquals(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = AdSchoolUser.entityManager();
        TypedQuery<AdSchoolUser> q = em.createQuery("SELECT o FROM AdSchoolUser AS o WHERE o.userName = :userName", AdSchoolUser.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    
    public static void initAdSchoolUser() {
        Long count = AdSchoolUser.countAdSchoolUsers();
        if (count == 0) {
            AdSchoolUser admin = new AdSchoolUser();
           
            admin.setUserName("admin");
            admin.setFirstName("Tchounga Njanke");
            admin.setPassword("12345");
            admin.setLastName("Stephane");
            admin.setAccounlLocked(Boolean.FALSE);
            admin.setDiseableLogin(Boolean.FALSE);
            admin.setAccountExpiration(DateUtils.addYears(new Date(), 1));
            admin.setCredentialExpiration(DateUtils.addYears(new Date(), 1));
            
            admin.setGenre(Genre.MASCULIN);
            admin.getRoleName().add(RoleNames.ROLE_ADMINISTRATEUR);
            admin.setPhoneNumber("76699918");
            admin.persist();
            
        }
    }

   
    
    
    
}
