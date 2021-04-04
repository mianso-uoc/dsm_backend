package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}
	
	public Optional<Company> getCompany(Long id) {
		return companyRepository.findById(id);
	}
	
	public Company editCompany(Company Company) {
		return companyRepository.save(Company);
	}
	
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}
	
}
