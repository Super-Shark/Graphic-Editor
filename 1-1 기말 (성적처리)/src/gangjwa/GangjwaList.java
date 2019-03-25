package gangjwa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTextArea;

import exeption.GwamokNameNotFoundExeption;
import gwamok.GwamokList;
import haksaeng.HaksaengList;
import kyosu.KyosuList;

public class GangjwaList {
	GwamokList gwamokList;
	HaksaengList haksaengList;
	KyosuList kyosuList;

	private Vector<Gangjwa> gangjwaVector;

	public GangjwaList() {
		this.gangjwaVector = new Vector<Gangjwa>();
	}

	public void associate(GwamokList gwamokList, HaksaengList haksaengList, KyosuList kyosuList) {
		this.gwamokList = gwamokList;
		this.haksaengList = haksaengList;
		this.kyosuList = kyosuList;
	}

	public void readFromFile() throws FileNotFoundException {
		File file = new File("data/gangjwaList.txt");// ���ϳ������־� //�տ� data�� �����ϰ� 1,2,3���� �����ϰ� �ٲٴ� �����? -> main�� �����.
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String name = scanner.next();//�ּҸ� ����
			Gangjwa gangjwa = new Gangjwa();
			gangjwa.readFromFile(name);

			// ���⼭ �����ϸ� ���µ�� �л������� ���ʿ䰡 �����ϴ�. �⵵, �б������ �˴� ������ �Ǽ� ���⶧����,
			// ����ҋ� ������ �ʿ䰡 �����ϴ�.

			// ���� ����
			// ȣ���� �������� ���´� ���� ID������ ����ϰ� �϶� �Ͻø�, �����ִ� �迭 �����س���, ������ �б�κ�������,
			// ID������ ���Ľ�Ű��, �л��κ��� ������ ���� ��������ϴµ�, �ؿ��ִ� �л��ش� ��ºκп� �����ȿ�,���� �����ŭ ����
			// for�������, �����µ�, �帧 �ȹٲٸ�, ����id�������� ��µǴµ�, ���͸� �ϳ� �������, ���¿��� ������ �б⸦ ���,
			// �Ʒ��� ������ ����ؼ� ���Ľ�Ų��(�Ⱦ��̴ºκ���������, �������ϱ�����) ���͸� ������� ��½�Ű�� �ȴ�.
			//�л��� ���ľ��ص��Ǹ�, ����͸� �ٲٸ� �ȴ�.

			// ������ �б⸦ ������. ����Ʈ�� �ٲٶ��ϸ� ���Ұ� ���Ƽ�. ���� ����� �������.
			if (this.gangjwaVector.size() == 0) {// ��ó�� ���ö��� ����. @@@���, �𼾵� �Ϸ��� <>4�� �ٲ��ָ� �ȴ�. �⺻�� ���.
				this.gangjwaVector.add(gangjwa);//ó������ �׳� �־� �ش�.
			} else {
				for (int i = 0; i < this.gangjwaVector.size(); i++) {// ������ �͵�. ���� �ȿ� ����ִ°� ��ŭ �ݺ�.
					Gangjwa sc = this.gangjwaVector.get(i);//ó�� ����, �״���, �״���... �����ɴϴ�.
					
					if (gangjwa.getyear() < sc.getyear()) {//���࿡, ���� �о���� ���°�, �����°��º��� ������ �������, �տ� �ֽ��ϴ�.
						this.gangjwaVector.add(i, gangjwa);// i�� ������ �ڸ������� �ڷ� �з�����. add�� set�� ����.
						i = this.gangjwaVector.size();//�ڸ��� ã�Ƽ� �־������Ƿ�, for���� �����ϴ�.
					}
					
					if (gangjwa.getyear() == sc.getyear()) {//����, ������ ���ٸ�, �б⸦ ���մϴ�.
						if (gangjwa.getsemester() < sc.getsemester()) {// ���� ���� ������ �бⰡ ���� ���� �б⺸�� ������,
							this.gangjwaVector.add(i, gangjwa);//�����°��� �ڸ��� ������ �ֽ��ϴ�. �����°��� �ڷ� �и��ϴ�. // ��������� �л��� ���� ���δ� ���� �ѹ���!
							i = this.gangjwaVector.size();//for���� �����ϴ�.
						}else{//���ų�, Ŭ���,
							this.gangjwaVector.add(i + 1, gangjwa);//�����°��� �����ڸ��� ���¸� �ֽ��ϴ�.
							i = this.gangjwaVector.size();//for���� �����ϴ�.
						}
					}
				}
				Gangjwa sc = this.gangjwaVector.get(this.gangjwaVector.size() - 1);//������ ���� ���� �ɴϴ�.
				if (sc.getyear() < gangjwa.getyear()) {//������ ������ ��ũ��
					this.gangjwaVector.add(gangjwa);//���� �ֽ��ϴ�.
				}
			}
		}
		scanner.close();
	}

	public void printGangjwaInfo(JTextArea textArea) throws GwamokNameNotFoundExeption   {
		for (Gangjwa gangjwa : this.gangjwaVector) {
			gangjwa.associate(this.gwamokList, this.haksaengList, this.kyosuList);/// ���⼭ ����ø� �ϴٴ�? �������Ϸ��� �������߳�.
			gangjwa.printinfo(textArea);
			textArea.append("\r\n");
		}
	}

	public void printhaksaengheader(int id, JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption  {//�л� ���� ��
		for (Gangjwa gangjwa : this.gangjwaVector) {
			gangjwa.associate(this.gwamokList, this.haksaengList, this.kyosuList);
			if (gangjwa.haksaengcheck(id)) {//���¿� �л��� �ִٸ�
				gangjwa.printheader(id, haksaeng_textarea);
			}
		}
	}
}
