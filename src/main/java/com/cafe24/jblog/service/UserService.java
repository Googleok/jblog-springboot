package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BlogDao blogDao;
	
	public Boolean existId(String id) {
		UserVo userVo = userDao.get(id);
		return userVo != null ;
	}
	
	public Boolean join(UserVo userVo) {
		if(userDao.insert(userVo)) {
			blogDao.insert(userVo.getId());
			return true;
		}
		return false;
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

	public Boolean update(UserVo userVo) {
		return userDao.update(userVo);
	}
	
}
