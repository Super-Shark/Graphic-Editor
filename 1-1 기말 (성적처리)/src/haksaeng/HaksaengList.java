package haksaeng;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTextArea;

import exeption.GwamokNameNotFoundExeption;
import exeption.HaksaengnamenotExistExeption;
import gangjwa.GangjwaList;

public class HaksaengList {
	
	private Vector<Haksaeng> haksaengVector;
	GangjwaList gangjwalist;

	public HaksaengList() {
		this.haksaengVector = new Vector<Haksaeng>();
	}
	public void associate(GangjwaList gangjwalist) {
		this.gangjwalist = gangjwalist;
	}

	public void readFromFile() throws FileNotFoundException {
		File file = new File("data/Haksaeng.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			Haksaeng haksaeng = new Haksaeng();
			haksaeng.readFromfile(scanner);
			this.haksaengVector.add(haksaeng);
		}
		scanner.close();
	}

	public String getHaksaengName(int haksaengID) throws HaksaengnamenotExistExeption {
		for (Haksaeng haksaeng : this.haksaengVector) {
			if (haksaeng.getId() == haksaengID) {
				return haksaeng.getName();
			}
		}
		throw new HaksaengnamenotExistExeption(haksaengID);
	}

	public void printHaksaengInfo(JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption  {//학생 인포메이션꺼
		for (Haksaeng haksaeng : this.haksaengVector) {
				haksaeng.printinfo(gangjwalist, haksaeng_textarea);
				haksaeng_textarea.append("\r\n");
		}
	}

	public void SearchHaksaeng(JTextArea area, int cnt) {//서치 학생 꺼. 이후것들은 위의 "프린트 학생인포"랑 같길래, 합체시켰습니다.
		for (Haksaeng haksaeng : this.haksaengVector) {//cnt는 입력한 번호.
			if (cnt == haksaeng.getId()) {
				try {
					haksaeng.printinfo(gangjwalist, area);
				} catch (GwamokNameNotFoundExeption e) {
					e.printStackTrace(area);
				}
			}
		}
	}

}
