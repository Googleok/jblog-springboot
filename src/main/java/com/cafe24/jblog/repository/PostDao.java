package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> getPostList(Map<String, Object> map) {
		return sqlSession.selectList("post.getPostList", map);
	}

	public Boolean write(Map<String, Object> map) {
		int count = sqlSession.insert("post.write", map);
		return count == 1;
	}

	public PostVo getPostOne(Map<String, Object> map) {
		return sqlSession.selectOne("post.getPostOne", map);
	}

	public PostVo getFirstPostOne(Map<String, Object> map) {
		return sqlSession.selectOne("post.getFirstPostOne", map);
	}

	public void deletePostByCategoryNo(Long no) {
		sqlSession.delete("post.deletePostByCategoryNo", no);	// delete 된 행의 개수
	}


	
}
