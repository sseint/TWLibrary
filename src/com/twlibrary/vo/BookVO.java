package com.twlibrary.vo;

/**
 * 각 도서의 객체 정보를 저장하는 클래스입니다.
 * 각 책의 고유성은 고유번호인 멤버변수 num 으로 선언한 값이 갖습니다. 
 * num은 다른 서비스에서 사용 될 때 이 객체의 고유성을 확인하는 가장 중요한 변수입니다.
 * 고유번호(num) 외에 제목(title), 저자(auth), 출판사(pub), 가격(price), 장르(genre), 책 수량(count) 등의 멤버변수가 존재합니다.
 * 제목은 사용자가 쉽게 검색하거나 책을 알아볼 수 있는 기능을 하며 도서 검색 서비스에서 검색메뉴 중 하나로 제공됩니다.
 * 저자와 출판사는 같은 제목의 책이더라도 다른 책일 수 있는 상황을 해결하며 도서 검색 서비스에서 검색메뉴 중 하나로 제공됩니다.
 * 가격은 연체료 부과시 연체일당 책 가격의 1/100 원을 부과할 수 있습니다.
 * 장르는 고유번호 정렬시에 인덱스 역할(컴퓨터 : 1 / 예술 : 2 / 과학 : 3 / 인문 : 4 / 수험서 : 5)로서 도움을 주며 도서 검색 서비스에서 검색메뉴 중 하나로 제공됩니다.
 * 책 수량은 책의 재고상태를 나타내는 역할로서 도서 대출시에 재고가 0일 경우 대출을 할 수 없습니다.
 * 관리자가 반납함에서 검수 후 재고를 리스탁 할 때에 다시 수량이 채워집니다.
 */
public class BookVO {

	private String num; // 고유번호
	private String title; // 제목
	private String auth; // 저자
	private String pub; // 출판사
	private String price; // 가격
	private String genre; // 장르 > 컴퓨터 : 1 / 예술 : 2 / 과학 : 3 / 인문 : 4 / 수험서 : 5
	private int count; // 책 수량

	public BookVO() {

	}

	public BookVO(String num, String title, String auth, String pub, String price, String genre, int count) {
		this.num = num;
		this.title = title;
		this.auth = auth;
		this.pub = pub;
		this.price = price;
		this.genre = genre;
		this.count = count;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BookVO [num=" + num + ", title=" + title + ", auth=" + auth + ", pub=" + pub + ", price=" + price
				+ ", genre=" + genre + ", count=" + count + "]";
	}

}
