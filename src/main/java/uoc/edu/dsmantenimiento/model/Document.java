package uoc.edu.dsmantenimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "mimetype")
	private String mimetype;
	
	@Lob
	@Column(name = "file")
	private byte[] file;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne()
    @JoinColumn(name = "issue_id")
    private Issue issue;
	
	@ManyToOne()
    @JoinColumn(name = "technician_id")
    private Technician technician;
	
	public Document() {	}

	public Document(String fileName, byte[] file, Issue issue, Technician technician, String mimetype) {
		super();
		this.fileName = fileName;
		this.file = file;
		this.issue = issue;
		this.technician = technician;
		this.mimetype = mimetype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", fileName=" + fileName + "]";
	}
}
