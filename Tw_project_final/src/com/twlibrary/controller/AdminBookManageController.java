package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.service.AdminBannabService;
import com.twlibrary.service.BookAddService;
import com.twlibrary.service.BookEditService;
import com.twlibrary.service.BookRemoveService;
import com.twlibrary.service.BookSearchService;
import com.twlibrary.vo.BookVO;

/**
 * 관리자 로그인-도서 관리 메뉴
 * 관리자가 도서 관련 서비스를 골라 해당 기능으로 이동하는 화면
 * 검색, 등록, 희망도서, 반납함, 연체 도서 조회 기능 중 선택
 */
public class AdminBookManageController {
	/**
	 * 기능 출력 및 선택하는 메소드
	 * 기능을 선택하면 선택한 기능을 호출
	 * 기능 번호 이외의 번호를 입력 시 반복
	 */
	public static void adminBookManage() { //관리자 로그인 - 도서 관리 메뉴 출력
		System.out.println("────────────────────────────────────────────────");
		System.out.println("1. 도서 검색    2. 도서 등록     3. 희망 도서 관리     4. 반납함 관리     5. 연체 도서 조회");
		System.out.print("메뉴 선택: ");
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			String sel = scan.nextLine();

			if (sel.equals("1")) {
				// 도서 검색
				BookVO b = BookSearchService.bookSearch();
				editRemove(b);
				break;
				
			} else if (sel.equals("2")) {
				// 도서 등록
				BookAddService.addBook();
				break;
				
			} else if (sel.equals("3")) {
				// 희망 도서 관리
				//TODO 희망도서 (관리자)
				WishListController.addWishList();
				break;
				
			} else if (sel.equals("4")) {
				// 반납함 관리
				AdminBannabService.restock();
				break;
				
			} else if (sel.equals("5")) {
				// 연체 도서 관리
				OverdueController.getOverdue();
				break;
				
			} else {
				System.out.println("1-5 입력");
			}

		}
	}
	private static void editRemove(BookVO b) {
		boolean loop = true;
		while(loop) {
			System.out.println();
			System.out.print("1. 수정 \t2. 삭제 : ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			if(input.equals("1")) {
				BookEditService.editBook(b);
				loop=false;
			} else if (input.equals("2")) {
				BookRemoveService.removeBook(b);
				loop=false;
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
}
