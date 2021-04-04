package uoc.edu.dsmantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
