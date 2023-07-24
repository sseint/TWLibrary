package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.BannabDAO;
import com.twlibrary.dao.BookDAO;
import com.twlibrary.vo.BannabVO;
import com.twlibrary.vo.BookVO;

/**
 * 관리자가 반납함을 조회하고 검수하여 재고를 리스탁하는 서비스를 제공하는 클래스입니다.
 * BannabVO의 목록을 출력해주고 책번호로 선택을 받습니다.
 * 선택한 번호의 도서를 검수했다는 메시지를 출력 후 도서의 고유번호와 일치하는 BookVO를 찾아 수량을 올려줍니다.
 *
 */
public class AdminBannabService {
	
	/**
	 * 서비스로 호출되는 메인 메소드
	 * BannabVO의 목록을 출력해주고 책번호로 선택을 받습니다.
	 * 선택한 번호의 도서를 검수했다는 메시지를 출력 후 도서의 고유번호와 일치하는 BookVO를 찾아 수량을 올려줍니다.
	 */
	public static void restock() {
		
		Scanner scan = new Scanner(System.in);
		boolean isExist = false;
		
		System.out.println("────────────반납함────────────");
		
		
		//반납함 출력 페이징(?)
		for (BannabVO bnb : BannabDAO.getList()) {
			System.out.printf("[책번호: %s\t\t제목: %s\t\t저자: %s\t\t출판사: %s]\n",
					bnb.getNum(),bnb.getTitle(),bnb.getAuth(),bnb.getPub());
			isExist = true;
		}
		
		if (isExist) {
			
			//검수할 도서 선택
			
			boolean valid = false;// 입력된 고유번호 유효검사
			
			System.out.print("검수할 도서의 고유번호를 입력해주세요: ");
			String num = scan.nextLine();
			
			for (BannabVO bnb : BannabDAO.getList()) {
				if (bnb.getNum().equals(num)) {
					valid = true; 
					while (true) {
						
						//재확인 문구 출력
						System.out.printf("[책번호: %s\t\t제목: %s\t\t저자: %s\t\t출판사: %s]\n",
								bnb.getNum(),bnb.getTitle(),bnb.getAuth(),bnb.getPub());
						System.out.print("선택하신 도서가 맞습니까?(Y/N): ");
						String answer = scan.nextLine();
						
						if (answer.toUpperCase().equals("Y")) {
							//재고 리스탁
							for (BookVO book : BookDAO.getList()) {
								if (book.getNum().equals(num)) {
									book.setCount(book.getCount() + 1);
								}
							}
							//반납함 삭제
							BannabDAO.getList().remove(BannabDAO.getList().indexOf(bnb));
							
							System.out.println("검수를 완료하였습니다. 재고를 리스탁합니다.");
							break;
						} else if(answer.toUpperCase().equals("N")) {
							System.out.println("검수를 취소합니다.");
							break;
						} else {
							System.out.println("Y 또는 N을 입력해주세요.");
						}
					} // Y 또는 N만을 입력받는 루프문
					break; // 고유번호 하나 찾았으면 반복문 탈출, 고유번호 중복문제때문
				}
				
			}
			
			if (!valid) {
				System.out.println("번호가 올바르지 않습니다.");
			}
			
		} else {
			System.out.println("────────반납함에 책이 없습니다────────");
		}
		
		System.out.println("메인 화면으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();
	}
}






