package adschool.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import adschool.domain.AnneeScolaire;
import adschool.domain.Etablissement;
import adschool.security.SecurityUtil;

public class ProcessHelper {
	
	
	public static List<Etablissement> getListeEtablissement(){
		
		if (SecurityUtil.getAdSchoolUser() != null){
			
			return new ArrayList<Etablissement> (SecurityUtil.getAdSchoolUser().getEtablissement());
			
		}else{
			
			return null;
		}
		
	}
	
	
	public static AnneeScolaire getAnneeCourante(){
		
		return AnneeScolaire.getCurrentAnneeScolaire();
	}
	

	public static void deconnexion(HttpServletRequest httpServletRequest) {
		
		String idPoste = httpServletRequest.getServerName();
		
		System.out.println("1"+httpServletRequest.getServerName());

		System.out.println("2"+httpServletRequest.getLocalName());


		System.out.println("3"+httpServletRequest.getLocalAddr());

		
		HttpSession session = httpServletRequest.getSession();
		
		//session.invalidate();
	}

}
