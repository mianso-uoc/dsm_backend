package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.City;
import uoc.edu.dsmantenimiento.model.Province;
import uoc.edu.dsmantenimiento.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ProvinceService provinceService;
	
	public List<City> getCitiesByProvince(long provinceId) {
		Optional<Province> province = provinceService.getProvince(provinceId);
		return cityRepository.findByProvince(province.get());
	}
	
	public Optional<City> getCity(Long id) {
		return cityRepository.findById(id);
	}
	
	public City editCity(City city) {
		return cityRepository.save(city);
	}
	
	public void deleteCity(Long id) {
		cityRepository.deleteById(id);
	}
}
