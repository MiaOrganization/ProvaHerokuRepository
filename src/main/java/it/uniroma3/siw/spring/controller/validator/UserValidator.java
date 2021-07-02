package it.uniroma3.siw.spring.controller.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.UserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserService userService;

	//	private static final Logger logger = LoggerFactory.getLogger(UtenteValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");

		if (!errors.hasErrors()) {
//			logger.debug("confermato: valori non nulli");
			if (this.userService.alreadyExists((User)o)) {
//				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
}
