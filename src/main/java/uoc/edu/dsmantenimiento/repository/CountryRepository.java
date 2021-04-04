package uoc.edu.dsmantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
}
