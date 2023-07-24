package com.twlibrary.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twlibrary.dao.MemberDAO;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.MemberVO;

/**
 * 회원가입 클래스
 * 이름, 생일, 전화번호, 아이디, 비밀번호를 입력받은 후 각각 맞는 형식에 따라
 * 유효성 검사, 그리고 검사가 완료된 항목들은 MemberVO에 저장해서 MemberDAO을 통해 member.txt에 저장
 */
public class RegisterService {
	public static String regex = ""; //정규식
	public static Pattern pattern = null; //정규식 객체
	public static Matcher matcher = null; //결과 객체
	/**
	 * 회원가입 클래스
	 * 이름, 생일, 전화번호, 아이디, 비밀번호를 입력받은 후 각각 맞는 형식에 따라
	 * 유효성 검사, 그리고 검사가 완료된 항목들은 MemberVO에 저장해서 MemberDAO을 통해 member.txt에 저장
	 */
	public static void register() {

		boolean loop = true;

		while (loop) {
			System.out.println("[회원 가입]");
			Scanner scan = new Scanner(System.in);
			String name = "";
			String birth = "";
			String id = "";
			String phone = "";
			String pw = "";

			do {
				System.out.print("이름: ");			//선 입력 후
				name = scan.nextLine();		
			} while (!nameIsValid(name)); 			//유효성검사와 일치하지 않으면 정지

			do {
				System.out.println();
				System.out.print("생일('-' 필수 기입) ex) OOOO-OO-OO : ");
				birth = scan.nextLine();
			} while (!birthIsValid(birth)); 
			do {
				System.out.print("전화번호: ");
				phone = scan.nextLine();
			} while (!phoneIsValid(phone)); 

			do {
				System.out.print("아이디: ");
				id = scan.nextLine();
			} while (!idIsValid(id)); 

			do {
				System.out.print("비밀번호: ");
				pw = scan.nextLine();
			} while (!pwIsValid(pw)); 

			loop = false;					//멈추지 않고 진행되면
			MemberVO m = new MemberVO();	//MemberVO에 입력값 setter
			m.setName(name);
			m.setBirth(birth);
			m.setPhoneNum(phone);
			m.setId(id);
			m.setPw(pw);
			m.setEvent("1");
			MemberDAO.getList().add(m);
			System.out.println("회원가입이 완료되었습니다. 메인으로 돌아갑니다.");
			UI.pause();
			
		}

		//날짜(2023-03-03) > "^[0-9]{4}-[0-9]{2}-[0-9]{2}$"
		//시간(14:30) > "^[0-9]{2}:[0-9]{2}$"
	}
	
	//이름 유효성 검사
	public static boolean nameIsValid(String name) {

		//1. 이름 > 필수입력, 한글, 2~5자 이내
		regex = "^[가-힣]{2,5}$";
		pattern = Pattern.compile(regex);	//Pattern.compile() : 정규표현식으로부터 패턴을 만듦
		matcher = pattern.matcher(name);	//대상 문자열이 패턴과 일치할 경우 true 반환

		if (!matcher.find()) {	//matcher.find() : 패턴이 일치하는 경우 true 반환, 그 위치로 이동
			System.out.println("이름을 필수입력, 한글, 2~5자 이내로 입력하세요.");
			return false;
		}
		return true;
	}
	//생년월일 유효성 검사
	public static boolean birthIsValid(String birth) {

		//2. 생년월일 > OOOO-OO-OO
		regex = "^([0-9]{0,4})-([0-9]{0,2})-([0-9]{0,2})$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(birth);
		if (!matcher.find()) {
			System.out.println("형식에 맞게 생일을 입력하세요.");
			return false;
		}
		return true;
	}
	//전화번호 유효성 검사
	public static boolean phoneIsValid(String phone) {
		//3. 전화번호 > OOO-OOOO-OOOO || OOO-OOO-OOOO
		regex = "^(010-[0-9]{3,4}-[0-9]{4})$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(phone);

		if (!matcher.find()) {
			System.out.println("전화번호가 올바른 형식이 아닙니다.");
			return false;
		}

		return true;
	}
	//아이디 유효성 검사
	public static boolean idIsValid(String id) {
		//4. 아이디 > 필수입력, 영어+숫자+_, 숫자로 시작 불가능, 5~12자 이내
		regex = "^[A-Za-z_][A-Za-z0-9_]{5,12}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(id);

		if (!matcher.find()) {
			System.out.println("아이디 > 필수입력, 영어+숫자+_, 숫자로 시작 불가능, 5~12자 이내");
			return false;
		}
		for (MemberVO m : MemberDAO.getList()) {
			if (id.equals(m.getId())) {
				System.out.println("아이디 중복");
				return false;
			}
		}

		return true;
	}
	//비밀번호 유효성 검사
	public static boolean pwIsValid(String pw) {
		//5. 비밀번호 > 영대소문자, 숫자, 5자이상
		regex = "^[A-Za-z0-9]{5,}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(pw);

		if (!matcher.find()) {
			System.out.println("비밀번호 > 영대소문자, 숫자, 5자이상");
			return false;
		}

		return true;
	}
}
