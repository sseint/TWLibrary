package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.QuizDAO;
import com.twlibrary.ui.UI;

/**
 * [메인 메뉴] > [이벤트]
 * 관리자가 등록한 퀴즈의 [문제]를 열람할 수 있는 클래스 입니다. 메인메뉴의 [이벤트] 및 회원메뉴의 [퀴즈 맞히기] 선택 시 호출됩니다.
 * 클래스 호출 시 QuizDAO에 저장되어 있는 ArrayList quizList 중 퀴즈의 [문제]만 출력합니다.
 */
public class EventService {

	static Scanner scan = new Scanner(System.in);

	/**
	 * UI.showEvent()를 호출하여 QuizDAO quizList에 저장된 퀴즈의 [문제]만 보여주는 메소드 입니다.
	 */
	public static void eventView() {
		QuizDAO.readQuiz(); 

		UI.showEvent();
		ReadKingService.getReadKing();
		UI.pause();
		System.out.println("메인으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();
		System.out.println();
		System.out.println();
	}

	
	
	

}
