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

@Entity
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "fileName")
	private String fileName;
	
	@Lob
	@Column(name = "file")
	private byte[] file;
	
	@ManyToOne()
    @JoinColumn(name = "issue_id")
    private Issue issue;
	
	@ManyToOne()
    @JoinColumn(name = "technician_id")
    private Technician technician;
	
	public Document() {	}

	public Document(String fileName, byte[] file, Issue issue) {
		super();
		this.fileName = fileName;
		this.file = file;
		this.issue = issue;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", fileName=" + fileName + "]";
	}
}
