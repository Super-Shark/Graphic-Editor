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
			System.out.println("������ �����.");
			e.printStackTrace();
		}
	}
}
//���� ���� �������Ѱ͵� / 1.GUI  / 2.��ġ�л�(������ �̻ڴ�) / 3.������Ʈ(��������Ʈ, ����, ����) /4.�������� ����? 
//5.�־����� ���� ����(�⺻��µ�) /  6.�л����� ����,�б� ����. / 7. ���� ����,�б�� ���� / 8.������ �л��� ���ܰ� ���� ��ü���ΰ�?

//txt���Ͽ���, gaumok => Gwamok �ϰ�, ����ID �߰�, ����txt���� ����� ����� ���ƿ�. data2�� �����̸� ���� ���� �߰� �س����ϴ�.
//�� �⵵�� �бⰡ ���� ����? -> data4�� �⵵, �б⸦ �ٸ��� �߽��ϴ�.

//�ٸ� txt���Ϸ� �ϰԵɶ�, �ڵ�ٲ�������, ���� �Ϸ��� �����̸��� "data"�� �ٲٴ°� ������.  Alt + Shift + R (�̸� �ٲٱ� ����Ű)