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
import org.springframework.web.bind.annotation.RestController;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Machine;
import uoc.edu.dsmantenimiento.service.CompanyService;
import uoc.edu.dsmantenimiento.service.MachineService;

@CrossOrigin(origins = {"http://localhost:8082", "https://dsm-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api")
public class MachineController {
	
	@Autowired
	MachineService machineService;
	
	@Autowired
	CompanyService companyService;

	@GetMapping("/companies/{companyId}/machines")
	public ResponseEntity<List<Machine>> getMachinesByCompany(@PathVariable("companyId") long companyId) {
		try {
			Optional<Company> company = companyService.getCompany(companyId);
			List<Machine> machines = machineService.getMachinesByCompany(company.get());

			if (machines.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(machines, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/machines/{id}")
	public ResponseEntity<Machine> getMachine(@PathVariable("id") long id) {
		Optional<Machine> machine = machineService.getMachine(id);

		if (machine.isPresent()) {
			return new ResponseEntity<>(machine.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/companies/machines/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable("id") long id) {
		Optional<Machine> machine = machineService.getMachine(id);

		if (machine.isPresent()) {
			return new ResponseEntity<>(machine.get().getCompany(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/machines")
	public ResponseEntity<Machine> createMachine(@RequestBody Machine machine) {
		try {
			System.out.println(machine.toString());
			Machine _machine = machineService.editMachine(new Machine(machine.getSerialNumber(), machine.getCompany(), machine.getProduct()));
			return new ResponseEntity<>(_machine, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/machines/{id}")
	public ResponseEntity<Machine> updateMachine(@PathVariable("id") long id, @RequestBody Machine machine) {
		Optional<Machine> machineData = machineService.getMachine(id);

		if (machineData.isPresent()) {
			Machine _machine = machineData.get();
			_machine.setSerialNumber(machine.getSerialNumber());
			_machine.setProduct(machine.getProduct());
			return new ResponseEntity<>(machineService.editMachine(_machine), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/machines/{id}")
	public ResponseEntity<HttpStatus> deleteMachine(@PathVariable("id") long id) {
		try {
			machineService.deleteMachine(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
