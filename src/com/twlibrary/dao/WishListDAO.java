package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.twlibrary.vo.BookVO;

/**
 * 
 * <p>wishList.txt파일의 정보를 읽어오는 클래스</p>
 * <p>WishListVO라는 클래스를 따로 작성하지 않고, BookVO클래스를 사용</p>
 *
 */
public class WishListDAO {
	private final static String PATH = ".\\dat\\wishList.txt";
	private static ArrayList<BookVO> list = new ArrayList<BookVO>();

	public static ArrayList<BookVO> getList() {
		return list;
	}

	/**
	 * <p>wishList.txt파일을 읽는 메서드</p>
	 * <p>설명</p>
	 * 데이터: 책제목, 저자, 출판사  
	 */
	public static void readWishList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;
			while ((str = reader.readLine()) != null) {
				String[] str2 = str.split("■");
				BookVO b = new BookVO();
				b.setTitle(str2[0]);
				b.setAuth(str2[1]);
				b.setPub(str2[2]);
				list.add(b);
			}
			reader.close();
		} catch (Exception e) {
			System.out.printf("");
		}

	}

}
