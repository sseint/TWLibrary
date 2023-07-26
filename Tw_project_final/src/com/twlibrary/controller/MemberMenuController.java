package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.service.BookSearchService;
import com.twlibrary.service.EventService;
import com.twlibrary.service.LoginBannabService;
import com.twlibrary.service.LoginRentService;
import com.twlibrary.service.QuizMemQuizService;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.BookVO;
/**
 * 로그인 - 회원 로그인
 * 회원이 로그인을 성공하고 기능을 선택하는 화면
 * 출력 : 로그인한 회원의 이름, 대출현황, 누적 대출 권수
 * 기능 : 책 검색 및 대출, 책 반납, 회원 정보 수정, 희망 도서 작성, 퀴즈 참여 
 */
public class MemberMenuController {
	/**
	 * 로그인 - 회원 로그인 화면 출력
	 * 기능 출력 및 선택하는 메소드
	 * 기능을 선택하면 선택한 기능을 호출
	 * 기능 번호 이외의 번호를 입력 시 반복
	 */
	public static void memberMenu() { // 로그인 - 회원 로그인 화면 출력 
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			UI.memberMenuUI();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				// 책 검색
				BookVO b =BookSearchService.bookSearch();
				LoginRentService.rentBook(b);
				
			} else if (sel.equals("2")) {
				// 책 반납
				LoginBannabService.bannabService();
				
			} else if (sel.equals("3")) {
				// 회원정보 수정
				MemberEditController.memberEdit();
				
			} else if (sel.equals("4")) {
				// 희망도서 작성
				WishListController.getWishList();
			} else if (sel.equals("5")) {
				// 퀴즈 맞히기
				QuizMemQuizService.memQuiz();
				
			} else if (sel.equals("0")){
				System.out.println("로그아웃합니다.");
				UI.pause();
				loop=false;
			} else {
				System.out.println("1-4 입력");
			}

		}
	}
}
