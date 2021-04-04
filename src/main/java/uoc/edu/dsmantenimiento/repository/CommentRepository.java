package uoc.edu.dsmantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
