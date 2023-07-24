package com.twlibrary.save;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.util.Calendar;

import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.vo.RentLogVO;

/**
 * 프로그램 사용 중에 조작된 RentLogDAO를 다시 데이터파일로 저장하는 클래스입니다.
 * RentLogDAO의 ArrayList에 저장된 RentLogVO들을 텍스트형태로 파일에 저장합니다.
 * 프로그램 종료시에 saveRentLog()메소드를 사용해 프로그램 사용 중 조작된 최종 데이터를 파일에 저장합니다.
 *
 */
public class RentLogSave {
	
	private static final String PATH = ".\\dat\\rentLog.txt";

	public static void saveRendLog() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (RentLogVO r : RentLogDAO.getList()) {
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s\r\n", r.getId(), r.getName(), r.getPhoneNum(),
						r.getBookNum(), r.getBookTitle(), printDay(r.getRentDay()), printDay(r.getBannabDay())));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 캘린더값을 txt데이터로저장 시 또는 print로 출력시 %tF로 변환해주는 메소드
	 * @param Calnedar day
	 * @return String 0000-00-00
	 */
	public static String printDay(Calendar day) {
		String output = String.format("%tF", day);
		return output;
	}
	
}
