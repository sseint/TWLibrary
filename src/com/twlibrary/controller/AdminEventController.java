package com.twlibrary.controller;

import java.util.Scanner;

import com.twlibrary.service.QuizRegisterService;
import com.twlibrary.service.QuizWinnerService;
import com.twlibrary.service.ReadKingService;
import com.twlibrary.service.EventService;
import com.twlibrary.ui.UI;

/**
 * [메인] > [관리자 로그인] > [이벤트 관리 메뉴]
 * 관리자가 퀴즈 등록 및 당첨자 추첨, 당첨 회원 정보 확인을 할 수 있는 클래스입니다.
 * QuizRegisterService와 QuizWinnerService 클래스를 호출합니다.
 */
public class AdminEventController {
	static Scanner scan = new Scanner(System.in);
	/**
	 * 관리자용 메인화면에서 [이벤트 관리 메뉴]를 선택 시 호출되는 메소드 입니다.
	 * [1. 퀴즈등록, 2. 당첨자 확인, 3. 독서왕 초기화, 0. 메인화면 이동]으로 연결됩니다.
	 */
	public static void adminEventManage() {

		boolean loop = true;

		while (loop) {

			UI.manageEvent();
			System.out.print("원하시는 메뉴의 번호를 입력해주세요:​ ");
			String eventChoice = scan.nextLine();
			System.out.println();
			System.out.println();
			

			if (eventChoice.equals("1")) {
				// 퀴즈 등록
				QuizRegisterService.registerQuiz();

			} else if (eventChoice.equals("2")) {
				// 당첨자 확인
				QuizWinnerService.choiceWinner();

			} else if (eventChoice.equals("3")) {
				ReadKingService.readKingClear();
				System.out.println("독서왕이 초기화 되었습니다.");
			}
			 else if (eventChoice.equals("0")) {
				loop=false;

			}
		}
	}

}
