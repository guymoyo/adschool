package adschool.beans;

import adschool.domain.Inscription;

public class InscriptionProcessBean {
	
	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	private Inscription inscription ;
	

	private long studentId;



	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	

	

}
