package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.service.BookSearchService;
import com.twlibrary.service.UnLoginBannabService;
import com.twlibrary.service.UnLoginRentService;
import com.twlibrary.vo.BookVO;

/**
 * 메인메뉴에서 2.비회원 이용 메뉴를 선택했을 시 제공되는 컨트롤러 클래스입니다.
 * 호출되는 메소드는 unRegisterd() 메소드입니다.
 * 이 메소드는 비회원 이용화면 UI를 출력해 주고 세가지 선택지를 제공합니다.
 * 각 선택지 선택시 필요한 서비스를 제공하는 메소드들을 호출해줍니다.
 */

public class UnLoginController {
	/**
	 * 호출되어 비회원 메뉴 컨트롤러 기능을 제공하는 메소드
	 */
	public static void unRegistered() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println();
		System.out.println("  ┌─────[ 비회원 이용 화면 ]─────┐");
		System.out.println("  │                                        │");
		System.out.println("  │   1. 도서 검색/대출                    │");
		System.out.println("  │   2. 도서 반납                         │");
		System.out.println("  │   0. 메인화면으로 돌아가기             │");
		System.out.println("  │                                        │");
		System.out.println("  └────────────────────┘");
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.print("원하시는 메뉴의 번호를 입력해주세요:​ ");
		
		while (true) {
			String sel = scan.nextLine();
			
			if (sel.equals("1") ) {
				//도서검색 후 대출
				BookVO b = BookSearchService.bookSearch();
				UnLoginRentService.rentBook(b);
				break;
			} else if (sel.equals("2")) {
				UnLoginBannabService.bannabService();
				break;
			} else if (sel.equals("0")) {
				System.out.println("메인화면으로 돌아갑니다.");
				break;
			} else {
				System.out.print("0~2를 입력해주세요: ");
			}
		
		}
		
	}

}












