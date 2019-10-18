package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	public Long insert(CategoryVo categoryvo) {
		
		return categoryDao.insert(categoryvo);
	}
	public CategoryVo get(Long no) {
		
		return categoryDao.get(no);
	}
	public Boolean delete(Long no) {
		postDao.delete(no);
		return categoryDao.delete(no);
	}
	
}
