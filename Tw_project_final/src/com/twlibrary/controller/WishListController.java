package com.twlibrary.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.twlibrary.service.WishListService;
import com.twlibrary.ui.UI;

/**
 * <p>희망도서 목록을 제어하는 클래스</p>
 */
public class WishListController {
	
	/**
	 * <p>회원 - 희망도서 검색, 추가메서드</p>
	 * 
	 * <p>설명</p>
	 * 기본값: while(true)를 이용하여 사용자가 페이지를 요청하거나, 책을 추가할 수 있게 계속하여 입력을 받는다.
	 * 내부적으로 유효성을 검사하는 메서드는 pageCount(), isPage()가 있다.
	 * 
	 *<p>pageCount(): 처음 ~ 끝페이지까지의 숫자가 들어간 리스트를 반환 > String으로 입력받기 때문에, 
	 *  Y와 N을 제외한 다른 문자열이 들어가면 String오류가 난다. 유효성을 검사하고자 작성된 메서드. </p>
	 *<p>isPage(): boolean을 리턴해주며, pageCount()에서 반환된 페이지(list)값들을 검사하여 숫자가 맞으면 유효한 페이지(true반환), 숫자가 일치하지 않으면 유효하지 않은 페이지(false반환)</p>
	 */
	public static void getWishList() {
		Scanner scan = new Scanner(System.in);
		WishListService.getWishList(1);
		boolean loop = true;
		while (loop) {
			System.out.print("페이지를 이동하려면 페이지 번호를, 책을 추가하려면 Y를 입력해주세요: ");
			String str = scan.nextLine();
			System.out.println();

			if (str.equalsIgnoreCase("Y")) {
				loop = false;
				WishListService.addWishList();
			} else if (str.equalsIgnoreCase("N")) {
				loop = false;
				UI.returnMemberUI();
			} else {
				if (!isPage(str)) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("숫자 혹은 페이지 수를 정확히 입력하시거나 (Y / N)을 입력해주세요");
					continue;
				} else {
					if (Integer.parseInt(str) > 0 && Integer.parseInt(str) <= WishListService.getLastPage()) {
						WishListService.getWishList(Integer.parseInt(str));
					}
				}
			}
		}
	}

	//위의 scan이 String으로 받기때문에, Y / N 이외의 다른 문자가 들어가면 Integer.parseInt가 오류를 내기에, 오류를 막고자 만든 메서드
	private static ArrayList<String> pageCount() {
		ArrayList<String> sList = new ArrayList<String>();
		for (int i = 1; i <= WishListService.getLastPage(); i++) {
			sList.add(Integer.toString(i));
		}
		return sList;
	}

	//비교를 되게 많이하게되지만,, 유효성을위해 어쩔수없이,, > 정규식은 좀 의미가 다를듯 해보임.
	private static boolean isPage(String str) {
		for (int i = 0; i < pageCount().size(); i++) {
			if (pageCount().get(i).equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <p>관리자 - 희망도서 관리 메서드</p>
	 * <p>설명</p>
	 * getWishList()메서드와 동일하게 페이지를 요청받는 기능이지만, Y나 N을 입력할시, 내부적으로 동작하는 기능이 다르다.
	 */
	public static void addWishList() {
		Scanner scan = new Scanner(System.in);
		WishListService.getWishList(1);
		boolean loop = true;
		while (loop) {
			System.out.print("페이지를 이동 시 페이지 번호, 희망도서 삭제 시 Y, 관리자화면으로 되돌아갈 시 N을 입력해주세요: ");
			String str = scan.nextLine();
			System.out.println();

			if (str.equalsIgnoreCase("Y")) {
				WishListService.adminAddWishList();
				loop = false;
			} else if (str.equalsIgnoreCase("N")) {
				//관리자 메인화면으로 이동 
				UI.pause();
				loop = false;
			} else {
				if (isPage(str)) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("숫자 혹은 페이지 수를 정확히 입력하시거나 (Y/ N)을 입력해주세요");
					continue;
				} else {
					if (Integer.parseInt(str) > 0 && Integer.parseInt(str) <= WishListService.getLastPage()) {
						WishListService.getWishList(Integer.parseInt(str));
					}
				}
			}
		}
	}
}
