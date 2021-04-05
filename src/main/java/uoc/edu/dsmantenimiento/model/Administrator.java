package uoc.edu.dsmantenimiento.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {

	public Administrator() {
		super();
	}
	
	public Administrator(String email, String name, String password) {
		super(email, name, password);
	}

	public Administrator(long id, String email, String name, String password) {
		super(id, email, name, password);
	}

	
}
