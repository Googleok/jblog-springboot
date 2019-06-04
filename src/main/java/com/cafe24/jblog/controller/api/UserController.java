package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.UserService;

// 똑같은 이름의 UserController 가 있기 때문에 충돌을 피하기 위해 Controller 에 ID를 준다.
@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkEmail(
			@RequestParam(value="id", required=true, defaultValue="") String id){
		System.out.println(id);
		Boolean exist = userService.existId(id);
		return JSONResult.success(exist);
	}
}
