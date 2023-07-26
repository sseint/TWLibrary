package com.twlibrary.service;

import java.util.Calendar;
import java.util.Scanner;

import com.twlibrary.dao.BannabDAO;
import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.BannabVO;
import com.twlibrary.vo.BookVO;
import com.twlibrary.vo.RentLogVO;

/**
 * 비회원이 대출한 도서를 반납하는 서비스를 제공하는 기능을 가진 클래스입니다.
 * 호출되어 반납서비스를 제공하는 메소드는 bannabService() 메소드입니다.
 * 반납하실 비회원의 이름과 전화번호를 입력받습니다.
 * 입력받은 내용과 RentLogDAO를 대조해 일치하는 로그를 읽어와 반납확인 메시지를 출력합니다.
 * 반납에 동의하는 입력을 받으면 반납된 책의 고유번호, 제목, 저자, 출판사를 담은 새로운 BannabVO를 생성하여 BannabDAO에 추가합니다.  
 * 그리고 RentLogDAO에 남아있던 VO를 제거해줍니다.
 * 반납 완료 메시지와 함께 만약 반납기한을 넘겨 연체가 되었다면 도서 가격의 연체 일수당 1/100의 연체료를 부과하는 메시지를 출력해줍니다.
 * 연체료는 getOverduePrice (RentLogVO r, BookVO book)메소드가 계산합니다.
 */
public class UnLoginBannabService {
	/**
	 * 호출되어 비회원이 책을 반납하는 서비스를 제공하는 메소드
	 * 자세한 설명은 클래스 설명을 참고하세요.
	 */
	public static void bannabService() { // 메인 메소드
		
		Scanner scan = new Scanner(System.in);
		boolean isExist = false;
		int overduePrice = 0;
		
		System.out.println("반납하실 분의 성함, 전화번호를 입력받습니다.");
		System.out.print("이름(한글2~5자): ");
		String name = scan.nextLine();
		
		System.out.print("전화번호(000-0000-0000): ");
		String phoneNum = scan.nextLine();
		
		
		for (RentLogVO r : RentLogDAO.getList()) {
			if (r.getId().equals("") //아이디가 공란 == 비회원
					&& r.getName().equals(name) // 이름 일치 
					&& r.getPhoneNum().equals(phoneNum)) { // 번호 일치
				
				isExist = true;
				
				BookVO book = LoginBannabService.matchBook(r.getBookNum());
				BannabVO bnb = new BannabVO(book.getNum(),book.getTitle(),book.getAuth(),book.getPub());
				BannabDAO.getList().add(bnb); // 반납함에 추가
				overduePrice = getOverduePrice(r, book); // 연체료 검사
				RentLogDAO.getList().remove(RentLogDAO.getList().indexOf(r)); // 로그에서 삭제
				break;
			}
		}
		
		if (isExist) {
			System.out.printf("성공적으로 반납되었습니다. 연체료는 %d원 입니다.\n", overduePrice);
		} else {
			System.out.println("대출 기록이 존재하지 않습니다.");
		}
		
		
		System.out.println("메인 화면으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();
		
	}
	/**
	 * 매개변수로 받은 대출로그로부터 연체 일자를 계산한다.
	 * 매개변수로 받은 책정보로부터 가격의 1/100을 계산한다.
	 * 계산된 연체일자와 가격의 1/100 을 곱하여 연체료를 책정하여 반환한다. 
	 * @param r 빌린 회원의 대출로그
	 * @param book 빌린 책
	 * @return int 연체료
	 */
	public static int getOverduePrice (RentLogVO r, BookVO book) {
		
		Calendar now = Calendar.getInstance();
		
		int day =  (int)((now.getTimeInMillis()/ 60 / 60 / 24 / 1000) - (int)(r.getBannabDay().getTimeInMillis()/ 60 / 60 / 24 / 1000));
		
		int bookPrice = Integer.parseInt(book.getPrice());
		
		int lastPrice = bookPrice * day/100;
		
		if (lastPrice < 0) {
			lastPrice = 0;
		}
		
		return lastPrice;
		
	}
	

}









