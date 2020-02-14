package practice.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import practice.dto.MemberDto;
import practice.service.MemberService;

@Controller
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	
	@GetMapping("/user")
	public String userPage() {
		return "/auth/index.html";
	}
	
	@GetMapping("/user/signup")
	public String signupForm(MemberDto memberDto) {
		return "/auth/signup.html";
	}
	
	@PostMapping("/user/signup")
	public String signup(@Valid MemberDto memberDto, Errors err, Model model) {
		if(err.hasErrors()) {
			model.addAttribute("memberDto", memberDto);
			
			Map<String, String> validatorResult = memberService.validateHandling(err);
			for (String key: validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/auth/signup.html";
		}
		
		memberService.joinUser(memberDto);
		return "redirect:/user/login";
	}
	
	@GetMapping("/user/login")
	public String loginForm() {
		return "/auth/login.html";
	}
	
	@GetMapping("/user/login/result")
	public String login(MemberDto memberDao) {
		return "/auth/loginSuccess.html";
	}
	
	@GetMapping("/user/logout/result")
	public String logout() {
		return "/auth/logout.html";
	}
	
	@GetMapping("/user/denied")
	public String denied() {
		return "/auth/denied.html";
	}
	
	@GetMapping("/user/info")
	public String myInfo() {
		return "/auth/myinfo.html";
				
	}

	@GetMapping("/admin")
	public String admin() {
		return "/auth/admin.html";
	}
	
}
