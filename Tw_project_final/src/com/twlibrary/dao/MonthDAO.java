package com.twlibrary.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * <p>month.txt의 정보를 읽어오는 클래스</p>
 *
 */
public class MonthDAO {
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static final String PATH = ".\\dat\\month.txt";

	/**
	 * <p>Month list의 정보를 리턴하는 get메서드</p>
	 * @return
	 */
	public static ArrayList<Integer> getList() {
		return list;
	}
	
	/**
	 * <p>month.txt의 정보를읽어오는 메서드</p> 
	 */
	public static void readMonth() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String str = null;

			while ((str = reader.readLine()) != null) {
				list.add(Integer.parseInt(str));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
