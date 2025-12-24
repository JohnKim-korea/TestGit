package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import examspring.AuthInfo;
import examspring.ChangePasswordService;
import examspring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePasswordController {
	
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(
			@ModelAttribute("command") ChangePasswordCommand cpc) {
		return "edit/changePwdForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String submit(
			@ModelAttribute("command")ChangePasswordCommand cpc,
			Errors errors,
			HttpSession session) {
		
		new ChangePasswordCommandValidator().validate(cpc, errors);
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(authInfo == null) {
			return "redirect:/main";
		}
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(),
					cpc.getCurPassword(), 
					cpc.getNewPassword());
			return "edit/changePwd";
		}catch(IdPasswordNotMatchingException e) {
			errors.rejectValue("curPassword", "notMatching");
			e.printStackTrace();
			return "edit/changePwdForm";
		}
		
	}
	
}
