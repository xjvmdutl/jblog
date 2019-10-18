package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.jblog.vo.UserVo;
import kr.co.itcen.jblog.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		UserVo vo =new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		
		
		UserVo authUser=userService.getUser(vo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//session처리를 해주면 된다.
		HttpSession session=request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
