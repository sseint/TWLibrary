package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.dao.MemberDAO;
import com.twlibrary.service.MemberEditService;
import com.twlibrary.service.MemberLoginService;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.MemberVO;
/**
 * 회원 로그인 - 회원정보수정
 * 로그인한 회원에 대한 정보를 수정항목을 선택하는 화면
 * 수정할 수 있는 항목: 아이디, 전화번호, 비밀번호
 */
public class MemberEditController {
	/**
	 * 회원 정보 수정
	 * 수정 항목을 선택, 이외의 번호를 입력 시 반복
	 * 
	 */
	public static void memberEdit() { //회원 정보 수정 항목 출력
		Scanner scan = new Scanner(System.in);
		//현재 MemberVO리스트에 있는 항목중 로그인 되어있는 인덱스 번호를 반환받아 수정한 후 list.set을 하기위함.
		UI.memberEditUI();
		System.out.print("수정하실 항목의 번호를 선택해주세요(숫자): ");
		int num = scan.nextInt();
		scan.skip("\r\n");
		while (true) {
			if (num == 1) {
				MemberEditService.idEdit(); //아이디 수정
				System.out.println("변경이 완료 되었습니다.");
				break;
			} else if (num == 2) {
				MemberEditService.phoneNumEdit(); //전화번호 수정
				System.out.println("변경이 완료 되었습니다.");
				break;
			} else if (num == 3) {
				MemberEditService.pwEdit(); //비밀번호 수정
				System.out.println("변경이 완료 되었습니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.print("수정하실 항목의 번호를 선택해주세요(숫자): ");
				num = scan.nextInt();
				scan.skip("\r\n");
			}
		}
		MemberDAO.getList().set(MemberLoginService.getIndex(), MemberVO.myLogin);
		System.out.println();
		UI.memberEditUI();
		UI.returnMemberUI();
	}
}
