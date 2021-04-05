package uoc.edu.dsmantenimiento.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uoc.edu.dsmantenimiento.model.User;
import uoc.edu.dsmantenimiento.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository<User> userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = null;
		try {
			user = (User) userRepository.findByEmail(email).get(0);

		} catch(Exception e) {
			new UsernameNotFoundException("User Not Found with email: " + email);
		}
		return UserDetailsImpl.build(user);
	}

}