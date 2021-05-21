package uoc.edu.dsmantenimiento.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.Issue;
import uoc.edu.dsmantenimiento.service.IssueService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class IssueController {

	@Autowired
	IssueService issueService;

	@GetMapping("/issues")
	public ResponseEntity<List<Issue>> getIssues() {
		try {
			List<Issue> issues = issueService.getIssues();

			if (issues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(issues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/issues/{startDate}/{endDate}")
	public ResponseEntity<List<Issue>> getIssuesByDate(@PathVariable Long startDate, @PathVariable Long endDate) {
		try {
			Date start = null;
			Date end = null;
			
			if (startDate != null) {
				start = new Date();
				start.setTime(startDate);
			}
			
			if (endDate != null) {
				end = new Date();
				end.setTime(endDate);
			}
			
			List<Issue> issues = issueService.getIssues(null, start, end);

			if (issues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(issues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/issues/{startDate}/{endDate}/{companyId}")
	public ResponseEntity<List<Issue>> findIssues(@PathVariable(required = false) Long startDate, @PathVariable(required = false) Long endDate, @PathVariable(required = false) Long companyId) {
		try {
			
			Date start = null;
			Date end = null;
			
			if (startDate != null) {
				start = new Date();
				start.setTime(startDate);
			}
			
			if (endDate != null) {
				end = new Date();
				end.setTime(endDate);
			}
			
			List<Issue> issues = issueService.getIssues(companyId, start, end);

			if (issues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(issues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/issues/company/{companyId}")
	public ResponseEntity<List<Issue>> findIssuesByCompany(@PathVariable Long companyId) {
		try {
			List<Issue> issues = issueService.getIssues(companyId, null, null);

			if (issues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(issues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/issues/technician/{technicianId}")
	public ResponseEntity<List<Issue>> getIssuesByTechnician(@PathVariable Long technicianId) {
		try {
			List<Issue> issues = issueService.getIssuesByTechnician(technicianId);

			if (issues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(issues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/issues/{id}")
	public ResponseEntity<Issue> getIssueById(@PathVariable("id") long id) {
		Optional<Issue> issueData = issueService.getIssue(id);

		if (issueData.isPresent()) {
			return new ResponseEntity<>(issueData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/issues")
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
		try {
			Issue _issue = issueService.editIssue(new Issue(issue.getTitle(), issue.getDescription(), issue.getTechnician(), issue.getCompany()));
			return new ResponseEntity<>(_issue, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/issues/{id}")
	public ResponseEntity<Issue> updateIssue(@PathVariable("id") long id, @RequestBody Issue issue) {
		Optional<Issue> issueData = issueService.getIssue(id);

		if (issueData.isPresent()) {
			Issue _issue = issueData.get();
			_issue.setTitle(issue.getTitle());
			_issue.setCompany(issue.getCompany());
			_issue.setCreateDate(issue.getCreateDate());
			_issue.setDescription(issue.getDescription());
			_issue.setStatus(issue.getStatus());
			_issue.setTechnician(issue.getTechnician());
			_issue.setSolution(issue.getSolution());
			_issue.setTotalPrice(issue.getTotalPrice());
			_issue.setDescription(issue.getDescription());
			return new ResponseEntity<>(issueService.editIssue(_issue), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/issues/{id}/close")
	public ResponseEntity<HttpStatus> closeIssue(@PathVariable("id") long id) {
		try {
			issueService.closeIssue(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/issues/{id}/solve")
	public ResponseEntity<HttpStatus> solveIssue(@PathVariable("id") long id, @RequestBody Issue issue) {
		try {
			issueService.solveIssue(id, issue.getSolution(), issue.getTotalPrice());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/issues/{issueId}/machine/{machineId}")
	public ResponseEntity<HttpStatus> addMachineToIssue(@PathVariable("issueId") long issueId, @PathVariable("machineId") long machineId) {
		try {
			issueService.addMachineToIssue(issueId, machineId);;
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/issues/{issueId}/machine/{machineId}")
	public ResponseEntity<HttpStatus> removeMachineFromIssue(@PathVariable("issueId") long issueId, @PathVariable("machineId") long machineId) {
		try {
			issueService.removeMachineFromIssue(issueId, machineId);;
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}