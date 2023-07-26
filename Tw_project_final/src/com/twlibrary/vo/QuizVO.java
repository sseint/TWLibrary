package com.twlibrary.vo;

/**
 * 퀴즈 객체를 생성하기 위한 클래스 입니다.
 * 관리자가 등록한 퀴즈 문제와 정답, 퀴즈의 정답을 맞춘 회원의 id, 이름, 전화번호를 저장합니다.
 */
public class QuizVO {
	private String question; // 문제내용
	private String answer;   // 답
	private String id;   	 // 정답자 id
	private String name;   	 // 정답자 이름
	private String phoneNum; // 정답자 전화번호
	
	
	public QuizVO() {
		
	}

	public QuizVO(String question, String answer) {
		
		this.question = question;
		this.answer = answer;
				
	}
	
	public QuizVO(String name, String id, String phoneNum) {
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	

	
	
	
	
	
	
	

}
