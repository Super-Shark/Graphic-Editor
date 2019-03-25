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
		File file = new File("data/gangjwaList.txt");// 파일네임이있어 //앞에 data를 간단하게 1,2,3으로 간단하게 바꾸는 방법은? -> main에 써놓음.
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String name = scanner.next();//주소를 읽음
			Gangjwa gangjwa = new Gangjwa();
			gangjwa.readFromFile(name);

			// 여기서 정렬하면 강좌들과 학생정렬을 할필요가 없습니다. 년도, 학기순으로 죄다 정렬이 되서 들어가기때문에,
			// 출력할떄 정렬할 필요가 없습니다.

			// 예상 문제
			// 호옥시 교수님이 강좌는 강좌 ID순으로 출력하게 하라 하시면, 여기있는 배열 복사해놓고, 연도와 학기부분지운후,
			// ID에따라 정렬시키고, 학생부분은 정렬을 따로 또해줘야하는데, 밑에있는 학생해더 출력부분에 포문안에,강좌 사이즈만큼 도는
			// for를만들고, 돌ㄹ는데, 흐름 안바꾸면, 강좌id순에따라 출력되는데, 벡터를 하나 더만들고, 강좌에서 연도랑 학기를 밭아,
			// 아래의 정렬을 사용해서 정렬시킨후(안쓰이는부분이있지만, 빠르게하기위해) 벡터를 순서대로 출력시키면 된다.
			//학생꺼 정렬안해도되면, 여기것만 바꾸면 된다.

			// 연도와 학기를 정렬함. 퀵소트를 바꾸라하면 못할것 같아서. 내가 만든것 사용했음.
			if (this.gangjwaVector.size() == 0) {// 맨처음 들어올때를 위해. @@@어센딩, 디센딩 하려면 <>4개 바꿔주면 된다. 기본은 어센딩.
				this.gangjwaVector.add(gangjwa);//처음것은 그냥 넣어 준다.
			} else {
				for (int i = 0; i < this.gangjwaVector.size(); i++) {// 나머지 것들. 벡터 안에 들어있는것 만큼 반복.
					Gangjwa sc = this.gangjwaVector.get(i);//처음 강좌, 그다음, 그다음... 꺼내옵니다.
					
					if (gangjwa.getyear() < sc.getyear()) {//만약에, 지금 읽어들인 강좌가, 꺼내온강좌보다 연도가 빠를경우, 앞에 넣습니다.
						this.gangjwaVector.add(i, gangjwa);// i에 넣을때 자리주인은 뒤로 밀려난다. add와 set의 차이.
						i = this.gangjwaVector.size();//자리를 찾아서 넣어줬으므로, for문을 끝냅니다.
					}
					
					if (gangjwa.getyear() == sc.getyear()) {//만약, 연도가 같다면, 학기를 비교합니다.
						if (gangjwa.getsemester() < sc.getsemester()) {// 지금 읽은 강좌의 학기가 꺼낸 것의 학기보다 빠르면,
							this.gangjwaVector.add(i, gangjwa);//꺼내온것의 자리에 읽은걸 넣습니다. 꺼내온것은 뒤로 밀립니다. // 강좌헤더랑 학생의 내용 전부다 정렬 한번에!
							i = this.gangjwaVector.size();//for문을 끝냅니다.
						}else{//같거나, 클경우,
							this.gangjwaVector.add(i + 1, gangjwa);//꺼내온것의 다음자리에 강좌를 넣습니다.
							i = this.gangjwaVector.size();//for문을 끝냅니다.
						}
					}
				}
				Gangjwa sc = this.gangjwaVector.get(this.gangjwaVector.size() - 1);//마지막 값을 가져 옵니다.
				if (sc.getyear() < gangjwa.getyear()) {//마지막 값보다 더크면
					this.gangjwaVector.add(gangjwa);//끝에 넣습니다.
				}
			}
		}
		scanner.close();
	}

	public void printGangjwaInfo(JTextArea textArea) throws GwamokNameNotFoundExeption   {
		for (Gangjwa gangjwa : this.gangjwaVector) {
			gangjwa.associate(this.gwamokList, this.haksaengList, this.kyosuList);/// 여기서 오쏘시를 하다닝? 위에ㅅ하려니 에러가뜨네.
			gangjwa.printinfo(textArea);
			textArea.append("\r\n");
		}
	}

	public void printhaksaengheader(int id, JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption  {//학생 인포 꺼
		for (Gangjwa gangjwa : this.gangjwaVector) {
			gangjwa.associate(this.gwamokList, this.haksaengList, this.kyosuList);
			if (gangjwa.haksaengcheck(id)) {//강좌에 학생이 있다면
				gangjwa.printheader(id, haksaeng_textarea);
			}
		}
	}
}
