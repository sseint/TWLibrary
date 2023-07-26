package com.twlibrary.service;

import java.util.Calendar;
import java.util.Scanner;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.MemberDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.BookVO;
import com.twlibrary.vo.MemberVO;
import com.twlibrary.vo.RentLogVO;

/**
 * 회원이 도서를 대출하는 기능을 제공하는 클래스입니다.
 * 서비스 기능을 호출받는 메소드는 rentBook(Book VO b) 메소드 입니다.
 * 이 메소드는 도서 검색서비스로부터 반환된 BookVO값을 매개변수로 이어받습니다.
 * 재고가 부족하면 빌릴 수 없습니다.
 * canRent() 메소드에서 로그인한 회원의 아이디와 대출로그 목록의 아이디 중에서 일치하는 목록 2개가 다 찼다면 빌릴 수 없습니다.
 * 빌릴 수 있는 조건이 충족되면 선택한 도서를 빌릴 것인지 확인을 받고 대출 예약을 성사합니다.
 * 대출한 회원의 아이디, 이름, 전화번호와 대출한 도서의 책 번호, 책 제목, 빌린 날짜, 반납 일자를 새로운 RentLogVO로 생성하여 RentLogDAO에 저장합니다.
 * 대출된 책의 수량을 감소합니다.
 */
public class LoginRentService {
	/**
	 * 호출되어 회원이 도서를 대출하는 기능을 제공하는 메소드
	 * 자세한 설명은 클래스 설명을 참고하세요. 
	 * @param b
	 */
	public static void rentBook(BookVO b) {
		
		Scanner scan = new Scanner(System.in);
		Calendar rentDay = Calendar.getInstance(); // 빌린 일자 > 오늘
		
		Calendar bannabDay = Calendar.getInstance();
		bannabDay.add(Calendar.DATE, 7); // 반납 일자 > 오늘 + 7
		
		
		BookVO selBook = b; // 가정) j번째 책
		
		
		
		
		
		
		if (selBook.getCount() == 0) {
			//재고가 부족하면
			System.out.println("(재고부족)더 이상 도서를 대출하실 수 없습니다.");
		} else if (!canRent()) {
			//회원이 책을 두권 모두 빌렸으면 
			System.out.println("(대여한도초과)더 이상 도서를 대출하실 수 없습니다.");
		} else {
			
			// 책을 빌릴 수 있으면
			while(true) {
				System.out.println();
				System.out.print("선택하신 도서를 대출하시겠습니까?(Y/N): ");
				String answer = scan.nextLine(); // Y/N
				
				if (answer.toUpperCase().equals("Y")) {
					
					RentLogVO nextRLVO = new RentLogVO(MemberVO.myLogin.getId()
							,MemberVO.myLogin.getName()
							,MemberVO.myLogin.getPhoneNum()
							,selBook.getNum()
							,selBook.getTitle()
							,rentDay
							,bannabDay);
					
					RentLogDAO.getList().add(nextRLVO); // 로그에 대출 정보 기록
					
					selBook.setCount(selBook.getCount()-1); // 책의 재고 감소
					MemberVO.myLogin.setCount(MemberVO.myLogin.getCount() + 1);
					
					System.out.printf("대출 예약이 완료되었습니다. 예약도서를 현장에서 수령해주세요. 반납일자는 %tF입니다.\r\n",bannabDay);
					break;
					
				} else if(answer.toUpperCase().equals("N")) {
					
					System.out.println("대출 예약을 취소합니다.");
					break;
					
				} else {
					System.out.println("Y 또는 N을 입력해주세요.");
					System.out.println();
				}
				
			}
				
		}
		
		System.out.println("메인 화면으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();
		
	}
	

	
	/**
	 * 책을 빌릴 수 있는지 확인하는 메소드
	 * 대여한도(2권) 검사한다.
	 * 회원정보는 MemberVO.myLogin에서 받아온다.
	 * @return boolean
	 */
	private static boolean canRent() {
		
		boolean canRent = true;
		
		String id = MemberVO.myLogin.getId();
		int count = 0;
		
		//랜트로그에 회원id를 세는 반복문
		for(RentLogVO r : RentLogDAO.getList()) {
			if (r.getId().equals(id)) {
				count++;
			}
		}
		
		//두 권 모두 빌렸는지 묻는 질문
		if (count == 2) {
			canRent = false;
		}
		
		return canRent;
		
	}


}
