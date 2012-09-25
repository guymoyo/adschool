package adschool.domain;

import java.util.List;

import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Mentions extends SchoolBaseEntity{

    private String mentionKey;

    private String intitule;

    private double valueMin;

    private double valueMax;
    
    @PostPersist
    public void postPersit() {
        mentionKey = NumberGenerator.getNumber("MT-", getId(), 4);
    }
    
    
public static boolean existe(double valueMin, double valueMax) {
   	
	 for(Mentions  men : Mentions.findAllMentionses()){

	        if((men.getValueMin() <= valueMin && valueMin <= men.getValueMax()) && (men.getValueMin() <= valueMax && valueMin <= men.getValueMax()) ){

	            return true;
	        }
	    }
	
	
	return false;
}

public static String getMention(double moyenne){

    String mention = "";

    for(Mentions  men : Mentions.findAllMentionses()){

        if(men.getValueMin() <= moyenne && men.getValueMax() > moyenne){

            mention = men.getIntitule();
        }
    }

    return mention;
    
}
    
    
    
    
    
    
}
