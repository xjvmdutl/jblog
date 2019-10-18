package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlsession;

	public Object get(String email) {
		UserVo result = sqlsession.selectOne("user.getByEmail",email);
		return result;
	}

	public Boolean insert(UserVo vo) {
		int count = sqlsession.insert("user.insert",vo);
		return count == 1;
		
	}

	public UserVo getUser(UserVo vo) {
		UserVo uservo = sqlsession.selectOne("user.getByidpassword",vo); 
		return uservo;
	}
	
}
