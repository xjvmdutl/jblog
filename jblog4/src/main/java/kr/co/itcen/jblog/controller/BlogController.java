package kr.co.itcen.jblog.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@RequestMapping("/{id:(?!assets)(?!images).*}")//assets밑에 있는 파일제외 모두 받는다
@Controller

public class BlogController {
	@Autowired
	private BlogService blogService;
	@RequestMapping({"","/{path1}","/{path1}/{path2}"})
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> path1,
		@PathVariable Optional<Long> path2,
		@AuthUser UserVo vo,
		Model model){
		List<CategoryVo> list = blogService.getList(id);
		model.addAttribute("list",list);
		Long categoryno= list.get(0).getNo();
		Long postno = 0L;
		if(path2.isPresent()) {
			categoryno=path1.get();
			postno=path2.get();
		}else if(path1.isPresent()) {
			categoryno=path1.get();
		}	
		List<PostVo> postList= blogService.getList(categoryno);
		model.addAttribute("postList",postList);
		PostVo postvo;
		if(postno==0L && (!postList.isEmpty())) {
			postno=postList.get(0).getNo();
			postvo=blogService.getPost(postno,categoryno);
		}else {
			postvo= blogService.getPost(postno,categoryno);
		}
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo",blogvo);
		model.addAttribute("post",postvo);
		model.addAttribute("id",id);
		if(id.equals(vo.getId()))
			model.addAttribute("isMe",true);
		return "blog/blog-main";
	}
	@RequestMapping("/admin/basic")
	public String blogmanage(@PathVariable String id,
			Model model,@AuthUser UserVo uservo) {
		if(!(id.equals(uservo.getId())))
			return "redirect:/"+id;
		BlogVo vo=blogService.get(id);
		model.addAttribute("vo",vo);
		return "blog/blog-admin-basic";
	}
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String blogmanage(@RequestParam(value = "logo-file",required = false) MultipartFile multipartFile,
			@PathVariable String id,
			BlogVo vo) {
		blogService.update(multipartFile,vo);
		return "redirect:/"+id;
	}
	@RequestMapping("/admin/category")
	public String category(@PathVariable String id,
			Model model,
			@AuthUser UserVo uservo) {
		if(!(id.equals(uservo.getId())))
			return "redirect:/"+id;
		List<CategoryVo> list= blogService.getCategoryList(id);
		model.addAttribute("list",list);
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo",blogvo);
		return "blog/blog-admin-category";
	}
	@RequestMapping("/admin/write")
	public String write(@PathVariable String id,
			Model model,
			@AuthUser UserVo uservo) {
		if(!(id.equals(uservo.getId())))
			return "redirect:/"+id;
		List<CategoryVo> list= blogService.getCategorytitle(id);
		
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("list",list);
		model.addAttribute("vo",blogvo);
		return "blog/blog-admin-write";
	}
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable String id,PostVo vo,@RequestParam("category") Long no) {
		vo.setCategory_no(no);
		blogService.insertPost(vo);
		return "redirect:/"+id;
	}
}