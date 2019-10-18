package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	

	public Boolean existUser(String email) {
		return userDao.get(email) != null;
	}

	public Boolean insert(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(vo.getId());
		return categoryDao.insert(vo.getId());
		
	}

	public UserVo getUser(UserVo vo) {
		return userDao.getUser(vo);
	}
	

}
