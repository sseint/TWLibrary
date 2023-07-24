package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.vo.BookVO;

/**
 * <p>[관리자] - [도서 삭제] 화면에서 책을 삭제할 때 사용하는 메소드입니다. 
 * Main 클래스에서 removeBook()을 호출하면 선택한 책의 정보가 삭제됩니다. 
 * BookSearchService()를 실행하여 책을 검색한 후에 실행됩니다. 책목록에서 제거할 책의 인덱스는 indexForRemove입니다. </p>
 * <p>(예) BookRemoveService.removeBook();</p>
 */
public class BookRemoveService {
	//boos.txt에서 제거할 행의 번호
	private static int indexForRemove = -1;
	
	/**
	 * 최종적으로 도서삭제를 실행하는 메소드입니다. 
	 */
	public static void removeBook(BookVO b2) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("[삭제]");
		
		getIndex(b2);
		removeOrNot(indexForRemove);
	
	} //removeBook
	
	
	/**<p>선택한 책의 유무와 books.txt의 몇번째 행에 위치하는지 인덱스를 구한 후에 책을 삭제할지, 삭제를 취소할지 결정하는 메소드입니다.
	 * 매개변수로는 getIndex()에서 구한 인덱스를 가지며 반환값은 없습니다.</p>
	 * 
	 * @param index : 책 목록(books.txt)에서 제거할 행의 인덱스
	 * 
	 */
	private static void removeOrNot(int index) {
		BookVO b = BookDAO.getList().get(index);
		Scanner scan = new Scanner(System.in);
		System.out.print("선택하신 책의 정보를 삭제하시겠습니까? (Y/N): ");
		String chocie = scan.nextLine();

		if (chocie.equals("Y")) {
			System.out.println("삭제를 완료했습니다.");
			BookDAO.getList().remove(indexForRemove);
			MemberRemoveSerivce.pause();
		} else if (chocie.equals("N")) {
			System.out.println("삭제를 취소했습니다.");
			MemberRemoveSerivce.pause();
		} else {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
		}
	}
	
	
	/** <p>삭제할 책의 정보를 매개변수 BookVO로 받아서 그 책이 책 목록에 존재하는지 검사하는 메소드입니다. 
	 * 책 목록(BookDAO)을 한 줄씩 읽어서 목록에 있는 책 번호와 관리자가 선택한 책 번호가 동일하면 해당 인덱스를 indexForRemove에 저장합니다.
	 * </p> 
	 * 
	 * @param b2 : b : 수정/삭제 작업 할 책 정보 (도서검색에서 고유번호로 선택한 1권)
	 */
	public static void getIndex(BookVO b2) {
		for (int i=0; i<BookDAO.getList().size(); i++) {
			BookVO b = BookDAO.getList().get(i);
			if (b2.getNum().equals(b.getNum())) {
				indexForRemove = BookDAO.getList().indexOf(b2);
			} else { 
				continue;
			}
			
		}
	}

}