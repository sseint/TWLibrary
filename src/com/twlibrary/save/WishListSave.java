package com.twlibrary.save;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.twlibrary.dao.WishListDAO;
import com.twlibrary.vo.BookVO;

/**
 *<p>희망도서목록을 저장하는 클래스</p>
 */
public class WishListSave {
	private static final String PATH = ".\\dat\\wishList.txt";
	
	/**
	 * <p>희망 도서 목록(list)의 정보를 WishList.txt파일에 저장하는 메서드</p>
	 */
	public static void saveWishList() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (BookVO b : WishListDAO.getList()) {
				writer.write(String.format("%s■%s■%s\r\n", b.getTitle(), b.getAuth(), b.getPub()));
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("WishListSave 오류");
		}
	}
}
