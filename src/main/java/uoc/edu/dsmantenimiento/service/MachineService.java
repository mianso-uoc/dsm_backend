package uoc.edu.dsmantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Machine;
import uoc.edu.dsmantenimiento.repository.MachineRepository;

@Service
public class MachineService {

	@Autowired
	private MachineRepository machineRepository;
	
	public List<Machine> getMachinesByCompany(Company company) {
		return machineRepository.findByCompany(company);
	}
	
	public Optional<Machine> getMachine(Long id) {
		return machineRepository.findById(id);
	}
	
	public Machine editMachine(Machine Machine) {
		return machineRepository.save(Machine);
	}
	
	public void deleteMachine(Long id) {
		machineRepository.deleteById(id);
	}
}
