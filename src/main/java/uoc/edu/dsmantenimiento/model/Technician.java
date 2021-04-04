package uoc.edu.dsmantenimiento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Technician extends User {

	@JsonIgnore
	@OneToMany(mappedBy = "technician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues;

	public Technician() {
		super();
	}

	public Technician(String email, String name) {
		super(email, name);
	}
	
	public Technician(long id, String email, String name) {
		super(id, email, name);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
}
