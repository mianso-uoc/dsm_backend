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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.Constants;
import uoc.edu.dsmantenimiento.model.Administrator;
import uoc.edu.dsmantenimiento.model.Customer;
import uoc.edu.dsmantenimiento.model.Technician;
import uoc.edu.dsmantenimiento.model.User;
import uoc.edu.dsmantenimiento.service.UserService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		try {
			List<User> users = userService.getUsers();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/type/{type}")
	public ResponseEntity<List<User>> getUsersByType(@PathVariable String type) {
		try {
			List<User> users = userService.getUsersByType(type);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		Optional<User> userData = userService.getUser(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam(required = false) Long companyId) {
		try {
			
			if (Constants.TECHNICIAN.equals(user.getType())) {
				Technician t = userService.editTechnician(new Technician(user.getEmail(), user.getName(), user.getPassword()));
				return new ResponseEntity<>(t, HttpStatus.CREATED);
			} else if (Constants.CUSTOMER.equals(user.getType()) && companyId != null){
				Customer c = userService.editCustomer(new Customer(user.getEmail(), user.getName(), user.getPassword(), companyId));
				return new ResponseEntity<>(c, HttpStatus.CREATED);
			} else if (Constants.ADMIN.equals(user.getType())) {
				Administrator a = userService.editAdministrator(new Administrator(user.getEmail(), user.getName(), user.getPassword()));
				return new ResponseEntity<>(a, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> editUser(@PathVariable("id") long id, @RequestBody User user, @RequestParam(required = false) Long companyId) {
		try {
			
			if (Constants.TECHNICIAN.equals(user.getType())) {
				Technician t = userService.editTechnician(new Technician(id, user.getEmail(), user.getName(), user.getPassword()));
				return new ResponseEntity<>(t, HttpStatus.CREATED);
			} else if (Constants.CUSTOMER.equals(user.getType()) && companyId != null) {
				Customer c = userService.editCustomer(new Customer(id, user.getEmail(), user.getName(), user.getPassword(), companyId));
				return new ResponseEntity<>(c, HttpStatus.CREATED);
			} else if (Constants.CUSTOMER.equals(user.getType())) {
				Customer dbCustomer = userService.getCustomer(id).get();
				Customer c = userService.editCustomer(new Customer(id, user.getEmail(), user.getName(), user.getPassword(), dbCustomer.getCompany().getId()));
				return new ResponseEntity<>(c, HttpStatus.CREATED);
			} else if (Constants.ADMIN.equals(user.getType())) {
				Administrator a = userService.editAdministrator(new Administrator(id, user.getEmail(), user.getName(), user.getPassword()));
				return new ResponseEntity<>(a, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
