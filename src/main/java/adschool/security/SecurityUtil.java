package adschool.security ;

/**
 * Give different security utilities on a user.
 */


import javax.persistence.TypedQuery;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class SecurityUtil {

	public static UserDetails getUserDetails(){
		SecurityContext context = SecurityContextHolder.getContext();
		if(context==null) return null;
		Authentication authentication = context.getAuthentication();
		if(authentication==null) return null;
		Object principal = authentication.getPrincipal();
		if(principal==null) return null;
		if (principal instanceof UserDetails) {
			return ((UserDetails)principal);
		} else {
			return null;
		}
	}

	public static String getUserName(){
		UserDetails userDetails = getUserDetails();
		if( userDetails != null) return userDetails.getUsername();
		
		return "system";
	}

	public static AdSchoolUser getAdSchoolUser(){
		UserDetails userDetails = getUserDetails();
		if(userDetails==null) return null;
		String username = userDetails.getUsername();
		TypedQuery<AdSchoolUser> query = AdSchoolUser.findAdSchoolUsersByUserNameEquals(username) ;
		return query.getSingleResult();
	}


}
