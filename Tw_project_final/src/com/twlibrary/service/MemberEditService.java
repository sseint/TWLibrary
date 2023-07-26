package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.vo.MemberVO;

/**
 * <p>회원 정보수정 클래스</p>
 */
public class MemberEditService {
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * 이름 유효성검사를하여 수정할 아이디를 do while문으로 계속 받음
	 */
	public static void idEdit() {
		String name = "";
		do {
			System.out.print("수정할 이름을 입력하세요: ");
			name = scan.nextLine();
		} while (!RegisterService.nameIsValid(name));
		MemberVO.myLogin.setName(name);
	}

	/**
	 * 비밀번호 유효성검사를하여 수정할 비밀번호를 do while문으로 계속 받음
	 */
	public static void pwEdit() {
		String pw = "";
		do {
			System.out.print("수정할 비밀번호를 입력하세요: ");
			pw = scan.nextLine();
		} while (!RegisterService.pwIsValid(pw));
		MemberVO.myLogin.setPw(pw);
	}

	/**
	 * 핸드폰 번호 유효성검사를하여 수정할 핸드폰 번호를 do while문으로 계속 받음
	 */
	public static void phoneNumEdit() {
		String phoneNum = "";
		do {
			System.out.print("수정할 전화번호를 입력하세요:");
			phoneNum = scan.nextLine();
		} while (!RegisterService.phoneIsValid(phoneNum));
		MemberVO.myLogin.setPhoneNum(phoneNum);
	}

}
