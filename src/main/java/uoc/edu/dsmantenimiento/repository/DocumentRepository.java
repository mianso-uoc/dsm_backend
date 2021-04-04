package uoc.edu.dsmantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
