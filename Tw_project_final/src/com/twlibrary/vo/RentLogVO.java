package com.twlibrary.vo;

import java.util.Calendar;

/**
 * 
 * 대출기록을 저장하는 클래스입니다.
 * 대출을 한 회원의 MemberVO에서 아이디, 이름, 전화번호를 가져오고 대출한 도서의 BookVO에서 책 고유번호와 제목을 가져옵니다.
 * 빌린 날에 Calendar.getInstance()를 이용해 빌린 날짜를, 거기에 7일을 더한 날을 반납해야 할 날짜로 기록합니다.
 */
public class RentLogVO {
	
	private String id; // 빌린 회원 아이디 (null이면 비회원)
	private String name; // 빌린 회원 이름
	private String phoneNum; // 빌린 회원 전화번호
	private String bookNum; // 빌린 책 고유번호
	private String bookTitle; // 빌린 책 제목
	
	private Calendar rentDay; // 빌린 날짜
	private Calendar bannabDay; // 반납해야할 날짜
	
	
	public RentLogVO() {
		
	}
	
	public RentLogVO(String id, String name, String phoneNum, String bookNum, String bookTitle, Calendar rentDay,
			Calendar bannabDay) {
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.bookNum = bookNum;
		this.bookTitle = bookTitle;
		this.rentDay = rentDay;
		this.bannabDay = bannabDay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhone(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Calendar getRentDay() {
		return rentDay;
	}

	public void setRentDay(Calendar rentDay) {
		this.rentDay = rentDay;
	}

	public Calendar getBannabDay() {
		return bannabDay;
	}

	public void setBannabDay(Calendar bannabDay) {
		this.bannabDay = bannabDay;
	}

	@Override
	public String toString() {
		return "RentLogVO [id=" + id + ", name=" + name + ", phoneNum=" + phoneNum + ", bookNum=" + bookNum + ", bookTitle="
				+ bookTitle + ", rentDay=" + rentDay + ", bannabDay=" + bannabDay + "]";
	}

}
