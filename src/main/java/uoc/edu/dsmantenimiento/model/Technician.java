package uoc.edu.dsmantenimiento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Technician extends User {

	@JsonIgnore
	@OneToMany(mappedBy = "technician", cascade = CascadeType.ALL)
    private List<Issue> issues;

	public Technician() {
		super();
	}

	public Technician(String email, String name, String password) {
		super(email, name, password);
	}
	
	public Technician(long id, String email, String name, String password) {
		super(id, email, name, password);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
}
