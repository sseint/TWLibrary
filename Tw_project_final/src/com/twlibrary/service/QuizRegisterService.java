package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.QuizDAO;
import com.twlibrary.vo.QuizVO;
/**
 * [메인메뉴] > [관리자 로그인] > [이벤트 관리 메뉴] > [퀴즈 등록]
 * 관리자가 [퀴즈 등록]을 할 수 있는 클래스. [문제]와 [정답]을 각각 입력하여 makeQuiz() 메소드에 저장 후 QuizDAO에 저장한다.
 */
public class QuizRegisterService {
	/**
	 * 관리자의 이벤트 관리 메뉴에서 [퀴즈 등록]을 선택 시 호출되는 메소드
	 * 퀴즈 등록 시 makeQuiz() 메소드를 호출하여 QuizDAO에 저장하고, 미등록 시 관리자용 메인으로 돌아간다.
	 */
	public static void registerQuiz() {
		
		Scanner scan = new Scanner(System.in);

		System.out.print("퀴즈를 등록하시겠습니까? (Y/N) : ");
		String enroll = scan.nextLine();
		System.out.println();

		if (enroll.equals("y") || enroll.equals("Y")) {
			makeQuiz();
			System.out.println("퀴즈 등록이 완료되었습니다.");
		} else if (enroll.equals("n") || enroll.equals("N")) {
			System.out.println("메인으로 돌아가시려면 [엔터]를 입력해주세요.");
			scan.nextLine();
			System.out.println();
		}

	}
	/**
	 * 관리자가 퀴즈의 [문제][정답]을 String으로 입력하면 받은 후 QuizDAO.getQuizList()에 매개변수로 받아 저장하는 메소드
	 */
	public static void makeQuiz() {
		Scanner scan = new Scanner(System.in);

		System.out.println("문제를 입력하시오.");
		System.out.print("문제: ");		
		String question = scan.nextLine();
		System.out.println();
		System.out.println("정답을 입력하시오.");
		System.out.print("정답: ");
		String answer = scan.nextLine();
		System.out.println();
		QuizDAO.getQuizList().set(0, new QuizVO(question,answer)); 
	}

}
