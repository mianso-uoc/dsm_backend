package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import uoc.edu.dsmantenimiento.model.Administrator;
import uoc.edu.dsmantenimiento.model.Customer;
import uoc.edu.dsmantenimiento.model.Technician;
import uoc.edu.dsmantenimiento.model.User;
import uoc.edu.dsmantenimiento.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private UserRepository<Administrator> adminRepository;
	
	@Autowired
	private UserRepository<Technician> technicianRepository;
	
	@Autowired
	private UserRepository<Customer> customerRepository;
	
	@Autowired
	PasswordEncoder encoder;

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public List<User> getUsersByType(String type) {
		return userRepository.findByType(type);
	}
	
	public List<Administrator> getAdministrators() {
		return adminRepository.findAll();
	}
	
	public List<Technician> getTechnicians() {
		return technicianRepository.findAll();
	}
	
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public List<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<Customer> getCustomer(Long id) {
		return customerRepository.findById(id);
	}
	
	public User editUser(User user) {
		return userRepository.save(user);
	}
	
	public Technician editTechnician(Technician technician) {
		
		if (!StringUtils.hasLength(technician.getPassword())) {
			technician.setPassword(getUser(technician.getId()).get().getPassword());
		} else {
			String encoded = encoder.encode(technician.getPassword());
			technician.setPassword(encoded);
		}
		
		return technicianRepository.save(technician);
	}
	
	public Customer editCustomer(Customer customer) {
		
		if (!StringUtils.hasLength(customer.getPassword())) {
			customer.setPassword(getUser(customer.getId()).get().getPassword());
		} else {
			String encoded = encoder.encode(customer.getPassword());
			customer.setPassword(encoded);
		}
		
		return customerRepository.save(customer);
	}
	
	public Administrator editAdministrator(Administrator administrator) {
		
		if (!StringUtils.hasLength(administrator.getPassword())) {
			administrator.setPassword(getUser(administrator.getId()).get().getPassword());
		} else {
			String encoded = encoder.encode(administrator.getPassword());
			administrator.setPassword(encoded);
		}
		
		return adminRepository.save(administrator);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
