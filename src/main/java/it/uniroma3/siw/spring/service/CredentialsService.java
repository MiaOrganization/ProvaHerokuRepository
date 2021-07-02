package it.uniroma3.siw.spring.service;

import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credenzialiRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credenzialiRepository.findById(id);
		return result.orElse(null);
	}
	
//	@Transactional
//	public User getUtente() {
//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Credentials credentials = this.getCredentials(userDetails.getUsername());
//		User user = credentials.getUser();
//		return user;
//	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credenzialiRepository.findByUsername(username);
		return result.orElse(null);
	}
		
	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		
		if(credentials.getUsername().equals("museum") && credentials.getPassword().equals("museum")) {
			credentials.setRole(Credentials.ADMIN_ROLE);
		}
		else {
			credentials.setRole(Credentials.DEFAULT_ROLE);
		}
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credenzialiRepository.save(credentials);
	}
}
