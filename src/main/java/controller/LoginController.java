package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import examspring.AuthInfo;
import examspring.AuthService;
import examspring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String form(@ModelAttribute("command") LoginCommand cmd) {
		return "login/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(@ModelAttribute("command") LoginCommand cmd, Errors errors,HttpSession session) {
		new LoginCommandValidator().validate(cmd, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(cmd.getEmail(), cmd.getPassword());
			System.out.println("로그인 성공");
			//세션에 정보를 저장
			session.setAttribute("authInfo", authInfo);
			
			return "login/loginSuccess";
		}catch(IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordNotMatching"); //글로벌 에러코드
			return "login/loginForm";
		}
	}
	
	
	
	
	
	
	
	
}
