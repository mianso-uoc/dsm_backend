package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Document;
import uoc.edu.dsmantenimiento.model.Issue;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByIssue(Issue issue);
}
