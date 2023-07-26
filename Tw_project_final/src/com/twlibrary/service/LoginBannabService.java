package com.twlibrary.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.twlibrary.dao.BannabDAO;
import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.MemberDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.BannabVO;
import com.twlibrary.vo.BookVO;
import com.twlibrary.vo.MemberVO;
import com.twlibrary.vo.RentLogVO;

/**
 * 회원이 대출한 도서를 반납하는 서비스를 제공하는 기능을 가진 클래스입니다.
 * 호출되어 반납서비스를 제공하는 메소드는 bannabService() 메소드입니다.
 * viewMyRentLog()메소드를 통해 현재 대출한 도서의 목록을 출력해줍니다.
 * 없다면 없다는 메시지와 함께 되돌아갑니다.
 * 한권 있다면 그 책을 반납하겠냐는 메시지를 출력하여 확인을 받고 반납합니다.
 * 두권 있다면 두권중 한권만 반납할지 두권 모두 반납할지 선택받습니다.
 * 두권 모두 반납한다면 두권 모두 반납처리됩니다.
 * 한권만 반납을 선택했을 시 두권중 어떤 책을 반납할지 고유번호를 입력받습니다.
 * 입력받은 고유번호의 책을 반납할지 확인하는 메시지를 출력 후 확인받고 반납합니다.
 * 반납된 책의 고유번호, 제목, 저자, 출판사를 담은 새로운 BannabVO를 생성하여 BannabDAO에 추가합니다.
 * 그리고 RentLogDAO에 남아있던 VO를 제거해줍니다.
 * 반납 완료 메시지와 함께 만약 반납기한을 넘겨 연체가 되었다면 도서 가격의 연체 일수당 1/100의 연체료를 부과하는 메시지를 출력해줍니다.
 * 연체료는 UnLoginBannabService 클래스의 getOverduePrice (RentLogVO r, BookVO book)메소드가 계산합니다.
 */
public class LoginBannabService {
	
	private static ArrayList<RentLogVO> logList = new ArrayList<RentLogVO>(); // 출력할 대출로그 저장할 배열
	private static ArrayList<BookVO> bookList = new ArrayList<BookVO>(); // 반납할 책들 저장할 변수
	private static int logIndex = -1; // 삭제할 도서의 랜트로그의 인덱스
	private static boolean loop = true; // 루프문에서 탈출할 변수
	private static MemberVO me = MemberVO.myLogin;
	private static ArrayList<Integer> overduePriceList = new ArrayList<Integer>();; // 연체료 저장 리스트
	
	
	/**
	 * 서비스로 호출되는 메인메소드.
	 * 자세한 설명은 클래스 설명을 참고하세요.
	 */
	public static void bannabService() { //메인 메소드
		
		Scanner scan = new Scanner(System.in);
		
		int rentCount = viewMyRentLog();
		

		
		if (rentCount == 2) {
			if (logList.size() == 2) { // 책이 두 권일 경우
				System.out.print("몇 권을 반납하시겠습니까?(1,2 이외 입력시 반납취소): ");
				String bookCount = scan.nextLine();
				
				if (bookCount.equals("1")) {
					//한 권 반납
					bannabOne();
				} else if (bookCount.equals("2")) {
					//두 권 반납
					bannabTwo();
				} else {
					System.out.println("반납이 취소됩니다.");
				}
			} 
			
		} else if (rentCount == 1){ // 책이 한 권 일경우
			loop = true;
			while (loop) {
				System.out.print("대출하신 도서를 반납하시겠습니까?(Y/N): ");
				String answer = scan.nextLine();
				singleBannab(answer);
			}
		} else if (rentCount == 0) {
			System.out.println("대출 목록 없음");
		}
		
		logList.clear();
		bookList.clear();
		overduePriceList.clear();
		
		System.out.println("메인 화면으로 돌아가시려면 [엔터]를 입력해주세요.");
		
		scan.nextLine();
		
	}
	
