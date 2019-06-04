package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	public UserVo get(UserVo userVo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userVo.getId());
		map.put("password", userVo.getPassword());
		UserVo userVo2 = sqlSession.selectOne("user.getByIdAndPassword", map);
		return userVo2;
	}	
	
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		
		return 1 == count;
	}	
	
	
	public Boolean update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		return 1==count;
	}

	public Object getUpdtaeInfo(Long no) {
		return null;
	}

	public UserVo get(String id) {
		UserVo userVo = sqlSession.selectOne("user.getById", id);
		return userVo;
	}

}
