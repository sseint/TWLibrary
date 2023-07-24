package com.twlibrary.vo;

/**
 * 회원VO 클래스
 */
public class MemberVO {
	private String id;
	private String name;
	private String birth;
	private String pw;
	private String phoneNum;
	private String event;
	private int count; // 이달의 누적권수
	public static MemberVO myLogin;

	/**
	 * 기본생성자
	 */
	public MemberVO() {

	}

	/**
	 * 모든 변수를 다 넣은 생성자
	 * @param id
	 * @param name
	 * @param birth
	 * @param pw
	 * @param phoneNum
	 * @param event
	 * @param count
	 */
	public MemberVO(String id, String name, String birth, String pw, String phoneNum, String event, int count) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.pw = pw;
		this.phoneNum = phoneNum;
		this.event = event;
		this.count = count;
	}
	
	/**
	 * <p>누적권수 getter메서드</p>
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <p>누적권수 setter메서드</p>
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * <p>아이디 getter메서드</p>
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>아이디 setter메서드</p>
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>이름 getter메서드</p>
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>이름 setter메서드</p>
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>생년월일 getter메서드</p>
	 * @return birth
	 */
	public String getBirth() {
		return birth;
	}
	
	/**
	 * <p>생년월일 setter메서드</p>
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * <p>비밀번호 getter메서드</p>
	 * @return pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * <p>비밀번호 setter메서드</p>
	 * @param pw
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}

	/**
	 * <p>핸드폰 번호 getter메서드</p>
	 * @return phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * <p>핸드폰 번호 setter메서드</p>
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * <p>이벤트 getter메서드</p>
	 * @return event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * <p>이벤트 setter메서드</p>
	 * @param event
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * <p>덤프</p>
	 */
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", birth=" + birth + ", pw=" + pw + ", phoneNum=" + phoneNum
				+ ", event=" + event + ", count=" + count + "]";
	}

}
