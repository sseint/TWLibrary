package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.twlibrary.vo.BookVO;
/**
 * 데이터에서 내용을 읽어와 BookVO로 변환해 리스트에 저장하는 클래스 입니다.
 * 프로그램 시작 시 readBook()메소드를 사용해 정보를 읽어오고 데이터는 메모리에 객체를 담은 리스트로 저장되도록 합니다.
 * 프로그램 사용 중 선언된 ArrayList의 getList() 메소드를 이용해 내부의 VO 데이터들을 읽고 쓰기가 가능합니다.
 *
 */
public class BookDAO {
	private static ArrayList<BookVO> list = new ArrayList<BookVO>();
	private static final String PATH = ".\\dat\\books.txt";
		
	public static ArrayList<BookVO> getList() {
		return list;
	}

	public static void readBook() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;

			while ((str = reader.readLine()) != null) {
				String[] str2 = str.split("■");
				BookVO b = new BookVO(str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], Integer.parseInt(str2[6]));
				list.add(b);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
