package uoc.edu.dsmantenimiento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.service.CompanyService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping("/companies")
	public ResponseEntity<List<Company>> getAllCompanies() {
		try {
			List<Company> companies = companyService.getCompanies();

			if (companies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/companies/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
		Optional<Company> companyData = companyService.getCompany(id);

		if (companyData.isPresent()) {
			return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/companies")
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		try {
			Company _company = companyService.editCompany(new Company(company.getName(), company.getAddress(), company.getLatitude(), company.getLongitude(), company.getPhone(), company.getCity()));
			return new ResponseEntity<>(_company, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/companies/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
		Optional<Company> companyData = companyService.getCompany(id);

		if (companyData.isPresent()) {
			Company _company = companyData.get();
			_company.setName(company.getName());
			_company.setAddress(company.getAddress());
			_company.setCity(company.getCity());
			_company.setPhone(company.getPhone());
			_company.setLatitude(company.getLatitude());
			_company.setLongitude(company.getLongitude());
			return new ResponseEntity<>(companyService.editCompany(_company), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/companies/{id}")
	public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
		try {
			companyService.deleteCompany(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(DataIntegrityViolationException e) { 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
