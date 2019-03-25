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

	int gwamokID;// 1:1 모든것의보고서! 데이터를 찾아내는키
	private Vector<Score> scoreVector;// id한개에 n개의 score을 가진다.1:n//자식임
	GwamokList gwamokList;// 키를 불러오는곳
	HaksaengList haksaengList;// 어쏘시에이티드 클래스
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
		this.kyosuID = scanner.nextInt();//추가한것
		this.gwamokID = scanner.nextInt();

		while (scanner.hasNext()) {
			Score score = new Score();
			score.readFromFile(scanner);
			
//점수를 정렬함
			if (this.scoreVector.size() == 0) {// 맨처음 들어올때를 위해
				this.scoreVector.add(score);
			} else {
				for (int i = 0; i < this.scoreVector.size(); i++) {// 나머지 것들
					Score sc = this.scoreVector.get(i);
					if (score.getkimal() > sc.getkimal()) {
						this.scoreVector.add(i, score);// i에 넣을때 자리주인은 뒤로 밀려난다.
						i = this.scoreVector.size();//포문을 멈춥니다
					}
				}
				Score sc = this.scoreVector.get(this.scoreVector.size() - 1);// 가장작은 값을 위해
				if (sc.getkimal() > score.getkimal()) {
					this.scoreVector.add(score);
				}
			}
		}
		scanner.close();
	}
	



	public void printinfo(JTextArea textArea) throws GwamokNameNotFoundExeption   {//여서 처리하면 다 워닝
		//과목네임 낫파운드를 워닝으로 하려면 여기서 캐치!
				gwamokName = this.gwamokList.getGwamokName(this.gwamokID);
				textArea.append(gwamokName + " " + this.name + " " + this.year + "년도" + " " + this.semester + "학기"+"\r\n");//강좌id출력 추가해라.
				textArea.append("강좌 ID : "+this.id+"\r\n");
				
				try {//워닝
					textArea.append("담당교수 : " + this.kyosuList.getKyosuName(kyosuID)+"\r\n");//교수리스트에서 던진게 밭아지고, 에리아 있네.
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

		private void grading() {//스코어 벡터의 사이즈가 듣는 애들의 명수니
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
			if(id2==score.haksaengID) {//스코어 전체를 봄
				a=true;
			}
		}
		return a;
	}

	public void printheader(int id2, JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption     {
		//과목네임 낫파운드를 워닝으로 하려면 여기서 캐치!
			
				gwamokName = this.gwamokList.getGwamokName(this.gwamokID);
				haksaeng_textarea.append( this.year + "년도 " + this.semester + "학기 " +this.name + " "+gwamokName + " / ");
				
				try {
					haksaeng_textarea.append("담당교수 : " + this.kyosuList.getKyosuName(kyosuID)+ " / ");
				} catch (KyosunotExistException e) {
					e.printStackTrace(haksaeng_textarea,kyosuID);
				}
				
				
				for (Score score : this.scoreVector) {
					if(id2==score.haksaengID) {
						score.grading();
						haksaeng_textarea.append("점수 : "+score.kimal+" / "+"등급 : "+score.grade+"\r\n");
					}
				}
	}

	public int getyear() {return this.year;}
	public int getsemester() {return this.semester;}

}
