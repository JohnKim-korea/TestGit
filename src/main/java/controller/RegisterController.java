package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import examspring.AlreadyExistingMemberException;
import examspring.MemberRegisterService;
import examspring.RegisterCommand;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public RegisterController(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService; //자바 메서드로 생성자를 정의하면 XML에서 프로퍼티 값을 설정해도 해당 값은 무시됩니다
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@RequestMapping(value="/register/step2", method=RequestMethod.POST)
	public String handleStep2(
			@RequestParam(value="agree", defaultValue="false")Boolean agree,
			Model model) {
		if(!agree) {
			return "register/step1";
		}
		model.addAttribute("command", new RegisterCommand());
		return "register/step2";
	}
	
	@RequestMapping(value="/register/step2", method=RequestMethod.GET)
	public String handleStep2() {
		return "redirect:/register/step1";
	}
	
	@RequestMapping(value="/register/step3", method=RequestMethod.POST)
	public String hendleStep3(
			@ModelAttribute("command")RegisterCommand cmd,
			Errors errors) { //Errors매개변수는 반드시 커맨드객체 바로 뒤에 선언(필수!)
		
		new RegisterCommandValidator().validate(cmd, errors);
		if(errors.hasErrors()) {
			return "register/step2";
		}
		try {
			memberRegisterService.regist(cmd);
			return "register/step3";
		}catch(AlreadyExistingMemberException e){
			//e.printStackTrace();
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
	

}






