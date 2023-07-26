package com.twlibrary.save;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.vo.BookVO;
/**
 * 프로그램 사용 중에 조작된 BookDAO를 다시 데이터파일로 저장하는 클래스입니다.
 * BookDAO의 ArrayList에 저장된 BookVO들을 텍스트형태로 파일에 저장합니다.
 * 프로그램 종료시에 saveBook()메소드를 사용해 프로그램 사용 중 조작된 최종 데이터를 파일에 저장합니다.
 * 
 */
public class BookSave {
	private static final String PATH = ".\\dat\\books.txt";

	public static void saveBook() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (BookVO b : BookDAO.getList()) {
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%d\r\n", b.getNum(), b.getTitle(), b.getAuth(),
						b.getPub(), b.getPrice(), b.getGenre(), b.getCount()));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
