package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.beans.factory.annotation.Value;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Machines extends SchoolBaseEntity {

    private String machineKey;

    private String nomMachine;

    private String IpMachine;

    private String adresseMac;

    @Value("true")
    private Boolean autorised;

    private String observation;
    
    @PostPersist
    public void postPersit() {
        machineKey = NumberGenerator.getNumber("MC-", getId(), 4);
    }
}
