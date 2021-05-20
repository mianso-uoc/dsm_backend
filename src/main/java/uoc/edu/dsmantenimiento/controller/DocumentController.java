package uoc.edu.dsmantenimiento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.Document;
import uoc.edu.dsmantenimiento.model.Issue;
import uoc.edu.dsmantenimiento.service.DocumentService;
import uoc.edu.dsmantenimiento.service.IssueService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	@Autowired
	IssueService issueService;

	@GetMapping("/issues/{issueId}/documents")
	public ResponseEntity<List<Document>> getDocumentsByIssue(@PathVariable Long issueId) {
		try {
			Optional<Issue> issue = issueService.getIssue(issueId);
			List<Document> documents = documentService.getDocuments(issue.get());

			if (documents.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(documents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/issues/{id}/documents")
	public ResponseEntity<Document> createDocument(@PathVariable Long id, @RequestBody Document document) {
		try {
			Optional<Issue> issue = issueService.getIssue(id);
			if (issue.isPresent()) {
				Document _document = documentService.editDocument(new Document(document.getFileName(), document.getFile(), issue.get(), document.getTechnician(), document.getMimetype()));
				return new ResponseEntity<>(_document, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/documents/{id}")
	public ResponseEntity<Document> getDocumentById(@PathVariable("id") long id) {
		Optional<Document> documentData = documentService.getDocument(id);

		if (documentData.isPresent()) {
			return new ResponseEntity<>(documentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/documents/{id}")
	public ResponseEntity<HttpStatus> deleteDocument(@PathVariable("id") long id) {
		try {
			documentService.deleteDocument(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}