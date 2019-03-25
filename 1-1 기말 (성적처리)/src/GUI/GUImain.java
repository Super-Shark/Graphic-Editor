package GUI;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gangjwa.GangjwaList;
import gwamok.GwamokList;
import haksaeng.HaksaengList;

@SuppressWarnings("serial")//저장할때와 관련있다봤는데, 까먹었다. // 직렬화 를 위한것. 버젼불일치가 적으니 무시해도 된다 카더라.
public class GUImain extends JFrame {

	private JPanel contentPane;// 패널만든다
	
	GwamokList gwamokList;
	HaksaengList haksaengList;
	GangjwaList gangjwalist;

	public void make() {
		GUImain frame = new GUImain(gwamokList, haksaengList,  gangjwalist);//2. 여기서 아래꺼 또 전체 실행됨.
		frame.setVisible(true);
	}
	
	public GUImain(GwamokList gwamokList, HaksaengList haksaengList, GangjwaList gangjwalist) {//1. 일단 여기가 실행됨. 
																													
		super("명지 대학교 성적 처리 프로그램 v43");//제목?
		this.gwamokList = gwamokList;
		this.haksaengList = haksaengList;
		this.gangjwalist = gangjwalist;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x누르면 프로그램 종료.
		setBounds(100, 100, 500, 300);// (x좌표,-y좌표,가로,세로) 컴퓨터 화면 전체에서.
		setLocationRelativeTo(null);// 가운데로 옮겨줌. 위의 좌표는 상관없어짐. 근데 위에꺼 지우면 에러떠서 위에는 남겨놓음.
		contentPane = new JPanel();// 실체화
		contentPane.setLayout(null);//여러가지들을 아무데나 놀수있게 해줌.(좌표로)
		contentPane.setBackground(Color.WHITE);//색
		setContentPane(contentPane);//메인을 설정하는 정도?

		JButton gwamokbtn = new JButton("Gawmok Information");//과목 버튼
		gwamokbtn.setBackground(Color.WHITE);//색
		gwamokbtn.addActionListener(new ActionListener() {//눌렸을때 실행되는것.
			public void actionPerformed(ActionEvent arg0) {
				GUI_Gwamok.main(gwamokList);
			}
		});
		gwamokbtn.setBounds(250, 60, 176, 23);//버튼의 위치
		contentPane.add(gwamokbtn);//메인판에 넣기

		JButton gangjwabtn = new JButton("Gangjwa Information");//강좌 버튼
		gangjwabtn.setBackground(Color.WHITE);// 잘쓰이는색을 짜논걸 썻습니다
		gangjwabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Gangjwa.main(gangjwalist);
			}
		});
		gangjwabtn.setBounds(250, 90, 176, 23);//메인판에서(x,-y,가,세)
		contentPane.add(gangjwabtn);

		JButton haksaengbtn = new JButton("Haksaeng Information");//학생 버튼
		haksaengbtn.setBackground(Color.WHITE);
		haksaengbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Haksaeng.main(haksaengList);
			}
		});
		haksaengbtn.setBounds(250, 120, 176, 23);
		contentPane.add(haksaengbtn);
		
		JButton search = new JButton("Search Hakseang");//서치 버튼
		search.setBackground(Color.WHITE);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Search.main(haksaengList);
			}
		});
		search.setBounds(250, 150, 176, 23);
		contentPane.add(search);

		JLabel image = new JLabel(new ImageIcon("src/aa.png"));//이미지 넣음
		image.setBounds(40, 10, 190, 200);//위치,사이즈,
		contentPane.add(image);
	}

}
