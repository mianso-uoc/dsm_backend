package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Manufacturer;
import uoc.edu.dsmantenimiento.model.Product;
import uoc.edu.dsmantenimiento.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	public List<Product> getProductsByManufacturer(long manufacturerId) {
		Optional<Manufacturer> manufacturer = manufacturerService.getManufacturer(manufacturerId);
		return productRepository.findByManufacturer(manufacturer.get());
	}
	
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}
	
	public Product editProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
