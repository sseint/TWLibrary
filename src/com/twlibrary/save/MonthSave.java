package com.twlibrary.save;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.twlibrary.dao.MonthDAO;

/**
 * <p>월(Month)을 저장하는 클래스</p>
 */
public class MonthSave {
	private static final String PATH = ".\\dat\\month.txt";
	
	/**
	 * <p>ReadKingService에서 호출당하는 메서드</p>
	 * <p>설명</p>
	 * 만약 달이 넘어가면 정보가 바뀐다.
	 * readKing.txt파일에 3 4 5 6 7 8 9 10 11 12 1 2순서대로 들어가있는데,
	 * 만약 오늘 4월1일이된다면, ReadKingService의 readKingClear()메서드가 연산을 끝내고 해당 메서드를 호출하여 정보를 4 5 6 7 8 9 10 11 12 1 2 3으로 바꾸어 저장하게된다.
	 */
	public static void saveMonth() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (int i : MonthDAO.getList()) {
				writer.write(String.format("%d\r\n", i));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
