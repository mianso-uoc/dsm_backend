package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Issue;
import uoc.edu.dsmantenimiento.model.Technician;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	List<Issue> findByTechnician(Technician technician);

}
