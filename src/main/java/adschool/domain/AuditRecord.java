package adschool.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class AuditRecord extends SchoolBaseEntity{

    private String bussinessName;

    private String bussinessKey;

    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creeLe;

    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateLe;

    private String details;
    
 public AuditRecord(FootPrint footPrint){
    	
    	this.bussinessKey = footPrint.getBussinessKey();
    	this.bussinessName= footPrint.getBussinessName();
    	this.creeLe =  footPrint.getCreeLe();
    	this.createBy = footPrint.getCreateBy();
    	this.updateLe = footPrint.getUpdateLe();
    	this.updateBy = footPrint.getUpdateBy();
    }
}
