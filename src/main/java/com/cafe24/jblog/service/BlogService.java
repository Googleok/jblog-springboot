package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	public BlogVo getBlogById(String id) {
		return blogDao.getBlogById(id);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getCategoryList(id);
	}

	public List<PostVo> getPostList(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		return postDao.getPostList(map);
	}

	public Boolean update(BlogVo vo) {
		return blogDao.update(vo);
	}

	public Boolean addCategory(String id, String name, String description) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("description", description);
		return categoryDao.insert(map);
	}

	public Boolean deleteCategory(Long no) {
		postDao.deletePostByCategoryNo(no);
		return categoryDao.delete(no);
	}

	public Boolean write(String title, String category, String contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("category", category);
		map.put("contents", contents);
		return postDao.write(map);
	}

	public Boolean updateCategoryCount(String category, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("id", id);
		return categoryDao.updateCategoryUpCount(map);
	}

	public PostVo getPostOne(String id, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return postDao.getPostOne(map);
	}

	public PostVo getFirstPostOne(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		return postDao.getFirstPostOne(map);
	}

	public Long getExistCategoryNum(String id) {
		Long num = categoryDao.getExistCategoryNum(id);
		if(num == null) {
			num = 1L;
		}
		return num;
	}

}
