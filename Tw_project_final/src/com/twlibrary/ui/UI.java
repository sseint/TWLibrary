package com.twlibrary.ui;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.twlibrary.dao.QuizDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.MemberVO;
import com.twlibrary.vo.RentLogVO;
/**
 * 콘솔 화면에 출력되는 UI를 작성하는 클래스이며, 기능적인 요소 없이 출력용으로만 사용됩니다.
 */
public class UI {
	/**
	 * 콘솔 실행 시 가장 먼저 출력되는 도메인 메소드입니다.
	 */
	public static void domainUI() {
		System.out.println("\n" + "\t\t\t _    _        _                                /\\/|\r\n"
				+ "\t\t\t| |  | |      | |                              |/\\/ \r\n" + "\t\t\t| |  | |  ___ | |  ___   ___   _ __ ___    ___      \r\n"
				+ "\t\t\t| |/\\| | / _ \\| | / __| / _ \\ | '_ ` _ \\  / _ \\     \r\n" + "\t\t\t\\  /\\  /|  __/| || (__ | (_) || | | | | ||  __/     \r\n"
				+ "\t\t\t \\/  \\/  \\___||_| \\___| \\___/ |_| |_| |_| \\___|   ");

		System.out.println(" _____                _    _                             _      _____ ______ ______   ___  ______ __   __ \r\n"
				+ "|_   _|              | |  | |                           | |    |_   _|| ___ \\| ___ \\ / _ \\ | ___ \\\\ \\ / /\r\n"
				+ "  | |    __ _   ___  | |  | |  __ _  _ __               | |      | |  | |_/ /| |_/ // /_\\ \\| |_/ / \\ V / \r\n"
				+ "  | |   / _` | / _ \\ | |/\\| | / _` || '_ \\              | |      | |  | ___ \\|    / |  _  ||    /   \\ /  \r\n"
				+ "  | |  | (_| ||  __/ \\  /\\  /| (_| || | | |             | |____ _| |_ | |_/ /| |\\ \\ | | | || |\\ \\   | |  \r\n"
				+ "  \\_/   \\__,_| \\___|  \\/  \\/  \\__,_||_| |_|             \\_____/ \\___/ \\____/ \\_| \\_|\\_| |_/\\_| \\_|  \\_/");

		System.out.println();
		System.out.println("               《 태완도서관 》");
		System.out.println();
		System.out.println("            😊😊😊환영합니다😊😊😊");
		System.out.println();  
		System.out.println("  ┌───────[메인  화면]───────┐");
		System.out.println("  │                                        │");
		System.out.println("  │       1. 로그인(회원, 관리자)          │");
		System.out.println("  │       2. 비회원 이용                   │");
		System.out.println("  │       3. 회원가입                      │");
		System.out.println("  │       4. 이벤트                        │");
		System.out.println("  │       0. 프로그램 종료                 │");
		System.out.println("  │                                        │");
		System.out.println("  └────────────────────┘");
		System.out.println("         ☎ 문의 전화번호: 02-123-5678​");
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.print("원하는 메뉴를 선택해주세요(숫자): ");
	}
	
