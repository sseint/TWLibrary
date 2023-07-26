package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.WishListDAO;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.BookVO;

/**
 * <p>희망도서 목록 조회 클래스</p>
 * 
 * <p>설명</p>
 * 회원 - 희망하는 도서를 등록 
 * 관리자 - 회원들이 신청한 희망도서목록 조회하여 도서관 도서목록에 추가
 */
public class WishListService {
	private static int lastIndex = WishListDAO.getList().size();
	private static double lastPage = Math.ceil((WishListDAO.getList().size() / 10.0));

	public static int getLastIndex() {
		return lastIndex;
	}
	
	public static double getLastPage() {
		return lastPage;
	}
	
	/**
	 * <p>희망도서목록 정보를 10개씩 출력해주는 메서드</p>
	 * 
	 * <p>설명</p>
	 * 인덱스는 0부터 시작하지만, 페이지는 1페이지부터 있기에, 1페이지를 받으면 0번 인덱스부터 출력하기위해 매개변수로 받은값에 -1을 주었다.
	 * 마지막 페이지를 호출할경우, 마지막 페이지에서 출력될 인덱스의 갯수가 10개가 되지 않을때 인덱스범위 초과 오류를 방지하고자 if(n + 1) == lastPage) 의 조건을 넣었다. 
	 * 
	 * <p>lastPage: 마지막페이지를 의미한다. </p>
	 * <p>lastIndex: 마지막인덱스 + 1을 의미한다.</p>
	 * <p>paging() 페이지 번호를 출력하는 메서드 / (매개변수로 요청한 페이지와, 마지막 페이지를 입력받는다.)</p>
	 * @param n : 페이지 수를 받는 매개변수
	 */
	public static void getWishList(int n) {
		UI.wishListUI();
		n -= 1; // for문 조건 + 매개변수 전달조건 때문에 발생함 : n * 10 + 1
		if ((n + 1) == (int) lastPage) {
			for (int i = n * 10; i < lastIndex; i++) {
				System.out.printf((i + 1) + ". \t%-15s\t%s\t%s\n", WishListDAO.getList().get(i).getTitle(), WishListDAO.getList().get(i).getAuth(),
						WishListDAO.getList().get(i).getPub());
			}

		} else {
			for (int i = n * 10; i < n * 10 + 10; i++) {
				System.out.printf((i + 1) + ". \t%-15s\t%s\t%s\n", WishListDAO.getList().get(i).getTitle(), WishListDAO.getList().get(i).getAuth(),
						WishListDAO.getList().get(i).getPub());
			}
		}
		PagingService.paging(n, lastPage);

	}
	
	/**
	 *<p> 회원 - 희망도서 추가 메서드</p>
	 */
	public static void addWishList() {
		Scanner scan = new Scanner(System.in);
		boolean rootLoop = true;
		boolean loop = true;
		String title = "";
		while (rootLoop) {
			System.out.print("제목: ");
			title = scan.nextLine();

			System.out.print("저자: ");
			String auth = scan.nextLine();
			
			System.out.print("출판사: ");
			String pub = scan.nextLine();
			
			//중복검사를 한다면 여기서 값3개 한꺼번에 겹치는 경우로 설정 근데 그냥 하지말자
			
			loop = true;
			while (loop) {
				System.out.print("신청하신 책의 정보가 올바릅니까? (Y/N) : ");
				String isTrue = scan.nextLine();
				
				isTrue = isTrue.toUpperCase();
				loop = true;
				if (isTrue.equals("Y")) {
					setWishList(title, auth, pub);
					loop = false;
					rootLoop = false;
				} else if (isTrue.equals("N")){
					System.out.println("올바른 값을 입력받습니다.");
					loop = false;
				} else {
					System.out.println("Y또는 N을 입력해주세요.");
				}
			}
		}
	}

	/**
	 * <p>WishList에 희망도서를 set하여 add하는 메서드</p>
	 * 
	 * <p>설명</p>
	 * addWishList()에서 호출당하는 메서드로 유효성검사가 끝난 후, 인자로 책제목, 저자, 출판사를 넘겨주어 희망도서 목록(list)set한다. 
	 * @param title 책제목
	 * @param auth 저자
	 * @param pub 출판사
	 */
	private static void setWishList(String title, String auth, String pub) {
		BookVO b = new BookVO();
		b.setTitle(title);
		b.setAuth(auth);
		b.setPub(pub);
		WishListDAO.getList().add(b);
		System.out.println("희망 도서 신청이 완료되었습니다.");
		System.out.println(WishListDAO.getList().get(WishListDAO.getList().size() - 1).getTitle());
		lastIndex++;
		UI.returnMemberUI();
	}
	private static void removeWishList(int n) {
		WishListDAO.getList().remove(n - 1);
		lastIndex--;
	}

	
	/**
	 * <p>관리자 - 희망도서 목록에서 실제로 검색(인터넷) 하여 도서를 등록하면, 희망 도서목록에서 삭제하는 메서드</p>
	 */
	public static void adminAddWishList() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("추가한 책의 번호를 입력해주세요.");
			int num = scan.nextInt();
			scan.skip("\r\n");
			if(num > 0 && num <= lastIndex) {
				removeWishList(num);
				System.out.println("선택하신 희망도서가 목록에서 삭제되었습니다.");
				UI.pause();
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

}
