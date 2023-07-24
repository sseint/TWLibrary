package com.twlibrary.save;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.twlibrary.dao.MemberDAO;
import com.twlibrary.vo.MemberVO;

/**
 * <p>member.txt파일을 저장하는 클래스</p>
 *
 */
public class MemberSave {
	private static final String PATH = ".\\dat\\member.txt";

	/**
	 * <p>memberDAO의 list를 가져와 list의 정보를 txt파일에 작성(저장)하는메서드</p>
	 */
	public static void saveMember() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
			for (MemberVO m : MemberDAO.getList()) {
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%d\r\n", m.getId(), m.getName(), m.getBirth(), m.getPw(),
						m.getPhoneNum(), m.getEvent(), m.getCount()));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
