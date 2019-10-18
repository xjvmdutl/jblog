package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;
import kr.co.itcen.jblog.dto.JSONResult;

@Controller("categoryController")
@RequestMapping("/api/admin")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/insertcategory")
	public JSONResult insertCategory(@RequestBody CategoryVo categoryvo,
			@AuthUser UserVo vo) {
		categoryvo.setBlog_id(vo.getId());
		Long no = categoryService.insert(categoryvo);
		CategoryVo cartvo = categoryService.get(no);
		cartvo.setCount(0L);
		
		return JSONResult.success(cartvo);
	}
	@ResponseBody
	@RequestMapping(value = "/deletecategory", method = RequestMethod.DELETE)
	public JSONResult deleteCategory(Long no) {
		Boolean exist = categoryService.delete(no);
		
		
		return JSONResult.success(exist);
	}
}