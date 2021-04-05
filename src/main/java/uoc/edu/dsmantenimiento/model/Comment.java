package uoc.edu.dsmantenimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "text")
	private String text;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne()
    @JoinColumn(name = "issue_id")
    private Issue issue;
	
	@ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
	
	public Comment() {	}

	public Comment(String text, Issue issue, User user) {
		super();
		this.text = text;
		this.user = user;
		this.issue = issue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + "]";
	}
}
