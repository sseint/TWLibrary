package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.service.MemberRemoveSerivce;
import com.twlibrary.ui.UI;
/**
 * 로그인 - 관리자로그인
 * 로그인화면에서 관리자가 로그인을 하면 출력하는 화면
 * 관리자 기능: 도서관리, 회원관리, 이벤트 관리
 */
public class AdminMenuController {
	/**
	 * 관리자 기능 출력 및 선택하는 메소드
	 * 기능을 선택하면 선택한 기능을 호출
	 * 기능 번호 이외의 번호를 입력 시 반복
	 */
	public static void adminMenu() { //로그인 - 관리자로그인 화면 출력
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			UI.adminLogin();
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				// 도서 관리 메뉴
				AdminBookManageController.adminBookManage();
			} else if (sel.equals("2")) {
				// 회원 관리 메뉴
				MemberRemoveSerivce.doRemove();
			} else if (sel.equals("3")) {
				// 이벤트 관리 메뉴
				AdminEventController.adminEventManage();
			} else if (sel.equals("0")) {
				// 메인화면으로 돌아가기
				break;
			} else {
				System.out.println("0-4 입력");
			}

		}
	}
}
