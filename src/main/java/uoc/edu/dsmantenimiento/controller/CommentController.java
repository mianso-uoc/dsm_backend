package uoc.edu.dsmantenimiento.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.Comment;
import uoc.edu.dsmantenimiento.model.Issue;
import uoc.edu.dsmantenimiento.service.CommentService;
import uoc.edu.dsmantenimiento.service.IssueService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	IssueService issueService;
	
	@Autowired
	CommentService commentService;

	@PostMapping("/issues/{id}/comments")
	public ResponseEntity<Comment> createComment(@PathVariable Long id, @RequestBody Comment comment) {
		try {
			Optional<Issue> issue = issueService.getIssue(id);
			if (issue.isPresent()) {
				Comment _comment = commentService.editComment(new Comment(comment.getText(), issue.get(), comment.getUser()));
				return new ResponseEntity<>(_comment, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("id") long id) {
		Optional<Comment> commentData = commentService.getComment(id);

		if (commentData.isPresent()) {
			return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}