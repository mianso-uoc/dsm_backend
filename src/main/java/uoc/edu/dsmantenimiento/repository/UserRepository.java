package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.User;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

	List<User> findByName(String name);
}
