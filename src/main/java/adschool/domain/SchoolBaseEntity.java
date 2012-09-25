package adschool.domain;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooToString
@RooEntity(mappedSuperclass = true)
public abstract class SchoolBaseEntity {
	
/*	@Embedded
	protected  FootPrint footPrint = new FootPrint();
	
	  @PrePersist
	    public void prePersist(){
			internalPrePersist();
			initFootPrint();
	    }
	    
	    @PostPersist
	    public void postPersist(){
			internalPostPersit();


	    }
	    
	    @PreUpdate
	    public void preUpdate(){
			internalPreUpdate();   
			gatherFootPrint();
	    }

	    // use for protect somme field before update
public void protectSomeField(){
			
		}
	    
	    protected void gatherFootPrint() {
	    	FootPrint footPrint2 = new FootPrint();
	    	footPrint2.setCreeLe(footPrint.getCreeLe());
	    	footPrint2.setUpdateLe(new Date());
	    	footPrint2.setCreateBy(footPrint.getCreateBy());
	    	footPrint2.setUpdateBy(footPrint.getUpdateBy());
	    	footPrint2.setBussinessName(footPrint.getBussinessName());
	    	footPrint2.setBussinessKey(footPrint.getBussinessKey());
	    	AuditRecord auditRecord = new AuditRecord(footPrint);
	    	auditRecord.persist();
	    	this.footPrint = footPrint2;
		}
	    
	    private void initFootPrint(){
	    	String userName =  null ; //SecurityUtil.getUserName();
	    	if(userName==null){
	        	footPrint.setUpdateBy("System");
	        	footPrint.setCreateBy("System");
	    	} else {
	        	footPrint.setUpdateBy(userName);
	        	footPrint.setCreateBy(userName);

	    	}
	    	footPrint.setCreeLe(new Date());
	    	footPrint.setUpdateLe(new Date());
	    	footPrint.setBussinessName(getClass().getSimpleName());
	    	footPrint.setBussinessKey(getBusinessKey());
	    }

	    protected String getBusinessKey(){
			return "BaseEntity";
		}

		protected void internalPreUpdate() {
			
		}
		
       protected void internalPostPersit() {
			
		}
		
		*//**
	     * Implement this for pre persist in subclasses
	     *//*
	    protected void internalPrePersist(){
	    	
	    }*/
	
	
}



