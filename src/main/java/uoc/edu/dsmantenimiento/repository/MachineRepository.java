package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Machine;

public interface MachineRepository extends JpaRepository<Machine, Long> {

	List<Machine> findByCompany(Company company);
}
