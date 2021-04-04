package uoc.edu.dsmantenimiento.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "machine")
public class Machine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "serialNumber")
	private String serialNumber;
	
	@ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
	
	@JsonIgnore
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "issue_machine",
            joinColumns = {@JoinColumn(name = "machine_id")},
            inverseJoinColumns = {@JoinColumn(name = "issue_id")}
	)
	private Set<Issue> issues;
	
	@ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
	
	public Machine() {	}

	public Machine(String serialNumber, Company company, Product product) {
		super();
		this.serialNumber = serialNumber;
		this.company = company;
		this.product = product;
	}
	
	public Machine(long id, String serialNumber, Company company, Product product) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.company = company;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", serialNumber=" + serialNumber + "]";
	}
}
