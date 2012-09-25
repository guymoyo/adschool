package adschool.domain;

import javax.persistence.PostPersist;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import adschool.utils.NumberGenerator;

@RooJavaBean
@RooToString
@RooEntity
public class Regime extends SchoolBaseEntity{
	private String regimeKey;
	private String libelle;
	@PostPersist
	public void postPersit() {
		regimeKey = NumberGenerator.getNumber("RG-", getId(), 4);
	}
	public static void initRegimes(){
		long count = Regime.countRegimes();
		if (count < 1) {
			Regime regime = new Regime();
			regime.setLibelle("JOUR");
			regime.persist();
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getLibelle());
		return sb.toString();
	}
}
