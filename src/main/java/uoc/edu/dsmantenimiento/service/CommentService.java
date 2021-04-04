package uoc.edu.dsmantenimiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Comment;
import uoc.edu.dsmantenimiento.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public Optional<Comment> getComment(Long id) {
		return commentRepository.findById(id);
	}
	
	public Comment editComment(Comment Comment) {
		return commentRepository.save(Comment);
	}
	
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
	
}
