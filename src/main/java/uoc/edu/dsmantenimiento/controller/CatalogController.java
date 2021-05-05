package uoc.edu.dsmantenimiento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.Manufacturer;
import uoc.edu.dsmantenimiento.model.Product;
import uoc.edu.dsmantenimiento.service.ManufacturerService;
import uoc.edu.dsmantenimiento.service.ProductService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class CatalogController {

	@Autowired
	ManufacturerService manufacturerService;
	
	@Autowired
	ProductService productService;

	@GetMapping("/manufacturers")
	public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
		try {
			List<Manufacturer> manufacturers = manufacturerService.getManufacturers();

			if (manufacturers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(manufacturers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/manufacturers/{id}")
	public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") long id) {
		Optional<Manufacturer> manufacturerData = manufacturerService.getManufacturer(id);

		if (manufacturerData.isPresent()) {
			return new ResponseEntity<>(manufacturerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/manufacturers")
	public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
		try {
			Manufacturer _manufacturer = manufacturerService.editManufacturer(new Manufacturer(manufacturer.getName()));
			return new ResponseEntity<>(_manufacturer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/manufacturers/{id}")
	public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable("id") long id, @RequestBody Manufacturer manufacturer) {
		Optional<Manufacturer> manufacturerData = manufacturerService.getManufacturer(id);

		if (manufacturerData.isPresent()) {
			Manufacturer _manufacturer = manufacturerData.get();
			_manufacturer.setName(manufacturer.getName());
			return new ResponseEntity<>(manufacturerService.editManufacturer(_manufacturer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/manufacturers/{id}")
	public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable("id") long id) {
		try {
			manufacturerService.deleteManufacturer(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/manufacturers/{manufacturerId}/products")
	public ResponseEntity<List<Product>> getProductsByManufacturer(@PathVariable("manufacturerId") long manufacturerId) {
		try {
			List<Product> products = productService.getProductsByManufacturer(manufacturerId);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		Optional<Product> product = productService.getProduct(id);

		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product _product = productService.editProduct(new Product(product.getName(), product.getManufacturer()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productService.getProduct(id);

		if (productData.isPresent()) {
			Product _product = productData.get();
			_product.setName(product.getName());
			_product.setManufacturer(product.getManufacturer());
			return new ResponseEntity<>(productService.editProduct(_product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
