package main;

import java.io.FileNotFoundException; 

import GUI.GUImain;
import gangjwa.GangjwaList;
import gwamok.GwamokList;
import haksaeng.HaksaengList;
import kyosu.KyosuList;

public class Main  {
	
	public static void main(String[] args) {
		try {
			GwamokList gwamokList = new GwamokList();
			gwamokList.readFromFile();

			HaksaengList haksaengList = new HaksaengList();
			haksaengList.readFromFile();

			KyosuList kyosuList = new KyosuList();
			kyosuList.readFromFile();

			GangjwaList gangjwalist = new GangjwaList();
			gangjwalist.readFromFile();
			
			GUImain gui = new GUImain( gwamokList,  haksaengList,   gangjwalist);
			gui.make();
			
			gangjwalist.associate(gwamokList, haksaengList, kyosuList);
			haksaengList.associate(gangjwalist);

		} catch (FileNotFoundException e) {
			System.out.println("파일이 없어요.");
			e.printStackTrace();
		}
	}
}
//뭔가 점수 밭을듯한것들 / 1.GUI  / 2.서치학생(심지어 이쁘다) / 3.교수세트(교수리스트, 교수, 예외) /4.직접만든 소팅? 
//5.주어진거 전부 가능(기본출력들) /  6.학생에서 연도,학기 정렬. / 7. 강좌 연도,학기순 정렬 / 8.교수와 학생의 예외가 좀더 구체적인것?

//txt파일에서, gaumok => Gwamok 하고, 교수ID 추가, 교수txt파일 만든것 말고는 같아요. data2는 교수이름 없음 오류 뜨게 해놨습니다.
//왜 년도랑 학기가 전부 같죠? -> data4는 년도, 학기를 다르게 했습니다.

//다른 txt파일로 하게될때, 코드바꾸지말고, 실행 하려는 파일이름을 "data"로 바꾸는게 빠르다.  Alt + Shift + R (이름 바꾸기 단축키)