package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.twlibrary.vo.BannabVO;

/**
 * 데이터에서 내용을 읽어와 BannabVO로 변환해 리스트에 저장하는 클래스 입니다.
 * 프로그램 시작 시 readeBannab()메소드를 사용해 정보를 읽어오고 데이터는 메모리에 객체를 담은 리스트로 저장되도록 합니다.
 * 프로그램 사용 중 선언된 ArrayList의 getList() 메소드를 이용해 내부의 VO 데이터들을 읽고 쓰기가 가능합니다.
 *
 */
public class BannabDAO {
	
	private static ArrayList<BannabVO> list = new ArrayList<BannabVO>();
	private static final String PATH = ".\\dat\\bannab.txt";
		
	public static ArrayList<BannabVO> getList() {
		return list;
	}
	
	public static void readBannab() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;

			while ((str = reader.readLine()) != null) {
				String[] str2 = str.split("■");
				BannabVO b = new BannabVO(str2[0], str2[1], str2[2], str2[3]);
				list.add(b);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
