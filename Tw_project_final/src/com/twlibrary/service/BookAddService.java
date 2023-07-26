package com.twlibrary.service;

import java.util.Scanner;

import com.twlibrary.dao.BookDAO;
import com.twlibrary.ui.UI;
import com.twlibrary.vo.BookVO;

/**
 * <p>[관리자]-[도서 등록] 화면에서 사용하는 클래스입니다. 
 * Main 클래스에서 addBook()을 호출하여 도서등록을 할 수 있습니다. 
 * 등록할 책의 정보를 입력받은 뒤 getIndex() 메소드에서 중복검사를 하고 
 * 기존에 있는 책이라면 addExistBook() 메소드 실행, 중복된 책이 없으면 getAddIndex()로 책 목록에 추가할 위치를 구한 뒤 
 * addNewBook()로 책의 정보를 BookVO에 새로 등록합니다.</p>
 * <p>(예) BookAddService.addBook(); </p> 
 */
public class BookAddService {
	
	public static void addBook() {
		BookVO bTemp = new BookVO();	//등록할 책의 정보를 임시로 저장할 BookVo
		Scanner scan = new Scanner(System.in);
		System.out.println("등록할 책의 정보를 입력하십시오.");
		System.out.print("책 제목: ");
		String title = scan.nextLine();
		System.out.print("저자: ");
		String author= scan.nextLine();
		System.out.print("출판사: ");
		String pub = scan.nextLine();
		System.out.print("장르: ");
		String genre = scan.nextLine();
		System.out.print("가격: ");
		String price = scan.nextLine();
		
		bTemp.setTitle(title);
		bTemp.setAuth(author);
		bTemp.setPub(pub);
		bTemp.setGenre(genre);
		bTemp.setPrice(price);
		
		getIndex(bTemp);
		
		if(getIndex(bTemp) == -1) { //해당하는 책이 없다면 => 신규도서 등록
			addNewBook(bTemp);
		
		} else { 					//해당하는 책이 이미 있다면 => 기존 도서 등록
			addExistBook(bTemp);
		}
		
	}//getBookInfo

	
	/**
	 * getIndex() 메소드 실행 후 리턴값이 -1이면 기존에 있는 도서를 추가하는 메소드입니다. 
	 * 
	 * @param 새로 등록할 책의 정보를 저장한 bTemp
	 */
	private static void addExistBook(BookVO bTemp) {
		Scanner scan = new Scanner(System.in);
		System.out.print("동일한 책이 존재합니다. 수량을 입력하십시오. (ex: 3권일 경우 -> 3): ");
		int count = scan.nextInt();
		scan.skip("\r\n");

		BookVO bExist = BookDAO.getList().get(getIndex(bTemp));

		bExist.setCount(bExist.getCount() + count);

		System.out.println("도서 등록을 완료하였습니다.");
		UI.pause();
	}//addExistBook

	
	/**신규 도서를 책 목록(books.txt)에 추가하는 메소드입니다.
	 * 
	 * @param 새로 등록할 책의 정보를 저장한 bTemp
	 */
	private static void addNewBook(BookVO bTemp) {
		Scanner scan = new Scanner(System.in);
		System.out.print("수량을 입력하십시오 (ex: 3권일 경우 -> 3): ");
		int count = scan.nextInt();
		scan.skip("\r\n");
		bTemp.setCount(count);

		//새로 등록할 책의 정보를 bNew에 저장
		BookVO bNew = new BookVO();
		
		//bTemp에 임시 저장하였던 정보를 bNew에 대입
		BookDAO.getList().add(getAddIndex(bTemp), bTemp);
		
		bNew.setNum(bTemp.getNum());
		bNew.setTitle(bTemp.getTitle());
		bNew.setAuth(bTemp.getAuth());
		bNew.setPub(bTemp.getPrice());
		bNew.setGenre(bTemp.getGenre());
		bNew.setPrice(bTemp.getPrice());
		bNew.setCount(bTemp.getCount());
		

		System.out.println("신규 도서 등록이 완료되었습니다.");

		UI.pause();
		
	}//addNewBook

