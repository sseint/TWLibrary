package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.MemberDAO;
import com.twlibrary.vo.MemberVO;

/**
 * 회원 로그인 하는 클래스
 * 아이디를 입력받으면 반복문을 통해서 member.txt에 있는 아이디와 비교
 * 비교해서 존재하면 다음 루프로 이동 아니면 계속 루프
 * 그 다음 루프에서는 비밀번호를 입력 받고 입력한 아이디와 일치한 Member의 위치에 있는 비밀번호와 비교
 * 일치하면 로그인 성공 아니면 로그인 실패
 */
public class MemberLoginService {
	private static int index;

	public static int getIndex() {
		return index;
	}
	/**
	 * 회원 로그인 하는 클래스
	 * 아이디를 입력받으면 반복문을 통해서 member.txt에 있는 아이디와 비교
	 * 비교해서 존재하면 다음 루프로 이동 아니면 계속 루프
	 * 그 다음 루프에서는 비밀번호를 입력 받고 입력한 아이디와 일치한 Member의 위치에 있는 비밀번호와 비교
	 * 일치하면 로그인 성공 아니면 로그인 실패
	 * @author 김태완
	 * 
	 */

	public static void loginService() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println("회원 로그인 창입니다.");
		while (loop) { //아이디 루프
			System.out.print("아이디 입력 : "); 
			String id = scan.nextLine();
			for (int i = 0; i < MemberDAO.getList().size(); i++) {
				if (id.equals(MemberDAO.getList().get(i).getId())) {
					loop = false;
					index = i;
				}
			}
			if (loop) {
				System.out.println("아이디가 존재하지 않습니다.");
			}

		}
		loop = true;
		while (loop) { //비밀번호 루프
			System.out.print("비밀번호 입력 :");
			String pw = scan.nextLine();
			if (pw.equals(MemberDAO.getList().get(index).getPw())) {
				System.out.println("로그인 완료");
				loop = false;
			}
			if (loop) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}

		}
		MemberVO.myLogin = MemberDAO.getList().get(index);

	}

}
