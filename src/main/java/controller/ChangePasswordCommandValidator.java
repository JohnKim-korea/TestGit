package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePasswordCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordCommandValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, "curPassword", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, "newPassword", "required");
	}

}
