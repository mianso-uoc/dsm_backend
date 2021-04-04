package uoc.edu.dsmantenimiento.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {

	public Administrator() {
		super();
	}
	
	public Administrator(String email, String name) {
		super(email, name);
	}

	public Administrator(long id, String email, String name) {
		super(id, email, name);
	}

	
}
