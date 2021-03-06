// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.security;

import adschool.security.AdSchoolUser;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect AdSchoolUser_Roo_Finder {
    
    public static TypedQuery<AdSchoolUser> AdSchoolUser.findAdSchoolUsersByFullNameEquals(String fullName) {
        if (fullName == null || fullName.length() == 0) throw new IllegalArgumentException("The fullName argument is required");
        EntityManager em = AdSchoolUser.entityManager();
        TypedQuery<AdSchoolUser> q = em.createQuery("SELECT o FROM AdSchoolUser AS o WHERE o.fullName = :fullName", AdSchoolUser.class);
        q.setParameter("fullName", fullName);
        return q;
    }
    
}
