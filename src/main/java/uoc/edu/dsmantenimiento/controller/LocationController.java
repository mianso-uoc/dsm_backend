package uoc.edu.dsmantenimiento.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.City;
import uoc.edu.dsmantenimiento.model.Country;
import uoc.edu.dsmantenimiento.model.Province;
import uoc.edu.dsmantenimiento.service.CityService;
import uoc.edu.dsmantenimiento.service.CountryService;
import uoc.edu.dsmantenimiento.service.ProvinceService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class LocationController {

	@Autowired
	CountryService countryService;
	
	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	CityService cityService;

	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getCountries() {
		try {
			List<Country> countries = countryService.getCountries();
			
			if (countries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(countries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/countries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable("id") long id) {
		Optional<Country> country = countryService.getCountry(id);

		if (country.isPresent()) {
			return new ResponseEntity<>(country.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/countries/{countryId}/provinces")
	public ResponseEntity<List<Province>> getProvinces(@PathVariable Long countryId) {
		try {
			List<Province> provinces = provinceService.getProvincesByCountry(countryId);
			
			if (provinces.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(provinces, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/provinces/{id}")
	public ResponseEntity<Province> getProvinceById(@PathVariable("id") long id) {
		Optional<Province> province = provinceService.getProvince(id);

		if (province.isPresent()) {
			return new ResponseEntity<>(province.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/provinces/{provinceId}/cities")
	public ResponseEntity<List<City>> getCities(@PathVariable Long provinceId) {
		try {
			List<City> cities = cityService.getCitiesByProvince(provinceId);
			
			if (cities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cities, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cities/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") long id) {
		Optional<City> province = cityService.getCity(id);

		if (province.isPresent()) {
			return new ResponseEntity<>(province.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
