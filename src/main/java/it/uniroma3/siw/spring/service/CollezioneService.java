package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CollezioneRepository;



@Service
public class CollezioneService {
	
	@Autowired //ciò permette di iniettare direttamente le dipendenze per tutte le variabili con questa segnatura
	//ci pensa il framework a iniettarle
	private CollezioneRepository collezioneRepository; 
	
	@Transactional
	public Collezione inserisci(Collezione persona) {
		return collezioneRepository.save(persona);
	}
	
	@Transactional
	public List<Collezione> collezioniPerNome(String nome) {
		return collezioneRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Collezione> collezioniPerCuratore(Curatore curatore) {
		return collezioneRepository.findByCuratore(curatore);
	}

	@Transactional
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByNome(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}

}
