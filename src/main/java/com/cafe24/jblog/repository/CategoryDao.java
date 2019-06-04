package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public boolean insert(Map<String, Object> map) {
		int count = sqlSession.insert("category.insert", map);
		return count == 1;
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("category.delete", no);
		return count == 1;
	}

	public Boolean updateCategoryUpCount(Map<String, Object> map) {
		int count = sqlSession.update("category.updateCategoryCount", map);
		return count == 1;
	}

	public Long getExistCategoryNum(String id) {
		return sqlSession.selectOne("category.getExistCategoryNum", id);
	}

}
