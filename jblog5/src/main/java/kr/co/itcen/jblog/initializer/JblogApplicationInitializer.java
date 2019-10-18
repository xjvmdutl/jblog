package kr.co.itcen.jblog.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kr.co.itcen.jblog.config.AppConfig;
import kr.co.itcen.jblog.config.WebConfig;

public class JblogApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

//	@Override
//	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//		DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
//		//Exception Handler가 발견되지 않으면 Error!!
//		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//		return dispatcherServlet;
//	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8",true)};
	}
	
}