	/**
	 * 책의 고유 번호를 인자로 받아 BookDAO에서 일치하는 고유번호를 반환해주는 메소드
	 * @param num
	 * @return BookVO
	 */
	public static BookVO matchBook(String num) {
		
		BookVO temp = new BookVO();
		
		for (BookVO b : BookDAO.getList()) {
			if(b.getNum().equals(num)) {
				temp = b;
				return temp;
			}
		}
		
		return temp;
	}
	/**
	 * 회원의 랜트로그를 탐색하여 리스트에 책정보와 랜트로그 정보와 연체료를 저장하고 대출 도서가 몇권인지 int 반환한다.
	 * @return int rentCount
	 */
	private static int viewMyRentLog() {
		
		System.out.println("-대출 현황-");
		
		for (RentLogVO r : RentLogDAO.getList()) {
			if (r.getId().equals(me.getId())) {
				System.out.printf("[책번호: %s\t\t제목: %s\t\t반납 기한: %tF]\n",
						r.getBookNum(), r.getBookTitle(), r.getBannabDay());
				
				// logIndex : 두 권이면 나중 것의 값을 저장하지만 두 권일 경우 한 권만 선택시 다시 받는 코드를 구현했음
				logIndex = RentLogDAO.getList().indexOf(r);
				logList.add(r);
				bookList.add(matchBook(r.getBookNum()));
				overduePriceList.add(UnLoginBannabService.getOverduePrice(r, matchBook(r.getBookNum()))); // 아무튼 연체료 넣는 상황
			}
		}
		
		return logList.size(); // 대출한 도서를 저장한 리스트의 크기
		
	}
	/**
	 * 반납할 도서 두권 중 한권을 선택받아 반납하는 메소드
	 * 반납할 도서의 고유번호를 입력받는다.
	 * 입력받은 고유번호와 일치하는 대출도서를 재출력해주고 viewMyRentLog()에서 저장한 리스트 세개(책,대출로그,연체료)를 초기화하고 다시 하나씩 저장해준다.
	 * 그리고서 다시 확인 메시지 출력 후 응답을 singleBannab() 메소드에 전달해준다.
	 */
	private static void bannabOne() {
		
		Scanner scan = new Scanner(System.in);

		loop = true;
		while (loop) {
			
			System.out.print("반납하실 도서의 고유번호를 입력해주세요: ");
			String num = scan.nextLine();
			
			//입력받은 고유번호와 일치하는 대출도서를 재출력해주고 리스트 두개를 초기화하고 다시 하나씩 저장해준다.
			for (RentLogVO r : logList) {
				if (num.equals(r.getBookNum())) {
					System.out.printf("%5s\t\t%s\t\t%tF\n",r.getBookNum(),r.getBookTitle(),r.getBannabDay());
					logList.clear();
					bookList.clear();
					overduePriceList.clear();
					logList.add(r);
					bookList.add(matchBook(num));
					overduePriceList.add(UnLoginBannabService.getOverduePrice(r, matchBook(r.getBookNum())));
					loop = false; // 유효한 고유번호를 입력한 경우
					break;
				}
			}
			
		}
		
		loop = true;
		while (loop) {
			
		System.out.print("반납하실 도서가 맞습니까?(Y/N): ");
		String answer = scan.nextLine();
			singleBannab(answer);
		}
		
		
	}
	/**
	 * 두권 모두 받납하는 응답을 받으면 대답을 doubleBannab()에 전달하는 메소드
	 */
	private static void bannabTwo() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("두 권 모두 반납하시겠습니까?(Y/N): ");
		String answer = scan.nextLine();
		doubleBannab(answer);
		
	}
	
	/**
	 * 도서를 반납하겠냐는 응답을 매개변수(String answer)로 받아 반납처리 해주는 메소드
	 * 반납 확인 응답을 받으면 세개의 리스트에 저장된 대출로그(removeLog()), 책정보(addBannab()), 연체료(sumOverduePrice())를 처리하는 메소드를 호출한다.
	 * 반납 성공 메시지와 연체료를 출력해준다.
	 * @param answer
	 */
	private static void singleBannab(String answer) {
		
		if (answer.toUpperCase().equals("Y")) {
			removeLog();
			addBannab();
			System.out.printf("성공적으로 반납되었습니다. 연체료는 %d원 입니다.\n", sumOverduePrice());
			loop = false;
		} else if (answer.toUpperCase().equals("N")) {
			System.out.println("반납이 취소됩니다.");
			loop = false;
		} else {
			System.out.println("Y 또는 N을 입력해 주세요");
		}
		
	}
	/**
	 * 도서를 반납하겠냐는 응답을 매개변수(String answer)로 받아 반납처리 해주는 메소드
	 * 반납 확인 응답을 받으면 세개의 리스트에 저장된 두권의 대출로그(removeLog()), 책정보(addBannab()), 연체료(sumOverduePrice())를 처리하는 메소드를 호출한다.\
	 * 반납 성공 메시지와 연체료를 출력해준다.
	 * @param answer
	 */
	private static void doubleBannab(String answer) {
		
		if (answer.toUpperCase().equals("Y")) {
			removeLog();
			addBannab();
			System.out.printf("성공적으로 반납되었습니다. 연체료는 %d원 입니다.\n", sumOverduePrice());
			loop = false;
		} else if (answer.toUpperCase().equals("N")) {
			System.out.println("반납이 취소됩니다.");
			loop = false;
		} else {
			System.out.println("Y 또는 N을 입력해 주세요");
		}
		
	}
	
	/**
	 * 호출 전의 과정에서 static logList에 저장된 대출로그들을 삭제하는 메소드
	 */
	private static void removeLog() {
		for(RentLogVO r : logList) {
			logIndex = RentLogDAO.getList().indexOf(r);
			RentLogDAO.getList().remove(logIndex); // RentLog에서 로그 삭제
			
		}
	}
	/**
	 * 호출 전의 과정에서 static bookList에 저장된 책 정보들을 반납함BookDAO에 저장하는 메소드
	 */
	private static void addBannab() {
		for (BookVO book : bookList) {
			BannabVO temp = new BannabVO(book.getNum(),book.getTitle(),book.getAuth(),book.getPub());
			BannabDAO.getList().add(temp);
		}
	}
	/**
	 * 호출 전의 과정에서 static overduePriceList에 저장된 연체료들을 합해서 반환해주는 메소드
	 * @return int 연체료 총합
	 */
	private static int sumOverduePrice() {
		int sum = 0;
		for (Integer price : overduePriceList) {
			sum += price;
		}
		return sum;
	}
	
}





