package com.twlibrary.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.twlibrary.dao.MemberDAO;
import com.twlibrary.dao.MonthDAO;
import com.twlibrary.save.MonthSave;
import com.twlibrary.vo.MemberVO;

/**
 * <p>독서왕 정보를 가져오는 클래스이다.</p>
 *
 */
public class ReadKingService {
	private static ArrayList<MemberVO> list = new ArrayList<MemberVO>(); // 독서킹 레스기릿

	//독서왕 5등까지 가져오기
	/**
	 * <p>누적독서량 1 ~ 5등까지 출력해주는 메서드</p>
	 * 
	 * <p>설명</p>
	 * 1 ~ 5등까지 출력해야하는 제약조건에 따라, 5등안에 10명이 들던 30명이들던 5등까지는 무조껀 출력해주는 메서드이다.
	 * 직접만든 sort(ArrayList<MemberVO> list)를 이용하여, 누적권수가 높은 순서대로 내림차순으로 정렬하여 list를 셋팅해주고.
	 * ranking()함수를 만들어 정렬된 값에따라 등수를 부여하는 메서드이다.
	 * 
	 */
	public static void getReadKing() {
		//getReadKing을 호출할때마다 list의 정보는 계속 읽어와서 add를 하기때문에, clear가 필요.
		list.clear();
		for (int i = 0; i < MemberDAO.getList().size(); i++) {
			list.add(MemberDAO.getList().get(i));
		}

		sort(list);
		System.out.println("┌───────────[이달의 독서 KING]─────────┐");
		System.out.println("│                                                          │");
		ranking();
		System.out.println("│                                                          │");
		System.out.println("└─────────────────────────────┘");

	}

	/**
	 * <p>회원들의 누적권수를 초기화하는 메서드</p>
	 * 월초마다 MemberVO의 list들의 누적권수를 for문을 통하여 초기화 시켜주는 메서드이다.
	 */
	//MemberVO list누적권수 초기화  
	// dat > readKing.txt파일생성 > (DAO)MonthDAO.class생성 > (Save)MonthSave.class생성 
	public static void readKingClear() {
		Calendar now = Calendar.getInstance();
		
		//readKing.txt파일에 지금 3 , 4 , 5, 6으로 셋팅 되어있어서  > 실제 동작하게 하려면 2 , 3, 4, 5로 셋팅해야함.
		//만약 오늘(이번달?)이 readKing.txt파일의 첫줄 숫자(달)보다 크면 실행함
		if(now.get(Calendar.MONTH) + 1 > MonthDAO.getList().get(0)) {
			for (int i = 0; i < MemberDAO.getList().size(); i++) {
				MemberDAO.getList().get(i).setCount(0); //list의 누적권수 초기화
			}
			//readKing.list가 월(달)이 들어가있습니다. 
			//if문을 탔다는거는 지금 달이 바뀌었다는 뜻이기에, 맨위에 숫자를 삭제
			MonthDAO.getList().remove(0);
			//remove동시에 바로 밑에있는달이 올라와서 (만약에 3월이 삭제되었으면 첫번째줄에 4월이 남아있게되어) (4 - 1)해서 3월이 맨 마지막에으로 들어가는거구요.
			//이제 누적권수가 싹다 0권이면 getReadKing()메서드를 실행해도 오류 안날거에요 아마,,? 좀 바꿔놨음
			MonthDAO.getList().add(MonthDAO.getList().get(0) - 1);
			//save 여기로 옮겼음
			MonthSave.saveMonth();
		}
//		//테스팅용도 txt가 잘 찍히는지
//		for(int i : MonthDAO.getList()) {
//			System.out.println(i);
//		}
		//readKing save위치 바꾸쟈~ 
	}
	
	//랭킹부여
	private static void ranking() {
		int rankNum = 1; //랭킹은 1등부터 시작.
		int rankNum2 = 1;

		for (int i = 0; i < rank(); i++) {
			String id = list.get(i).getId().substring(0, 4);
			String name = Character.toString(list.get(i).getName().charAt(1));

			System.out.printf("│  %d등 아이디: %-13s 이름: %-5s 누적권수: %3d권 │\n", rankNum, list.get(i).getId().replace(id, "****"),
					list.get(i).getName().replace(name, "*"), list.get(i).getCount());

			if (list.get(i).getCount() == list.get(i + 1).getCount()) {
				rankNum2++;
			} else if (list.get(i).getCount() > list.get(i + 1).getCount()) {
				rankNum2++;
				rankNum = rankNum2;
			}
		}
		if (rank() == 0) {
			System.out.println("│\t\t 독서KING이 없습니다.   \t\t    │");
		}

	}

	//몇명이 5등안에 드는지
	private static int rank() {
		int index = 1; //명명은 index이지만, 실제로 몇명의 인원이 5등안에 속하는지 인원 수를 세는 변수다.
		for (int i = 0; i < list.size() - 1; i++) {
			//공동등수일경우 5등이 아니라면 출력해줘야함.
			if (list.get(i).getCount() == list.get(i + 1).getCount()) {
				if (list.get(i).getCount() == 0) {
					index--;  
					break;
				}
				index++;
				continue;
				//공동 등수가 아니지만, 5등까지 인원이 차있지 않다면 넣어줌.
			} else if (list.get(i).getCount() > list.get(i + 1).getCount()) {
				if (index >= 5) {
					break;
				}
				index++;
			}

		}

		//1 ~ 5등 이외의 정보는 삭제 
		for (int i = index; i < list.size(); i++) {
			list.remove(index);
		}
		return index;
	}

	//누적권수 기준 리스트 정렬
	private static ArrayList<MemberVO> sort(ArrayList<MemberVO> list) {

		for (int i = 0; i < list.size() - 1; i++) {
			int value = list.get(i).getCount();
			if (value < list.get(i + 1).getCount()) {
				MemberVO b = list.get(i);
				list.set(i, list.get(i + 1));
				list.set(i + 1, b);
			}
		}
		return list;
	}
}
