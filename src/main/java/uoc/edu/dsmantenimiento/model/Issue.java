package uoc.edu.dsmantenimiento.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uoc.edu.dsmantenimiento.model.enums.IssueStatus;

@Entity
@Table(name = "issue")
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private IssueStatus status;
	
	@Column(name = "createDate")
	private Date createDate;
	
	@Column(name = "closeDate")
	private Date closeDate;
	
	@Column(name = "solution")
	private String solution;
	
	@Column(name = "totalPrice")
	private Float totalPrice;
	
	@ManyToMany(mappedBy = "issues")
    private Set<Machine> machines;
	
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
	
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;
	
	@ManyToOne()
    @JoinColumn(name = "technician_id")
    private Technician technician;
	
	@ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
	
	public Issue() {	}

	public Issue(String title, String description, Technician technician, Company company) {
		super();
		this.title = title;
		this.description = description;
		this.status = IssueStatus.PENDING;
		this.createDate = new Date();
		this.technician = technician;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + "]";
	}
}
