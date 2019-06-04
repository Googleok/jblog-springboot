package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	// 내부적으로 클래스를 만든것
	public enum Role{USER, ADMIN}
	public Role role() default Role.USER;
//	String value() default "USER";
//	int test() default 1;
}