	/**<p>새로 등록할 책이 책 목록(books.txt)에 존재하는지 검사하는 메소드입니다.
	 * 매개변수로는 임시저장한 bTemp를 받고 int를 반환하는 메소드입니다. <br>
	 * 책 제목, 저자, 출판사가 동일하면 같은 책으로 간주합니다.
	 * 
	 * </p>
	 * 
	 * @param 새로 등록할 책의 정보를 저장한 bTemp
	 * @return 책이 존재하지 않으면 -1을, 존재하면 그 위치의 인덱스를 반환합니다.
	 */
	public static int getIndex(BookVO bTemp) {
		int index = -1;
		
		for(int i=0; i<BookDAO.getList().size(); i++) {
			BookVO b = BookDAO.getList().get(i);
			if(bTemp.getTitle().equals(b.getTitle()) &&
					bTemp.getAuth().equals(b.getAuth())&&
					bTemp.getPub().equals(b.getPub())) {
				
				index = BookDAO.getList().indexOf(b);
			}
		}
	
			
		return index;
		
	} //getIndex;
	
	
	/**<p>신규 도서를 책 목록(books.txt)에 저장할 위치를 구하는 메소드입니다. 
	 * 매개변수로 BookVO를 받고 인덱스값을 int로 반환합니다.</p> 
	 * 
	 *<p>신규 도서를 책 목록(books.txt)에 저장할 인덱스를 구하는 메소드입니다. 
	 *매개변수로 BookVO를 받고 인덱스 값을 int로 반환합니다.</p>  
	 *<p>books.txt에 저장된 도서 목록의 장르별 개수를 각각  computer, art, science, inmoon, textbook에 저장합니다. 
	 *새로 등록할 책의 정보를 임시저장한 BookVO bTemp의 장르를 '컴퓨터', '예술', '과학', '인문', '수험서'와 비교합니다. 
	 *장르별 목록의 마지막 인덱스는 (장르별 개수 -1)입니다. </p> 
	 *<p>(예) '컴퓨터' 장르 50개가 books.txt에 행마다 존재 => 마지막 인덱스 값은 49 </p>
	 *장르별 마지막 인덱스에 해당하는 BookVO 객체 b2를 생성합니다. b2의 고유번호에 1을 더하여 새 인덱스를 구합니다. </p>
	 * 
	 * @param 관리자가 입력한 책의 정보를 저장한 bTemp
	 * @return books.txt에 새로 저장할 위치를 반환
	 */
	public static int getAddIndex(BookVO bTemp) {
		
		int computer = 0;
		int art = 0;
		int science = 0;
		int inmoon = 0;
		int textbook = 0;
		
		int addIndex = -1;
		int newNum = 0;
		
		
		
		//책 목록을 한줄씩 읽어서 장르별 개수를 셈
		for(BookVO b : BookDAO.getList()) {
			if(b.getGenre().equals("컴퓨터")) {
				computer++;
			} else if (b.getGenre().equals("예술")) {
				art++;
			} else if (b.getGenre().equals("과학")) {
				science++;
			} else if (b.getGenre().equals("인문")) {
				inmoon++;
			} else if (b.getGenre().equals("수험서")) {
				textbook++;
			}
		}
		
		//새로 등록할 책의 장르에 따라 인덱스 부여
		if (bTemp.getGenre().equals("컴퓨터")) {
			addIndex = computer;
			
			BookVO b2 = BookDAO.getList().get(addIndex-1);
			newNum = Integer.parseInt(b2.getNum())+1;
			
			bTemp.setNum(String.valueOf(newNum));
			
		} else if (bTemp.getGenre().equals("예술")) {
			addIndex = computer + art;
			BookVO b2 = BookDAO.getList().get(addIndex-1);
			newNum = Integer.parseInt(b2.getNum())+1;
			
			bTemp.setNum(String.valueOf(newNum));
			
		} else if (bTemp.getGenre().equals("과학")) {
			addIndex = computer + art + science;
			BookVO b2 = BookDAO.getList().get(addIndex-1);
			newNum = Integer.parseInt(b2.getNum())+1;
			
			bTemp.setNum(String.valueOf(newNum));
			
		} else if (bTemp.getGenre().equals("인문")) {
			addIndex = computer + art + science + inmoon;
			BookVO b2 = BookDAO.getList().get(addIndex-1);
			newNum = Integer.parseInt(b2.getNum())+1;
			
			bTemp.setNum(String.valueOf(newNum));
			
		} else if (bTemp.getGenre().equals("수험서")) {
			addIndex = computer + art + science + inmoon + textbook;
			BookVO b2 = BookDAO.getList().get(addIndex-1);
			newNum = Integer.parseInt(b2.getNum())+1;
			
			bTemp.setNum(String.valueOf(newNum));
		
		}
		
		return addIndex;
		
	}//getAddIndex
	
	
	
}
