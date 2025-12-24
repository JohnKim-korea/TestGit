package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import examspring.RegisterCommand;

public class RegisterCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {//Controller 메서드에서 @ModelAttribute 어노테이션으로 설정된 객체가 이 target 객체입니다.
		RegisterCommand cmd = (RegisterCommand)target;
		String emailRegExp = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailRegExp);
		
		if(cmd.getEmail() == null || cmd.getEmail().trim().isEmpty()) { //trim은 공백을 제거하는 메서
			errors.rejectValue("email", "required"); //에러 필드 명/에러 메시지 코드
		} else {
			Matcher matcher = pattern.matcher(cmd.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		/*Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
		  Matcher matcher = pattern.matcher("example@example.com");
		  boolean isMatch = matcher.matches(); // 패턴이 일치하는지 확인
		  Matcher 클래스는 pattern 클래스랑 같이 쓰이며 문자열 패턴이 일치하는 지 확인하는 역활을 한다.*/
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required"); // 첫번째인자는 Error객체 두번째인자는 form에서의 검증대상 세번째 인자는 errors 객체에 저장되는 에러값 
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		if(!cmd.getPassword().isEmpty() && !cmd.getConfirmPassword().isEmpty()) {
			if(!cmd.isPasswordEqualsToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		
		}
		
	}

}




