package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

	List<Manufacturer> findByName(String name);
}
