package adschool.domain;

import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class PasswordReset {
	
    private String id;
	
	private String version;

    private String userName;

    private String passwordCurrent;

    private String newPassword;

    private String confirmNewPassword;
    
    public boolean passwordsEqual(){
		return StringUtils.equals(newPassword, confirmNewPassword);
	}
}
