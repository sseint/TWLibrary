package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.twlibrary.vo.RentLogVO;
/**
 * 데이터에서 내용을 읽어와 RentLogVO로 변환해 리스트에 저장하는 클래스 입니다.
 * 프로그램 시작 시 readRentLog()메소드를 사용해 정보를 읽어오고 데이터는 메모리에 객체를 담은 리스트로 저장되도록 합니다.
 * 프로그램 사용 중 선언된 ArrayList의 getList() 메소드를 이용해 내부의 VO 데이터들을 읽고 쓰기가 가능합니다.
 * Calendar 형태로 저장되는 데이터는 파일을 읽어오면서 String의 형태에서 Calendar 형태로 변환하여 저장합니다.
 * 
 */
public class RentLogDAO {

	private static ArrayList<RentLogVO> list = new ArrayList<RentLogVO>();
	private static final String PATH = ".\\dat\\rentLog.txt";

	public static ArrayList<RentLogVO> getList() {
		return list;
	}

	public static void readRentLog() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;

			while ((str = reader.readLine()) != null) {
				String[] str2 = str.split("■");
				RentLogVO b = new RentLogVO(str2[0], str2[1], str2[2], str2[3], str2[4], readDay(str2[5]),
						readDay(str2[6]));
				list.add(b);
			}
			reader.close();
		} catch (Exception e) {
			System.out.print("");
		}

	}

	/**
	 * 데이터에서 읽어온 날짜txt(0000-00-00)를 실제 캘린더 객체에 저장하는 메소드
	 * 
	 * @param String day
	 * @return Calendar date
	 */
	public static Calendar readDay(String day) {

		Calendar date = Calendar.getInstance();
		String[] temp = day.split("-");
		date.set(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
		return date;
	}

}
