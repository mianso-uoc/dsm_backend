package uoc.edu.dsmantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uoc.edu.dsmantenimiento.model.Manufacturer;
import uoc.edu.dsmantenimiento.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByManufacturer(Manufacturer manufacturer);
}
