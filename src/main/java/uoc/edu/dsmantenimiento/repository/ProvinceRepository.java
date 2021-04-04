package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Country;
import uoc.edu.dsmantenimiento.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

	List<Province> findByCountry(Country country);
}
