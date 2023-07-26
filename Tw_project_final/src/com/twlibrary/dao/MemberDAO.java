package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.twlibrary.vo.MemberVO;

/**
 * <p>member.txt파일을 읽어오는 클래스</p>
 *
 */
public class MemberDAO {
	private static ArrayList<MemberVO> list = new ArrayList<MemberVO>();
	private static final String PATH = ".\\dat\\member.txt";

	/**
	 * <p>memberVO list를 반환하는 getter메서드</p>
	 * @return
	 */
	public static ArrayList<MemberVO> getList() {
		return list;
	}
	
	/**
	 * <p>member.txt파일을 읽어와 MemberVO의 list에 저장하는 메서드</p>
	 */
	public static void readMember() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;

			while ((str = reader.readLine()) != null) {
				String[] str2 = str.split("■");
				MemberVO b = new MemberVO(str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], Integer.parseInt(str2[6]));
				list.add(b);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
