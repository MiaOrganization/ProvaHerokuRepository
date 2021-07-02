package it.uniroma3.siw.spring.service;

import java.util.List;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.UtenteRepository;

@Service
public class UserService {

	@Autowired 
	private UtenteRepository utenteRepository;

	@Transactional
	public User inserisci(User utente) {
		return utenteRepository.save(utente);
	}

	@Transactional
	public List<User> utentePerNomeAndCognome(String nome, String cognome) {
		return utenteRepository.findByNomeAndCognome(nome, cognome);
	}
	
	@Transactional
	public List<User> utentePerCellulare(String cellulare) {
		return utenteRepository.findByCellulare(cellulare);
	}
	
	@Transactional
	public User utentePerId(Long id){
		Optional<User> optional = utenteRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public List<User> tutti(){
		return (List<User>) utenteRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(User u) {
		List<User> listaUtenti=this.utenteRepository.findByNomeAndCognome(u.getNome(),u.getCognome());
		if(listaUtenti.size()>0)
			return true;
		else
			return false;
	}

}
