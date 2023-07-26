package com.twlibrary.service;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.BookVO;
import com.twlibrary.vo.RentLogVO;

/**
 * 비회원이 도서를 대출하는 기능을 제공하는 클래스입니다.
 * 서비스 기능을 호출받는 메소드는 rentBook(Book VO b) 메소드 입니다.
 * 이 메소드는 도서 검색서비스로부터 반환된 BookVO값을 매개변수로 이어받습니다.
 * 재고가 부족하면 빌릴 수 없습니다.
 * 비회원이 선택한 BookVO를 대출하기 원하면 이름과 전화번호를 입력받습니다.
 * 이름과 전화번호는 각각 nameIsValid(String Value) phoneIsValid(String Value) 메소드들을 통해 유효성 검사를 받습니다.
 * 입력 받은 이름과 전화번호는 canRent() 메소드에서 RentLogDAO에서 일치하는 목록 존재 여부를 반환받습니다.
 * 일치하는 목록이 없을 경우에 대출이 성사되며 
 * 이때의 입력된 이름, 전화번호와 대출한 도서의 책 번호, 책 제목, 빌린 날짜, 반납 일자를 새로운 RentLogVO로 생성하여 RentLogDAO에 저장합니다.
 * 대출된 책의 수량을 감소합니다.
 *
 */
public class UnLoginRentService {
	
	private static String name; // 비회원의 이름을 저장할 변수
	private static String phoneNum; // 비회원의 전화번호를 저장할 변수
	private static String regex = "";		//정규식
	private static Pattern pattern = null; //정규식 객체
	private static Matcher matcher = null; //결과 객체
	
	/**
	 * 호출되어 비회원이 책을 대출하는 기능을 제공하는 메소드
	 * 자세한 설명은 클래스 설명을 참고하세요.
	 * @param b
	 */
	public static void rentBook(BookVO b) {
		
		Scanner scan = new Scanner(System.in);
		Calendar rentDay = Calendar.getInstance(); // 빌린 일자 > 오늘
		
		Calendar bannabDay = Calendar.getInstance();
		bannabDay.add(Calendar.DATE, 7); // 반납 일자 > 오늘 + 7
		
		BookVO selBook = b;
		
		if (selBook.getCount() == 0) {
			System.out.println("(재고부족)더 이상 도서를 대출하실 수 없습니다.");
		} else {
			
			while (true) {
			System.out.println();
			System.out.print("선택하신 도서를 대출하시겠습니까?(Y/N): ");
			String answer = scan.nextLine(); // Y/N
				
				if (answer.toUpperCase().equals("Y")) {
					
					
					if (canRent()) {
						//비회원이 빌릴 수 있으면
						
						RentLogVO nextRLVO = new RentLogVO(""
								,name
								,phoneNum
								,selBook.getNum()
								,selBook.getTitle()
								,rentDay
								,bannabDay);
						
						RentLogDAO.getList().add(nextRLVO); // 로그에 대출 정보 기록
						
						selBook.setCount(selBook.getCount()-1); // 책의 재고 감소
						
						System.out.printf("대출 예약이 완료되었습니다. 예약도서를 현장에서 수령해주세요. 반납일자는 %tF입니다.\r\n",bannabDay);
						
					} else {
						
						//비회원이 이미 한 권을 빌렸으면
						System.out.println("(대여한도초과)더 이상 책을 대출하실 수 없습니다.");
						System.out.println("회원가입을 하시면 총 2권까지 대출하실 수 있습니다.");
					}
					
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
	 * 비회원의 정보를 받아오고 연체로그와 대조해 빌릴 수 있는지 검사한다.
	 * @return
	 */
	private static boolean canRent() {
		
		boolean canRent = true;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[비회원 대여]이름과 전화번호를 입력해주세요.");

		do {
			System.out.print("이름(한글2~5자): ");
			name=scan.nextLine();
		} while(!nameIsValid(name));
		
		do {
			System.out.print("전화번호(000-0000-0000): ");
			phoneNum=scan.nextLine();
		} while(!phoneIsValid(phoneNum));
		
		//랜트로그에 이름과 전화번호가 있는지 보는 반복문
		for(RentLogVO r : RentLogDAO.getList()) {
			if (r.getId().equals("") // 아이디가 공란(빈문자열)
					&& r.getName().equals(name) // 이름이 중복
					&& r.getPhoneNum().equals(phoneNum)) { // 전화번호가 중복
				canRent = false;
			}
		}
		
		return canRent;
	}
	/**
	 * 입력된 이름이 유효한지 검사하는 메소드 (한글, 2~5자)
	 * @param name
	 * @return 유효성
	 */
	private static boolean nameIsValid(String name) {
		
		
		//1. 이름 > 필수입력, 한글, 2~5자 이내
		regex = "^[가-힣]{2,5}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(name);
		
		
		if(!matcher.find()) {
			System.out.println("이름을 필수입력, 한글, 2~5자 이내로 입력하세요.");
			return false;
		}
		return true;
	}
	/**
	 * 입력된 전화번호가 유효한지 검사하는 메소드 (000-0000-0000) 형식
	 * @param phoneNum
	 * @return 유효성
	 */
	private static boolean phoneIsValid(String phoneNum) {
		//3. 전화번호 > OOO-OOOO-OOOO || OOO-OOO-OOOO
		regex = "^([0-9]{3}-[0-9]{3,4}-[0-9]{4})$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(phoneNum);
		
		if(!matcher.find()) {
			System.out.println("전화번호가 올바른 형식이 아닙니다.");
			return false;
		}
		
		return true;
	}
	

}

