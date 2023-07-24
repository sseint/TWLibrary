package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.QuizDAO;
import com.twlibrary.ui.UI;

/**
 * [메인 메뉴] > [관리자 로그인] > [이벤트 관리 메뉴] > [당첨자 확인]
 * 관리자가 퀴즈의 [당첨자] 추첨 및 회원정보를 조회하는 클래스 입니다.
 * 당첨자 확인을 선택 시 printWinner() 메소드를 통해 QuizDAO winnerList에 저장되어 있는 당첨자의 목록을 조회하면서 QuizDAO writeWinner() 메소드를 통해 새로운 당첨자 추첨을 실행합니다. 
 */
public class QuizWinnerService {
	/**
	 * 당첨자의 [회원 정보]를 읽고, 동시에 당첨자 추첨을 하는 메소드
	 * [회원 정보]를 읽어오는 printWinner() 메소드와 난수를 통해 당첨자 추첨을 하는 QuizDAO.writeWinner() 메소드를 동시에 호출한다.
	 */
	public static void choiceWinner() {
		Scanner scan = new Scanner(System.in);
		printWinner();
		QuizDAO.writeWinner();
		
	}
	/**
	 * QuizDAO 클래스에서 ArryaList winnerList 에 저장한 회원 정보를 전부 출력하는 메소드
	 */
	public static void printWinner() {
		
		Scanner scan = new Scanner(System.in);
		
		UI.showWinner();
		
		if (QuizDAO.getWinnerList().size() > 0) {
			for (int i = 0; i < QuizDAO.getWinnerList().size(); i++) {
				System.out.printf("%s \t%s \t%10s\r\n"
						, QuizDAO.getWinnerList().get(i).getName()
						, QuizDAO.getWinnerList().get(i).getId()
						, QuizDAO.getWinnerList().get(i).getPhoneNum());
				
			}
		} else {
			System.out.println("당첨자가 없습니다.");
		}
		
		System.out.println();
		System.out.println();
		System.out.println("메인으로 돌아가시려면 [엔터]를 입력해주세요.");
		scan.nextLine();
		
	}

}
