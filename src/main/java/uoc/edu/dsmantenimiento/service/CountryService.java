package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Country;
import uoc.edu.dsmantenimiento.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getCountries() {
		return countryRepository.findAll();
	}
	
	public Optional<Country> getCountry(Long id) {
		return countryRepository.findById(id);
	}
	
	public Country editCountry(Country country) {
		return countryRepository.save(country);
	}
	
	public void deleteCountry(Long id) {
		countryRepository.deleteById(id);
	}
}
