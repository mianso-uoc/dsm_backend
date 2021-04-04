package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<User> getUsers() {
		return userRepository.findAll();
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
	
	public User editUser(User user) {
		return userRepository.save(user);
	}
	
	public Technician editTechnician(Technician technician) {
		return technicianRepository.save(technician);
	}
	
	public Customer editCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Administrator editAdministrator(Administrator administrator) {
		return adminRepository.save(administrator);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
