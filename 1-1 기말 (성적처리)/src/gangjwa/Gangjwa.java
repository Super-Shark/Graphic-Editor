package gangjwa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTextArea;

import exeption.GwamokNameNotFoundExeption;
import exeption.HaksaengnamenotExistExeption;
import exeption.KyosunotExistException;
import gwamok.GwamokList;
import haksaeng.HaksaengList;
import kyosu.KyosuList;


public class Gangjwa {

	private int id;
	private String name;
	private String gwamokName;
	private int year;
	private int semester;
	private int kyosuID;

	int gwamokID;// 1:1 �����Ǻ���! �����͸� ã�Ƴ���Ű
	private Vector<Score> scoreVector;// id�Ѱ��� n���� score�� ������.1:n//�ڽ���
	GwamokList gwamokList;// Ű�� �ҷ����°�
	HaksaengList haksaengList;// ���ÿ���Ƽ�� Ŭ����
	KyosuList kyosuList;

	public Gangjwa() {
		this.scoreVector = new Vector<Score>();
	}

	public void associate(GwamokList gwamokList, HaksaengList haksaengList, KyosuList kyosuList) {
		this.gwamokList = gwamokList;
		this.haksaengList = haksaengList;
		this.kyosuList = kyosuList;
	}

	public void readFromFile(String name) throws FileNotFoundException {

		File file = new File(name);

		Scanner scanner = new Scanner(file);
		this.id = scanner.nextInt();
		this.name = scanner.next();
		this.year = scanner.nextInt();
		this.semester = scanner.nextInt();
		this.kyosuID = scanner.nextInt();//�߰��Ѱ�
		this.gwamokID = scanner.nextInt();

		while (scanner.hasNext()) {
			Score score = new Score();
			score.readFromFile(scanner);
			
//������ ������
			if (this.scoreVector.size() == 0) {// ��ó�� ���ö��� ����
				this.scoreVector.add(score);
			} else {
				for (int i = 0; i < this.scoreVector.size(); i++) {// ������ �͵�
					Score sc = this.scoreVector.get(i);
					if (score.getkimal() > sc.getkimal()) {
						this.scoreVector.add(i, score);// i�� ������ �ڸ������� �ڷ� �з�����.
						i = this.scoreVector.size();//������ ����ϴ�
					}
				}
				Score sc = this.scoreVector.get(this.scoreVector.size() - 1);// �������� ���� ����
				if (sc.getkimal() > score.getkimal()) {
					this.scoreVector.add(score);
				}
			}
		}
		scanner.close();
	}
	



	public void printinfo(JTextArea textArea) throws GwamokNameNotFoundExeption   {//���� ó���ϸ� �� ����
		//������� ���Ŀ�带 �������� �Ϸ��� ���⼭ ĳġ!
				gwamokName = this.gwamokList.getGwamokName(this.gwamokID);
				textArea.append(gwamokName + " " + this.name + " " + this.year + "�⵵" + " " + this.semester + "�б�"+"\r\n");//����id��� �߰��ض�.
				textArea.append("���� ID : "+this.id+"\r\n");
				
				try {//����
					textArea.append("��米�� : " + this.kyosuList.getKyosuName(kyosuID)+"\r\n");//��������Ʈ���� ������ �������, ������ �ֳ�.
				} catch (KyosunotExistException e1) {
					e1.printStackTrace(textArea,kyosuID);
				}
				
				for (Score score : this.scoreVector) {
					score.writeToFile(textArea);
				}
				
				System.out.println();
	}

	private class Score {
		private int haksaengID;
		private int kimal;
		private char grade;

		public void writeToFile(JTextArea textArea)  {
			grading();
			String haksaengName="";
			try {
				haksaengName = haksaengList.getHaksaengName(this.haksaengID);
				textArea.append(haksaengName + " " + this.kimal + " " + this.grade+"\r\n");
			} catch (HaksaengnamenotExistExeption e) {
				e.printStackTrace(textArea,haksaengID);
			}
			
		}

		private void grading() {//���ھ� ������ ����� ��� �ֵ��� �����
			if (kimal > 84) {
				grade = 'A';
			} else if (kimal > 69) {
				grade = 'B';
			} else if (kimal > 49) {
				grade = 'C';
			} else if (kimal > 19) {
				grade = 'D';
			} else {
				grade = 'F';
			}
		}

		public int getkimal() {
			return this.kimal;
		}

		public void readFromFile(Scanner sc) {
			this.haksaengID = sc.nextInt();
				this.kimal = sc.nextInt();
		}
	}

	public boolean haksaengcheck(int id2) {
		boolean a =false;
		for (Score score : this.scoreVector) {
			if(id2==score.haksaengID) {//���ھ� ��ü�� ��
				a=true;
			}
		}
		return a;
	}

	public void printheader(int id2, JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption     {
		//������� ���Ŀ�带 �������� �Ϸ��� ���⼭ ĳġ!
			
				gwamokName = this.gwamokList.getGwamokName(this.gwamokID);
				haksaeng_textarea.append( this.year + "�⵵ " + this.semester + "�б� " +this.name + " "+gwamokName + " / ");
				
				try {
					haksaeng_textarea.append("��米�� : " + this.kyosuList.getKyosuName(kyosuID)+ " / ");
				} catch (KyosunotExistException e) {
					e.printStackTrace(haksaeng_textarea,kyosuID);
				}
				
				
				for (Score score : this.scoreVector) {
					if(id2==score.haksaengID) {
						score.grading();
						haksaeng_textarea.append("���� : "+score.kimal+" / "+"��� : "+score.grade+"\r\n");
					}
				}
	}

	public int getyear() {return this.year;}
	public int getsemester() {return this.semester;}

}
