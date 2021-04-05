package uoc.edu.dsmantenimiento.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends User {

	@ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

	public Customer() {
		super();
	}

	public Customer(String email, String name, String password) {
		super(email, name, password);
	}
	
	public Customer(long id, String email, String name, String password) {
		super(id, email, name, password);
	}
	
	public Customer(String email, String name, String password, long companyId) {
		super(email, name, password);
		Company c = new Company();
		c.setId(companyId);
		this.company = c;
	}

	public Customer(long id, String email, String name, String password, long companyId) {
		super(id, email, name, password);
		Company c = new Company();
		c.setId(companyId);
		this.company = c;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
