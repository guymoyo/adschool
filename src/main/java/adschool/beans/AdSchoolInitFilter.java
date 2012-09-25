package adschool.beans;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import adschool.domain.AnneeScolaire;
import adschool.domain.Batiment;
import adschool.domain.Cycles;
import adschool.domain.DocInscription;
import adschool.domain.Document;
import adschool.domain.Pays;
import adschool.domain.Regime;
import adschool.security.AdSchoolUser;
import adschool.security.SecurityUtil;
import adschool.utils.ProcessHelper;

@Service
@Transactional
public class AdSchoolInitFilter extends OncePerRequestFilter {
	boolean done=false;
	@Override
	protected void doFilterInternal(HttpServletRequest arg0,HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		
		
		if(!done){
			Cycles.initCycles();
			Batiment.initBatiments();
			Document.initDocument();
			Regime.initRegimes();
			Pays.initCountrys();
			AnneeScolaire.initSchoolYears();
			
			AdSchoolUser.initAdSchoolUser();
		
			done=true;
			
			
			}
		
	
	//	ProcessHelper.deconnexion(arg0);
		
		
		arg2.doFilter(arg0, arg1);
	}

}
