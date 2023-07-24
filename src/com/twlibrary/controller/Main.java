package com.twlibrary.controller;

import java.io.IOException;
import java.util.Scanner;

import com.twlibrary.dao.BannabDAO;
import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.MemberDAO;
import com.twlibrary.dao.MonthDAO;
import com.twlibrary.dao.QuizDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.dao.WishListDAO;
import com.twlibrary.save.BannabSave;
import com.twlibrary.save.BookSave;
import com.twlibrary.save.MemberSave;
import com.twlibrary.save.QuizSave;
import com.twlibrary.save.RentLogSave;
import com.twlibrary.save.WishListSave;
import com.twlibrary.service.EventService;
import com.twlibrary.service.LoginService;
import com.twlibrary.service.ReadKingService;
import com.twlibrary.service.RegisterService;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.MemberVO;
/**
 * 태완 도서관의 전산시스템 프로그램의 메인화면
 * 회원, 관리자, 비회원 전용 기능, 회원가입, 이벤트 열람을 구현 
 * 모든 데이터 파일을 read, 프로그램을 종료 시 수정한 내용들을 save 
 */
public class Main {
	/**
	 * 프로그램의 메인
	 * 모든 데이터 파일을 read, 프로그램을 종료 시 수정한 내용들을 save
	 * 없는 메뉴의 번호를 입력 시 반복
	 */
	public static void main(String[] args) throws IOException {
		MonthDAO.readMonth();
		MemberDAO.readMember();
		WishListDAO.readWishList();
		BookDAO.readBook();
		RentLogDAO.readRentLog();
		BannabDAO.readBannab();
		QuizDAO.readWinner();
		QuizDAO.readQuiz();
		QuizDAO.readCorrect();

		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		while (loop) {
			UI.domainUI();
			String str = scan.nextLine();
			
			if (str.equals("1")) {
				//로그인(회원, 관리자)
				LoginService.login();
				MemberVO.myLogin=new MemberVO();
				
			} else if (str.equals("2")) {
				//비회원 이용
				UnLoginController.unRegistered();
				
			} else if (str.equals("3")) {
				// 회원가입
				RegisterService.register();
				
			} else if(str.equals("4")) {
				// 이벤트
				EventService.eventView();
				
			} else if(str.equals("0")) {
				break;
			} else {
				System.out.println("없는 메뉴입니다.");
				UI.pause();
				
			}
		}
		scan.close();
		MemberSave.saveMember();
		WishListSave.saveWishList();
		BookSave.saveBook();
		RentLogSave.saveRendLog();
		BannabSave.saveBannab();
		QuizSave.saveQuiz();
		System.out.println("프로그램 종료");
		

		
	}
}
