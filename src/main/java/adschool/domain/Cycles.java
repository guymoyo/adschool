package adschool.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.PostPersist;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Cycles extends SchoolBaseEntity {

    private String cycleKey;

    private String libelle;

    private Integer duree;

    @PostPersist
    public void postPersit() {
        cycleKey = NumberGenerator.getNumber("CY-", getId(), 4);
    }
    
    
    public static void initCycles() {
        long count = Cycles.countCycleses();
        if (count < 1) {
            Cycles cycle = new Cycles();
            cycle.setLibelle("SUPERIEUR");
            cycle.persist();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getLibelle());
        return sb.toString();
    }
}
