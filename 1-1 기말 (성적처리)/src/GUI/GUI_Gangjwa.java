package GUI;

import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import exeption.GwamokNameNotFoundExeption;
import gangjwa.GangjwaList;

@SuppressWarnings("serial")//? 없으면 노랑줄떠서 해놓긴 함. // 직렬화 를 위한것. 버젼불일치가 적으니 무시해도 된다 카더라.
public class GUI_Gangjwa extends JFrame {

	private JPanel contentPane;
	static JTextArea gangjwa_textarea;
	static JScrollPane scrollPane;

	public static void main(GangjwaList gangjwalist) {
		//강좌네임없음 예외를 크리/워닝 으로바꾸기 = 트라이캐치읎에고, 뜨로우따라없에고 출력부에 트라이캐치
		//학생은 학생gui-학생리스트-학생-강좌리스트-강좌 길다. 다바꿔야함. 이거바꾸면 학생서치도 바뀜.

		GUI_Gangjwa frame = new GUI_Gangjwa();
		frame.setVisible(true);
	
			try {//크리티컬
				gangjwalist.printGangjwaInfo(gangjwa_textarea);
			} catch (GwamokNameNotFoundExeption e) {//과목네임 낫파운드를 워닝으로 하려면 강좌의 표시 두개를 고쳐라. 나머지는 에러따라 고치기
				// TODO 자동 생성된 catch 블록
				e.printStackTrace(gangjwa_textarea);
			}
		
		//이 라인이 전체 info를 출력하기 때문에, 여기서 try/catch하면, 바로 끝낼수 있다.(크리티컬)
		
		Runnable doScroll = new Runnable() {// 이거 진짜 찾기 힘들었어요.....스크롤바 위로 올리기....
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		};
		SwingUtilities.invokeLater(doScroll);
	}

	public GUI_Gangjwa() {
		super("Gangjwa Information"); //한창에 표현하려면, contentPanel이랑 scrollPanel사이즈 업시키면됨. 800쯤? 스크롤은 쫌더 작게.
		setBounds(100, 100, 500, 300);//창에서 x,-y임 
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);// 가운데로 옮겨줌.
		setContentPane(contentPane);

		gangjwa_textarea = new JTextArea();
		scrollPane = new JScrollPane(gangjwa_textarea);
		scrollPane.setBounds(0, 0, 485, 262);//스크롤바는 필요할때만 나옵니당.//파니에서 x,-y임.
		contentPane.add(scrollPane);
		gangjwa_textarea.setEditable(false);
	}
}
