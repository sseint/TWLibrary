package com.twlibrary.service;

import java.util.Scanner;
import com.twlibrary.vo.BookVO;

/**<p>[관리자] - [도서관리] 화면에서 도서 수정할 때 사용하는 클래스입니다. 
 * 도서 검색 후 원하는 도서를 선택한 뒤에 수정, 삭제를 할 수 있습니다. 
 * Main에서 editBook()을 호출하여 사용합니다. 
 * </p>
 * 
 */
public class BookEditService {
	
	/** <p>도서 수정 3가지 옵션(제목, 저자, 출판사) 중 1개 택하여 책을 수정하는 메소드입니다.</p>
	 * 1번 선택 : 책 제목 수정<br>
	 * 2번 선택 : 저자 수정 <br>
	 * 3번 선택 : 출판사 수정
	 * 
	 * 
	 * @param b : 수정/삭제 작업 할 책 정보 (도서검색에서 고유번호로 선택한 1권)
	 */
	public static void editBook(BookVO b) {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println("[수정]");
		System.out.println();
		System.out.println("1. 책제목\t2. 저자\t3. 출판사");
		System.out.println();
		System.out.print("수정할 항목의 번호를 선택해주세요: ");
		String choice = scan.nextLine();
		
		if (choice.equals("1")) {
			editTitle(b);
		} else if (choice.equals("2")) {
			editAuthor(b);
		} else if (choice.equals("3")) {
			editPub(b);
		}
		
	}//editBook
	
	/**출판사를 입력받아 수정하는 메소드입니다. 
	 * 
	 * @param b2 : 수정/삭제 작업 할 책 정보 (도서검색에서 고유번호로 선택한 1권)
	 */
	private static void editPub(BookVO b2) {
		BookVO b = b2;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("어떻게 수정하시겠습니까?: ");
		String newPub = scan.nextLine();
		
		b.setPub(newPub);
		
		System.out.println("출판사 수정이 완료되었습니다.");
		System.out.println();
		MemberRemoveSerivce.pause();
		
	}//editPub
	
	/** 저자를 입력받아 책 정보를 수정하는 메소드입니다.
	 * 
	 * @param b2 : 수정/삭제 작업 할 책 정보 (도서검색에서 고유번호로 선택한 1권)
	 */
	private static void editAuthor(BookVO b2) {
		BookVO b = b2;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("어떻게 수정하시겠습니까?: ");
		String newAuthor = scan.nextLine();
		b.setAuth(newAuthor);
		
		System.out.println("저자 수정이 완료되었습니다.");
		System.out.println();
		MemberRemoveSerivce.pause();
		
		
		
	}//editAuthor

	/**책 제목을 입력받아 책 제목을 수정하는 메소드입니다. 
	 * 
	 * @param b2 : 수정/삭제 작업 할 책 정보 (도서검색에서 고유번호로 선택한 1권)
	 */
	public static void editTitle(BookVO b2) {
		BookVO b = b2;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("어떻게 수정하시겠습니까?: ");
		String newTitle = scan.nextLine();
		
		b.setTitle(newTitle);
		
		System.out.println("책 제목 수정이 완료되었습니다.");
		System.out.println();
		MemberRemoveSerivce.pause();
		
	}//editTitle

}
