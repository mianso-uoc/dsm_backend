package uoc.edu.dsmantenimiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Document;
import uoc.edu.dsmantenimiento.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	public Optional<Document> getDocument(Long id) {
		return documentRepository.findById(id);
	}
	
	public Document editDocument(Document Document) {
		return documentRepository.save(Document);
	}
	
	public void deleteDocument(Long id) {
		documentRepository.deleteById(id);
	}
	
}