	/**
	 * 메인화면에서 로그인 선택 시 호출되는 로그인 메뉴 화면 메소드입니다.
	 */
	public static void login() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println();
		System.out.println("  ┌──────────────[로그인]────────────┐");
		System.out.println("  │                                                            │");
		System.out.println("  │    1. 회원 로그인   2. 관리자 로그인  0. 메인으로 돌아가기 │");
		System.out.println("  │                                                            │");
		System.out.println("  └──────────────────────────────┘");
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println();
		}

	/**
	 * 관리자 로그인 시 호출되는 관리자용 메인 화면 메소드입니다.
	 */
	public static void adminLogin() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println();
		System.out.println("  ┌─────[관리자용 메인 화면]─────┐");
		System.out.println("  │                                        │");
		System.out.println("  │   1. 도서 관리 메뉴                    │");
		System.out.println("  │   2. 회원 관리 메뉴                    │");
		System.out.println("  │   3. 이벤트 관리 메뉴                  │");
		System.out.println("  │   0. 메인화면으로 돌아가기             │");
		System.out.println("  │                                        │");
		System.out.println("  └────────────────────┘");
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.print("원하시는 메뉴의 번호를 입력해주세요:​ ");

	}
	
	/**
	 * 관리자용 메인화면에서 [이벤트 관리 메뉴] 선택 시 호출되는 이벤트 관리 메뉴 화면 메소드입니다.
	 */
	public static void manageEvent() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println();
		System.out.println("  ┌─────[이벤트 관리 메뉴]──────┐");
		System.out.println("  │                                        │");
		System.out.println("  │   1. 이달의 퀴즈 등록                  │");
		System.out.println("  │   2. 당첨자 확인                       │");
		System.out.println("  │   3. 이달의 독서왕 초기화              │");
		System.out.println("  │   0. 메인화면으로 돌아가기             │");
		System.out.println("  │                                        │");
		System.out.println("  └────────────────────┘");
		System.out.println();
		System.out.println("────────────────────────────────────");
		
	}
	
	/**
	 * 메인화면 > [이벤트] 및 회원 > [퀴즈 맞히기] 선택 시 호출되는 메소드입니다. QUizDAO에 저장된 퀴즈의 문제를 출력합니다.
	 */
	public static void showEvent() {
		
		if(QuizDAO.getQuizList().size() > 0) {
			System.out.println("────────────[이달의 퀴즈를 맞춰보세요]───────────");
			System.out.println();
			System.out.println(QuizDAO.getQuizList().get(0).getQuestion());
			System.out.println();
			System.out.println("────────────────────────────────────");
			System.out.println();
			
		} else {
			System.out.println("─────────────[퀴즈가 없습니다.]──────────────");
			
		}
			
		
	}

	/**
	 * 회원 로그인 시 호출되는 메소드입니다. 
	 * 로그인 한 회원의 정보를 MemberVO로부터 이름, 누적 대출 권수 및 RentLogDAO로부터 대출 현황, 대출 일자, 반납 일바를 출력합니다.
	 * 
	 */
	public static void memberMenuUI() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println("회원 로그인 창");
		System.out.println("────────────────────────────────────");
		System.out.printf("%s 님 환영합니다. 누적 대출 권수 : %d\n"
				, MemberVO.myLogin.getName()
				, MemberVO.myLogin.getCount());
		System.out.println("────────────────────────────────────");
		System.out.println("\t\t대출 현황\t\t[대출 일자]\t\t[반납 일자]");
		System.out.println("────────────────────────────────────");
		RentLogVO r;
		String book = "";
		for(int j=0; j<RentLogDAO.getList().size(); j++) {
			r=RentLogDAO.getList().get(j);
				if(MemberVO.myLogin.getId().equals(r.getId())) {
					System.out.println(String.format("%s\t\t%tF\t\t%tF\n",convert(r.getBookTitle(), 30),r.getRentDay(),r.getBannabDay()));
				}
		}
		System.out.println(book);
		System.out.println("  ┌────────────[회원  메뉴]─────────────┐");
		System.out.println("  │                                                              │");
		System.out.println("  │   1. 책 검색         2. 책 반납         3. 회원정보 수정     │");
		System.out.println("  │   4. 희망도서 작성   5. 퀴즈 도전       0. 로그아웃          │");
		System.out.println("  │                                                              │");
		System.out.println("  └───────────────────────────────┘");
		System.out.print("메뉴 선택: ");
	}

	/**
	 * 메인화면 이동 전 호출하는 메소드입니다.
	 */
	public static void returnMemberUI() {
		try {
			System.out.println("메인화면으로 이동합니다..");
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 관리자가 연체도서 조회 시 출력되는 메소드입니다.
	 */
	public static void rentLogUI() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println("\t           연체 도서 목록");
		System.out.println("────────────────────────────────────");
		System.out.println("고유번호\t책 제목\t\t\t    이름\t 전화번호\t    연체일자\t연체금액");
	}

	/**
	 * 관리자가 희망도서 조회 시 출력되는 메소드입니다.
	 */
	public static void wishListUI() {
		System.out.println();
		System.out.println("────────────────────────────────────");
		System.out.println("\t           희망 도서 목록");
		System.out.println("────────────────────────────────────");
		System.out.println("번호\t책 제목\t\t\t저자\t출판사");
	}

	/**
	 * 회원이 희망도서 작성 시 출력되는 메소드입니다.
	 */
	public static void wishListAddUI() {
		Scanner scan = new Scanner(System.in);
		System.out.print("희망 도서를 신청하시겠습니까? (Y / N) : ");
		String str = scan.nextLine();

	}

	/**
	 * 회원이 회원정보 수정 시 출력되는 메소드이며, MemberVO.myLogin 변수에 저장된 로그인 한 회원의 정보를 출력합니다.
	 */
	public static void memberEditUI() {
		System.out.println();
		System.out.println("────────────[회원 정보 수정]────────────────");
		System.out.println("이름: " + MemberVO.myLogin.getName());
		System.out.println("아이디: " + MemberVO.myLogin.getId());
		System.out.println("생년월일: " + MemberVO.myLogin.getBirth());
		System.out.println("전화번호: " + MemberVO.myLogin.getPhoneNum());
		System.out.println("────────────────────────────────────");
		System.out.println("1.이름    2.전화번호    3.비밀번호");

	}

	/**
	 * 관리자가 당첨자 조회 시 출력되는 메소드입니다.
	 */
	public static void showWinner() {
		System.out.println();
		System.out.println("──────────[이번 달 퀴즈 당첨자]──────────────");
		System.out.println();
		System.out.println("[이름]    [아이디]   [전화번호]");
		System.out.println();
	      
	      
	   }
	
	/**
	 * 책 제목으로 도서 검색 시 String kor를 매개변수로 받아 '한글' 유효성 검사를 시행하는 메소드입니다.
	 * 유효성 검사 완료 시 int cnt를 반환합니다.
	 * @param kor
	 * @return cnt
	 */
	private static int getKorCnt(String kor) {
	    int cnt = 0;
	    for (int i = 0 ; i < kor.length() ; i++) {
	        if (kor.charAt(i) >= '가' && kor.charAt(i) <= '힣') {
	            cnt++;
	        }
	    } return cnt;
	}
	
	/**
	 * String word와 int size를 매개변수로 받아 해당 문자 및 숫자의 byte 수를 줄여주는 메소드입니다.
	 * @param word
	 * @param size
	 * @return
	 */
	public static String convert(String word, int size) {
	    String formatter = String.format("%%%ds", size -getKorCnt(word));
	    return String.format(formatter, word);
	}
	
	/**
	 * 호출 시 1초간 일시정지 하는 메소드입니다.
	 */
	public static void pause() {

		try {
			LocalDateTime.now();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
