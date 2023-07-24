package com.twlibrary.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.twlibrary.service.OverdueService;
import com.twlibrary.ui.UI;

/**
 * <p>연체목록을 제어하는 클래스</p>
 *
 */
public class OverdueController {
	
	/**
	 * <p>관리자 - 연체목록 조회 메서드</p>
	 * 
	 * <p>설명</p>
	 * 기본값: while(true)를 이용하여 사용자가 요청한 페이지번호를 계속 요청할 수 있다. 뒤로 돌아가려면 "N" or "n" 입력
	 * 내부적으로 유효성을 검사하는 메서드는 pageCount(), isPage()가 있다.
	 * 
	 *<p>pageCount(): 처음 ~ 끝페이지까지의 숫자가 들어간 리스트를 반환 > String으로 입력받기 때문에, 
	 *  Y와 N을 제외한 다른 문자열이 들어가면 String오류가 난다. 유효성을 검사하고자 작성된 메서드. </p>
	 *<p>isPage(): boolean을 리턴해주며, pageCount()에서 반환된 페이지(list)값들을 검사하여 숫자가 맞으면 유효한 페이지(true반환), 숫자가 일치하지 않으면 유효하지 않은 페이지(false반환)</p>
	 */
	public static void getOverdue() {
		Scanner scan = new Scanner(System.in);
		OverdueService.getOverdue(1);
		while (true) {
			System.out.print("페이지를 이동하려면 페이지번호를, 뒤로 돌아가시려면 N을 입력해주세요: ");
			String n = scan.nextLine();
			System.out.println();
			if (n.equalsIgnoreCase("N")) {
				UI.returnMemberUI();
				break;
			} 
			if(isPage(n)) {
				if (Integer.parseInt(n) > 0 && Integer.parseInt(n) <= OverdueService.getLastPage()) {
					OverdueService.getOverdue(Integer.parseInt(n));
				}
			} else {
				System.out.println("1 ~ " + (int) OverdueService.getLastPage() + "페이지까지 있습니다.");
			}
			
			
			
		}
	}
	private static ArrayList<String> pageCount() {
		ArrayList<String> sList = new ArrayList<String>();
		for (int i = 1; i <= OverdueService.getLastPage(); i++) {
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
}
