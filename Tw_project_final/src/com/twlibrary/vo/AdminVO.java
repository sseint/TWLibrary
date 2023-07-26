package com.twlibrary.vo;

/**
 * 관리자VO 클래스 입니다.
 * 관리자는 고정된 하나의 ID, PW를 가지며, 관리자 로그인 시 입력한 String과 일치하는지 유효성 검사를 할 때 사용됩니다.
 */
public class AdminVO {
	
	private final String adminId = "admin123";
	private final String adminPw = "admin123";
	
	public String getAdminId() {
		return adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	
	
}
