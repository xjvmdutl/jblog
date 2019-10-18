package kr.co.itcen.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

@RequestMapping("/user")
@Controller()
public class UserController {
	@Autowired
	private UserService userservice;
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		return "/user/join";
	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "/user/join";
		}
		userservice.insert(vo);
		return "user/joinsuccess";
	}
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
}
