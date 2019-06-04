package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	// 여기서 리턴하는 객체가 @AuthUser 어노테이션 단 authUser에 삽입이 된당
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}

		return session.getAttribute("authUser");
	}

	// 파라미터가 들어가는 것은 다 여기에 걸린다.
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// @AuthUser가 안 붙어 있음
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		if (authUser == null) {
			return false;
		}

		// 파라미터 타입이 UserVo가 아님
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}

		return true;
	}

}
