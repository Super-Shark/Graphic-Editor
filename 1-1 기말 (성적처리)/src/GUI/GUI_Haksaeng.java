package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import exeption.GwamokNameNotFoundExeption;
import haksaeng.HaksaengList;

@SuppressWarnings("serial") // 직렬화 를 위한것. 버젼불일치가 적으니 무시해도 된다 카더라.
public class GUI_Haksaeng extends JFrame {

	private JPanel contentPane;
	static JTextArea haksaeng_textarea;
	static JScrollPane scrollPane;

	public static void main(HaksaengList haksaengList) {
		
		GUI_Haksaeng frame = new GUI_Haksaeng();
		frame.setVisible(true);
		
			try {//과목네임  크리티컬 //과목네임 낫파운드를 워닝으로 하려면 강좌의 표시 두개를 고쳐라. 나머지는 에러따라 고치기
				haksaengList.printHaksaengInfo(haksaeng_textarea);
			} catch (GwamokNameNotFoundExeption e) {
				e.printStackTrace(haksaeng_textarea);
			}
		

		Runnable doScroll = new Runnable() {// 이거 진짜 찾기 힘들었어요.....스크롤바 위로 올리기....
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		};
		SwingUtilities.invokeLater(doScroll);
	}

	public GUI_Haksaeng() {
		super("Haksaeng Information");
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);// 가운데로 옮겨줌.
		setContentPane(contentPane);

		haksaeng_textarea = new JTextArea();
		scrollPane = new JScrollPane(haksaeng_textarea);// 스크롤바 추가하는 세줄.
		scrollPane.setBounds(0, 0, 485, 262);//일일이 조정하기 힘들다.
		contentPane.add(scrollPane);
		haksaeng_textarea.setEditable(false);
		haksaeng_textarea.setBounds(0, 0, 450, 300);
	}
}
