package adschool.security ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adschool.domain.RoleNames;
/**
 * This class is a custom implementation of spring it's UserDetailsService.
 */

@Service
@Transactional
public class AdSchoolUserDetailsService implements UserDetailsService {
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		try {
			// check context
			if(StringUtils.isBlank(username)){
				throw new UsernameNotFoundException("Estate user with email not found");
			}
			TypedQuery<AdSchoolUser> typedQuery = AdSchoolUser.findAdSchoolUsersByUserNameEquals(username);
			AdSchoolUser  user = typedQuery.getSingleResult();
			
			if ( user == null)
				throw new UsernameNotFoundException("Estate user with email not found");
			
			return createUserDetails(user);
			
		} finally {
			
		}
	}

	public static UserDetails createUserDetails(AdSchoolUser  user)
			throws UsernameNotFoundException, DataAccessException 
	{
		String password = user.getPassword();
		boolean enabled = !user.getDiseableLogin();
		boolean accountNonExpired = true ;
		if (user.getAccountExpiration()!=null) {
			accountNonExpired = new Date().before(user.getAccountExpiration());	
		}
		boolean credentialsNonExpired = true ;
		if (user.getCredentialExpiration() != null) {
			
			credentialsNonExpired = new Date().before(user.getCredentialExpiration());
		}
		
		boolean accountNonLocked =!user.getAccounlLocked();
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Set<RoleNames> roles = user.getRoleName();
		for (RoleNames roleName : roles) {
			GrantedAuthorityImpl ga = new GrantedAuthorityImpl(
					roleName.name());
			authorities.add(ga);
		}
	
		UserDetails userDetails = new User(user.getUserName(), password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
		
		return userDetails;
	}
}
