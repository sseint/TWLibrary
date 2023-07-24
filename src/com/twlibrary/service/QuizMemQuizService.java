package com.twlibrary.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import com.twlibrary.dao.QuizDAO;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.MemberVO;
/**
 * [메인 메뉴] > [회원 로그인] > [퀴즈 맞히기]
 * 회원이 퀴즈 참여 시 [정답]을 제출하고, 해당 회원의 로그인 된 [ID][이름][전화번호]가 correctList.txt파일에 저장되는 클래스 입니다.
 */
public class QuizMemQuizService {
	
	/**
	 * 회원 메뉴에서 [5. 퀴즈 맞히기]를 선택 시 호출되는 메소드 입니다.
	 * 회원의 퀴즈 도전 여부 및 도전 시 정답/오답을 구분하는 participate() 메소드를 호출합니다.
	 */
	public static void memQuiz() {
		Scanner scan = new Scanner(System.in);
		participate();

		UI.pause();
		System.out.println();
		System.out.println();
		System.out.println("메인으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();

	}

	/**
	 * 회원이 퀴즈 도전 시 [정답]과 [오답]을 구분하고, [정답] 시 회원의 정보를 저장하는 메소드 입니다.
	 * 회원이 입력한 String answer가 quizList.txt파일에 저장되어 있는 데이터와 일치할 시 QuizDAO.writeCorrect 메소드를 통해 correctList.txt파일에 회원의 정보가 저장됩니다. 
	 * @return "";
	 */
	public static String participate() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		System.out.println();
		
		while (loop) {
			System.out.print("퀴즈에 도전하시겠습니까?(Y/N) : ");
			String quizPart = scan.nextLine();
			System.out.println();

			if (quizPart.toUpperCase().equals("Y")) {

				UI.showEvent();
				System.out.print("정답의 번호를 입력해주세요 : ");
				String answer = scan.nextLine();
				System.out.println();

				if (answer.equals(QuizDAO.getQuizList().get(0).getAnswer())) {
					QuizDAO.writeCorrect();
					System.out.println("***정답입니다! 매월 초 추첨을 통해 경품을 증정합니다.***");
					System.out.println();
					loop = false;
					break;

				} else {
					System.out.print("틀렸습니다! 다시 도전하시겠습니까? (Y/N) : ");
					String rePart = scan.nextLine();
					System.out.println();

					if (rePart.equals("y") || rePart.equals("Y")) {

					} else if (rePart.equals("n") || rePart.equals("N")) {
						System.out.println("아쉽지만 다음기회에 도전해주세요.");
						loop = false;
						break;
					}

				} // 정답/오답 if문

			} else if (quizPart.equals("n") || quizPart.equals("N")) {
				System.out.println("퀴즈에 도전하지 않으셨습니다.");
				loop = false;
				break;
			} else {
				System.out.println("Y 혹은 N을 입력해주십시오.");
				System.out.println();
				System.out.println();
				
			} // 퀴즈 참여/미참여 if문

		} // while

		return "";
	}

	public static void writeCorrect() {
		
		
	}
	
}
