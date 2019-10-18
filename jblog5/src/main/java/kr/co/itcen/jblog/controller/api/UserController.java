package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.dto.JSONResult;

@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email",required=true, defaultValue = "")String email) {
		Boolean exist = userService.existUser(email);
		return JSONResult.success(exist);
	}
}
