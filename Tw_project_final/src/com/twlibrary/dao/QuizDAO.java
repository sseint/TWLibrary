package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.twlibrary.vo.MemberVO;
import com.twlibrary.vo.QuizVO;

/**
 * 관리자가 [이벤트 관리 메뉴] 사용 시 사용하는 데이터를 입출력 하는 클래스입니다.
 * ArrayList인 quizList, winnerList를 통해 QuizVO 내에 데이터를 읽고 쓸 수 있습니다.
 * 퀴즈를 읽어오는 readQuiz(), 정답자를 저장하는 writeCorrect(), 정답자를 읽어오는 readCorrect(), 난수 생성 후 당첨자를 저장하는 writeWinner(), 당첨자를 읽어오는 readWinner() 메소드가 있습니다.
 */
public class QuizDAO {
	private static ArrayList<QuizVO> quizList = new ArrayList<>();
	private static ArrayList<QuizVO> winnerList = new ArrayList<QuizVO>();
	private static ArrayList<String> correctLine = new ArrayList<String>();
	private static final String QUIZPATH = ".\\dat\\quizList.txt";
	private static final String CORRECTPATH = ".\\dat\\correctList.txt";
	private static final String WINNERPATH = ".\\dat\\winnerList.txt";
	
	
	

	public static ArrayList<QuizVO> getQuizList() {
		return quizList;
	}
	
	public static ArrayList<QuizVO> getWinnerList() {
		return winnerList;
	}
	
	public static ArrayList<String> getCorrectLine(){
		return correctLine;
	}
	

	/**
	 *  관리자가 퀴즈 등록 후 저장한 txt파일을 읽어와 List에 [문제][답]을 저장하는 메소드 입니다.
	 */
	public static void readQuiz() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(QUIZPATH));// quizList.txt 파일을 읽어옴

			String line = null;

			while ((line = reader.readLine()) != null) { // 읽어온 데이터들을 line에 저장
				String[] temp = line.split("■"); // ■로 구분해서 temp배열에 저장
				QuizVO quiz = new QuizVO(temp[0], temp[1]); // QuizVO 클래스에 temp배열을 저장 후

				quizList.add(quiz);
			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
	/**
	 * 회원이 로그인 후 퀴즈의 정답을 맞출 시 해당 회원의 [이름][ID][전화번호]를 correctList.txt파일에 저장하는 메소드 입니다.
	 */
	public static void writeCorrect() {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(CORRECTPATH, true));
			
			writer.write(String.format("%s■%s■%s"
										, MemberVO.myLogin.getName()
										, MemberVO.myLogin.getId()
										, MemberVO.myLogin.getPhoneNum()));
			writer.newLine(); 	//이어쓸 때 줄바꿈
			
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * correctList.txt 파일로부터 데이터를 읽어와 ArrayList인 correctLine에 저장하는 메소드 입니다.
	 */
	public static void readCorrect() {
		
		//correctList.txt에서 정답자들을 배열에 읽어오기
		try {
			BufferedReader reader = new BufferedReader(new FileReader(CORRECTPATH));
			
			String line = null;
				while((line = reader.readLine()) != null) {
					
					correctLine.add(line);
					
				}
				reader.close();
			

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * correctList.txt 파일에 저장된 목록을 HashSet 배열을 사용하여 중복없이 5개 무작위 선정 후 winnerList.txt 파일에 저장하는 메소드 입니다.
	 */
	public static void writeWinner() {

		HashSet<String> winnerSet = new HashSet<String>();
		while (winnerSet.size() < 5) {

			int index = (int) (Math.random() * correctLine.size());
			String winner = correctLine.get(index);
			winnerSet.add(winner);

		}
		ArrayList<String> winList = new ArrayList<String>(winnerSet);

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(WINNERPATH));

			for (String line : winList) {
				writer.write(line);
				writer.newLine();
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 관리자 조회용이며 winnerList.txt파일을 읽어와서 ArrayList인 winnerList배열에 저장하는 메소드 입니다.
	 */
	public static void readWinner() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(WINNERPATH));
			String line = null;
			
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				QuizVO winner = new QuizVO(temp[0], temp[1], temp[2]);
				winnerList.add(winner);
			}
			reader.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	

}
