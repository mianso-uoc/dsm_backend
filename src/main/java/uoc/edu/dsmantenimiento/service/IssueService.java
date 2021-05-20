package uoc.edu.dsmantenimiento.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Issue;
import uoc.edu.dsmantenimiento.model.Machine;
import uoc.edu.dsmantenimiento.model.Technician;
import uoc.edu.dsmantenimiento.model.User;
import uoc.edu.dsmantenimiento.model.enums.IssueStatus;
import uoc.edu.dsmantenimiento.repository.IssueRepository;
import uoc.edu.dsmantenimiento.repository.IssueRepositoryCustom;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private IssueRepositoryCustom searchIssueRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MachineService machineService;
	
	@Transactional
	public List<Issue> getIssues() {
		return issueRepository.findAll();
	}
	
	@Transactional
	public List<Issue> getIssues(Long companyId, Date startDate, Date endDate) {
		
		Company company = null;
		
		if (companyId != null) {
			Optional<Company> findCompany = companyService.getCompany(companyId);
			if (findCompany.isPresent()) {
				company = findCompany.get();
			} else {
				return null;
			}
		}
		
		return searchIssueRepository.find(company, startDate, endDate);
		
	}
	
	@Transactional
	public List<Issue> getIssuesByTechnician(long technicianId) {
		Optional<User> user = userService.getUser(technicianId);
		if (user.get() instanceof Technician) {
			Technician technician = (Technician) user.get();
			return issueRepository.findByTechnician(technician);
		} else {
			return null;
		}
	}
	
	@Transactional
	public Optional<Issue> getIssue(Long id) {
		return issueRepository.findById(id);
	}
	
	public Issue editIssue(Issue Issue) {
		return issueRepository.save(Issue);
	}
	
	public void solveIssue(Long id, String solution, Float totalPrice) {
		Optional<Issue> optionalIssue = issueRepository.findById(id);
		Issue issue = optionalIssue.get();
		issue.setStatus(IssueStatus.SOLVED);
		issue.setTotalPrice(totalPrice);
		issue.setSolution(solution);
		issue.setCloseDate(new Date());
		this.editIssue(issue);
	}
	
	public void closeIssue(Long id) {
		Optional<Issue> optionalIssue = issueRepository.findById(id);
		Issue issue = optionalIssue.get();
		issue.setStatus(IssueStatus.CLOSED);
		issue.setCloseDate(new Date());
		this.editIssue(issue);
	}
	
	@Transactional
	public void addMachineToIssue(Long idIssue, Long idMachine) {
		Optional<Issue> optionalIssue = issueRepository.findById(idIssue);
		Optional<Machine> optionalMachine = machineService.getMachine(idMachine);
		
		Issue issue = optionalIssue.get();
		Machine machine = optionalMachine.get();
		machine.getIssues().add(issue);
		issue.getMachines().add(machine);
		this.editIssue(issue);
	}
	
	@Transactional
	public void removeMachineFromIssue(Long idIssue, Long idMachine) {
		Optional<Issue> optionalIssue = issueRepository.findById(idIssue);
		Optional<Machine> optionalMachine = machineService.getMachine(idMachine);
		
		Issue issue = optionalIssue.get();
		Machine machine = optionalMachine.get();
		machine.getIssues().remove(issue);
		issue.getMachines().remove(machine);
		this.editIssue(issue);
	}
	
}
