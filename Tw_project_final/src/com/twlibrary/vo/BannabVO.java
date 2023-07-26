package com.twlibrary.vo;

/**
 * 회원이 반납한 도서를 반납함형태로 데이터를 이루는 클래스입니다.
 * 반납된 책의 고유번호, 제목, 저자, 출판사의 값을 지니고 있습니다.
 * 관리자가 반납함을 검수하여 재고를 채울 시 BookVO와 고유번호 데이터를 대조하여 일치하는 BookVO의 수량을 올립니다.
 */
public class BannabVO {
	
	private String num;	   // 고유번호
	private String title; // 제목
	private String auth; // 저자
	private String pub; // 출판사
	
	public BannabVO() {

	}
	
	

	public BannabVO(String num, String title, String auth, String pub) {
		this.num = num;
		this.title = title;
		this.auth = auth;
		this.pub = pub;
	}



	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "BannabVO [num=" + num + ", title=" + title + ", auth=" + auth + ", pub=" + pub + "]";
	}
	
	
}
