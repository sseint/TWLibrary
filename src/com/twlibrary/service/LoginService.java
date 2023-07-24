package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.controller.AdminMenuController;
import com.twlibrary.controller.MemberMenuController;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.AdminVO;

/**
 * [메인 메뉴] > [로그인]
 * 로그인 시 [회원][관리자] 중 선택하여 로그인 및 [관리자] 선택 시 ID와 비밀번호의 유효성 검사 클래스 입니다.
 * 사용자가 입력한 ID, PW가 AdminVO에 저장되어 있는 adminId, adminPw와 일치하는지 유효성 검사를 실시 후 모두 일치할 시 AdminMenuController.adminMenu()를 호출합니다.
 */
public class LoginService { //class
	static Scanner scan = new Scanner(System.in);
	public static AdminVO admin = new AdminVO();

	/**
	 * 메인메뉴에서 [로그인] 선택 시 [회원]과 [관리자] 중 선택하여 로그인할 수 있는 메소드 입니다. 
	 * 관리자의 ID와 비밀번호는 AdminVO 클래스에 고정되어 있으며, 관리자 로그인 시 사용자가 입력한 ID, PW와 비교하여 유효성 검사를 실시합니다.
	 */
	public static void login() {

		//1. 로그인(회원, 관리자) 서비스 중 선택
		while(true) {
		
			UI.login();
			System.out.print("원하시는 메뉴의 번호를 입력해주세요:​ ");
			String loginChoice = scan.nextLine();
			System.out.println();
			
			if (loginChoice.equals("1")) {
				MemberLoginService.loginService();
				MemberMenuController.memberMenu();
				break;
			} //2. 관리자 로그인 선택 시
			else if (loginChoice.equals("2")) {
				System.out.println("관리자 아이디와 비밀번호를 입력하시오. ​");
				
				adminId();
				adminPw();
				
				UI.pause(); //로그인 완료 후 1초간 일시정지
				System.out.println();
				System.out.println();
				
				//3. 관리자 메인 화면 출력
				
				AdminMenuController.adminMenu();
				break;
			
			} //loginChoice
			else if (loginChoice.equals("0")) {
				System.out.println("메인화면으로 돌아갑니다.");
				UI.pause();
				break;
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}

	}//login 클래스

	/**
	 * 사용자가 입력한 문자와 관리자 ID가 일치하는지 유효성 검사를 하는 메소드 입니다.
	 * ID가 일치하면 비밀번호 검사로 넘어가도록 빈 문자열을 반환하며, 일치하지 않을 시 while문을 통해 올바른 ID를 입력할 때까지 반복합니다.
	 * @return "";
	 */
	public static String adminId() {

		boolean flag = true;

		while (flag) {

			System.out.print("아이디: ");
			String adminId = scan.nextLine();

			if (adminId.equals(admin.getAdminId())) { // 관리자 아이디와 일치하면
				flag = false; // 루프 종료
				break; // 돌아가기
			} else { // 일치하지 않으면
				System.out.println("잘못된 아이디 입니다. 다시 입력하십시오.");
			} // while
		}

		return "";

	}// checkId

	/**
	 * 사용자가 입력한 문자와 관리자 비밀번호가 일치하는지 유효성 검사를 하는 메소드 입니다.
	 * 비밀번호가 일치하면 빈 문자열을 반환 후 AdminMenuController.adminMenu(); 로 넘어가며, 일치하지 않을 시 while문을 통해 올바른 비밀번호를 입력할 때까지 반복합니다.
	 * @return "";
	 */
	public static String adminPw() {

		boolean flag = true;

		while (flag) {

			System.out.print("비밀번호: ");
			String adminPw = scan.nextLine();

			if (adminPw.equals(admin.getAdminPw())) { // 관리자 비밀번호와 일치하면
				System.out.println("로그인이 완료되었습니다.");

				flag = false; // 루프 종료
				break; // 돌아가기
			} else { // 일치하지 않으면
				System.out.println("잘못된 비밀번호 입니다. 다시 입력하십시오.");
			} // while
		}

		return "";

	}//checkPw

}//class
