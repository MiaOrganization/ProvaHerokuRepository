package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.User;

public interface UtenteRepository extends CrudRepository<User,Long>  {

	public List<User> findByNomeAndCognome(String nome,String cognome);
	
	public List<User> findByCellulare(String cellulare);
}
