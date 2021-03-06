package it.uniroma3.siw.spring.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;



@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;

	@Autowired
	private CollezioneValidator collezioneValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/admin/addCollezione", method = RequestMethod.GET)
	public String addCollezione(Model model) {

		logger.debug("addCollezione");
		model.addAttribute("collezione", new Collezione());
		return "collezioneForm.html";

	}

	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {

		model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
		return "collezione.html";

	}

	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {

		model.addAttribute(this.collezioneService.tutti());
		return "collezioni.html";

	}

	@RequestMapping(value = "/collezione", method = RequestMethod.POST)
	public String newCollezione(@ModelAttribute("collezione") Collezione collezione, Model model, BindingResult bindingResult) {

		this.collezioneValidator.validate(collezione, bindingResult);
		if(!bindingResult.hasErrors()){
			this.collezioneService.inserisci(collezione);
			model.addAttribute("collezioni", this.collezioneService.tutti());
			return "collezioni.html";
		}

		return "collezioneForm.html";

	}



}