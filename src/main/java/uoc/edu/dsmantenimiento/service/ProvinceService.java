package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Country;
import uoc.edu.dsmantenimiento.model.Province;
import uoc.edu.dsmantenimiento.repository.ProvinceRepository;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private CountryService countryService;
	
	public List<Province> getProvincesByCountry(long countryId) {
		Optional<Country> country = countryService.getCountry(countryId);
		return provinceRepository.findByCountry(country.get());
	}
	
	public Optional<Province> getProvince(Long id) {
		return provinceRepository.findById(id);
	}
	
	public Province editProvince(Province province) {
		return provinceRepository.save(province);
	}
	
	public void deleteProvince(Long id) {
		provinceRepository.deleteById(id);
	}
}
