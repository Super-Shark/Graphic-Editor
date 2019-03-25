package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import gwamok.GwamokList;

@SuppressWarnings("serial") // 뭔진 모르는데 이거없으면 노란줄뜸. 있다고 에러뜨지도 않음. // 직렬화 를 위한것. 버젼불일치가 적으니 무시해도 된다 카더라.
public class GUI_Gwamok extends JFrame {

	private JPanel contentPane;
	static JTextArea textArea;
	static JScrollPane scrollPane;

	public static void main(GwamokList gwamokList) {

		GUI_Gwamok frame = new GUI_Gwamok();
		frame.setVisible(true);
		gwamokList.printGwamokinfo(textArea);

		Runnable doScroll = new Runnable() {// 이거 진짜 찾기 힘들었어요.....스크롤바 위로 올리기....
			public void run() {//등록!
				scrollPane.getVerticalScrollBar().setValue(0);//이게, area를 만든후,append스킨거라 그런지,스크롤이 아래있는데, 실행된 이후에 올리는게 힘들었음.
			}//벨류를 맥스로하면 맨아래가긴 하는데, 어펜드 시킨거라 안해도 맨아래임. 원하지도 않고.
		};
		SwingUtilities.invokeLater(doScroll);//작업 완료후 실행.
	}

	public GUI_Gwamok() {
		super("Gawmok Information");
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);// 가운데로 옮겨줌.
		setContentPane(contentPane);

		textArea = new JTextArea();// fanel은 한줄,area는 여러줄.
		scrollPane = new JScrollPane(textArea);// 스크롤바 추가하는 세줄.
		scrollPane.setBounds(0, 0, 485, 270);// 위치잡기가 힘들다.
		contentPane.add(scrollPane);

		textArea.setEditable(false);// 지우거나 편집 안되게. 보기전용이니까.편집 만들기는 힘들것 같다.읽기도 복잡한데 쓰기할라면 다 반대로 또 해야하는거 아닌가?

	}

}
