package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Manufacturer;
import uoc.edu.dsmantenimiento.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	public List<Manufacturer> getManufacturers() {
		return manufacturerRepository.findAll();
	}
	
	public Optional<Manufacturer> getManufacturer(Long id) {
		return manufacturerRepository.findById(id);
	}
	
	public Manufacturer editManufacturer(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}
	
	public void deleteManufacturer(Long id) {
		manufacturerRepository.deleteById(id);
	}
}
