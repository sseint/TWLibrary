package com.twlibrary.service;


/**
 * 페이지 번호 클래스
 */
public class PagingService {

	public static void paging(int n, double lastPage) {
		System.out.printf("\t\t   <%d/%d>\n", n + 1, (int) lastPage);
		System.out.println();
	}
}
