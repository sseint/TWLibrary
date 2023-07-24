package com.twlibrary.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.dao.RentLogDAO;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.RentLogVO;

/**
 * 연체목록 클래스
 *
 */
public class OverdueService {
	private static ArrayList<RentLogVO> list = new ArrayList<RentLogVO>();
	private static int lastIndex = list.size();
	private static double lastPage = Math.ceil((list.size() / 10.0));

	public static ArrayList<RentLogVO> getList() {
		return list;
	}

	public static int getLastIndex() {
		return lastIndex;
	}

	public static double getLastPage() {
		return lastPage;
	}

	public static void getOverdue(int n) {
		// 연체된 정보들 추출
		Calendar now = Calendar.getInstance();
		list.clear();
		for (int i = 0; i < RentLogDAO.getList().size(); i++) {
			if ((now.getTimeInMillis() - RentLogDAO.getList().get(i).getRentDay().getTimeInMillis()) / 60 / 60 / 24
					/ 1000 > 7) {
				list.add(RentLogDAO.getList().get(i));
			}
		}
		if(list.size()==0) {
			System.out.println("연체 목록이 없습니다.");
		} else {
			
			lastIndex = list.size();
			lastPage = Math.ceil((list.size() / 10.0));
			
			UI.rentLogUI();
			n -= 1;
			//		 마지막 페이지에서 요소의 개수가 10개가 안될 경우를 대비함
			if ((n + 1) == (int) lastPage) {
				for (int i = n * 10; i < lastIndex; i++) {
					int day = (int) ((now.getTimeInMillis() - list.get(i).getRentDay().getTimeInMillis()) / 60 / 60 / 24 / 1000) - 7;
					System.out.printf("%s\t%-15s\t%s\t%s\t\t%,2d일\t%,5d원\n"
							, UI.convert(list.get(i).getBookNum(), 4)
							, UI.convert(list.get(i).getBookTitle(), 30)
							, UI.convert(list.get(i).getName(), 10)
							, UI.convert(list.get(i).getPhoneNum(), 13)
							,day
							,day * overduePrice(list.get(i).getBookTitle()));
				}
			} else {
				for (int i = n * 10; i < n * 10 + 10; i++) {
					int day = (int) ((now.getTimeInMillis() - list.get(i).getRentDay().getTimeInMillis()) / 60 / 60 / 24 / 1000) - 7;
					System.out.printf("%s\t%-15s\t%s\t%s\t\t%,2d일\t%,5d원\n"
							, UI.convert(list.get(i).getBookNum(), 4)
							, UI.convert(list.get(i).getBookTitle(), 30)
							, UI.convert(list.get(i).getName(), 10)
							, UI.convert(list.get(i).getPhoneNum(), 13)
							,day
							,day * overduePrice(list.get(i).getBookTitle()));
				}
			}
			
			PagingService.paging(n, lastPage);
		}
	}

	public static int overduePrice(String title) {
		String price = "";
		for (int i = 0; i < BookDAO.getList().size(); i++) {
			if (BookDAO.getList().get(i).getTitle().equals(title)) {
				price = BookDAO.getList().get(i).getPrice();
			}
		}
		return Integer.parseInt(price) / 100;
	}
}
