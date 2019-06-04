package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.FileuploadService;
import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets.*).*}" )	// assets 으로 시작하는 모든것들이 다 들어오지 않는다.
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@Autowired
	private FileuploadService fileUploadService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable String id,
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2,
			ModelMap modelMap
	) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		}else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}else {
			categoryNo = blogService.getExistCategoryNum(id);
		}
		System.out.println(categoryNo);
		BlogVo blogVo = blogService.getBlogById(id);
		if(blogVo == null) {
			return "redirect:/main?result=fail";
		}
		
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		List<PostVo> postList = blogService.getPostList(id, categoryNo);
		PostVo postOne = blogService.getPostOne(id, categoryNo, postNo);
		
		if(postNo == 0L) {
			postOne = blogService.getFirstPostOne(id, categoryNo);
		}
		
		modelMap.addAttribute("blogVo", blogVo);
		modelMap.addAttribute("categoryList", categoryList);
		modelMap.addAttribute("postList", postList);
		modelMap.addAttribute("postOne", postOne);
		return "/blog/blog-main";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String basic(@PathVariable String id, @AuthUser UserVo userVo,Model model) {
		if(!userVo.getId().equals(id)) {
			return "redirect:/";
		}
		model.addAttribute("blogId", id);
		return "/blog/blog-admin-basic";
	}

	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basic(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "logo-file", required = false, defaultValue = "")MultipartFile multipartFile
			) {
		String logo = fileUploadService.restore(multipartFile);
		BlogVo vo = new BlogVo(id, title, logo);
		blogService.update(vo);
		return "redirect:/"+id;
	}

	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String category(@PathVariable String id, @AuthUser UserVo userVo) {
		if(!userVo.getId().equals(id)) {
			return "redirect:/";
		}
		return "/blog/blog-admin-category";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/category/add")
	@ResponseBody
	public String ajax_addCategory(
				@PathVariable String id,
				@RequestParam(value = "name", required = true, defaultValue = "") String name,
				@RequestParam(value = "description", required = true, defaultValue = "") String description,
				@AuthUser UserVo userVo
			) {
		if(!userVo.getId().equals(id)) {
			return "redirect:/";
		}
		boolean result = blogService.addCategory(id, name, description);
		if(result == false) {
			return "fail";
		}
		return "success";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/category/delete/{no}")
	@ResponseBody
	public JSONResult ajax_deleteCategory(
				@PathVariable String id,
				@PathVariable(value="no") Long no,
				@AuthUser UserVo userVo
			) {
		if(!userVo.getId().equals(id)) {
			return JSONResult.fail("Not User");
		}
		boolean result = blogService.deleteCategory(no);
		if(result == false) {
			return JSONResult.fail("Not Working");
		}
		return JSONResult.success("success");
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/category/get", produces="application/json; charset=utf8")
	@ResponseBody
	public JSONResult ajax_categoryList(
				@PathVariable String id
			) {
		return JSONResult.success(blogService.getCategoryList(id));
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String write(@PathVariable String id, Model model, @AuthUser UserVo userVo) {
		if(!userVo.getId().equals(id)) {
			return "redirect:/";
		}
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("list", categoryList);
		return "/blog/blog-admin-write";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(
			@PathVariable String id,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "category", required = true, defaultValue = "") String category,
			@RequestParam(value = "contents", required = true, defaultValue = "") String contents,
			Model model
	) {
		if(blogService.write(title, category, contents)) {
			blogService.updateCategoryCount(category, id);
		}

		return "redirect:/"+id;
	}

}
