package uoc.edu.dsmantenimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;
	
	@Formula("DTYPE")
	private String type;
	
	public enum Type {
		
		ADMIN("administrator"), TECHNICIAN("technician"), CUSTOMER("customer");
		
		private String value;

		public String getValue() {
			return value;
		}

		Type(String value) {
			this.value = value;
		}
	}
	
	public User() {	}

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public User(long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
}
