package com.twlibrary.save;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.twlibrary.dao.BannabDAO;
import com.twlibrary.vo.BannabVO;

/**
 * 프로그램 사용 중에 조작된 BannabDAO를 다시 데이터파일로 저장하는 클래스입니다.
 * BannabDAO의 ArrayList에 저장된 BannabVO들을 텍스트형태로 파일에 저장합니다.
 * 프로그램 종료시에 saveBannab()메소드를 사용해 프로그램 사용 중 조작된 최종 데이터를 파일에 저장합니다.
 * 
 */
public class BannabSave {
	
	private static final String PATH = ".\\dat\\bannab.txt";
	
	public static void saveBannab() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (BannabVO b : BannabDAO.getList()) {
				writer.write(String.format("%s■%s■%s■%s\r\n", b.getNum(), b.getTitle(), b.getAuth(),
						b.getPub()));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
