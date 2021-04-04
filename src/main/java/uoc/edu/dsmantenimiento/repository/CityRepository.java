package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.City;
import uoc.edu.dsmantenimiento.model.Province;

public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByProvince(Province province);
}
