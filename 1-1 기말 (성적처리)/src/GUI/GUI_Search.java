package GUI;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import haksaeng.HaksaengList;

@SuppressWarnings("serial")// 직렬화 를 위한것. 버젼불일치가 적으니 무시해도 된다 카더라.
public class GUI_Search extends JFrame {
//학생과 비슷한데, 입력한 숫자를 읽고, 숫자==id만 출력하는게 다르다.
	private JPanel contentPane;
	static JTextField Field;
	JTextArea Area;
	static HaksaengList HaksaengList;

	public static void main(HaksaengList haksaengList) {
		HaksaengList = haksaengList;
		GUI_Search frame = new GUI_Search();
		frame.setVisible(true);
	}

	public GUI_Search() {
		super("Search Hakseang");
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);//마구놓기
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);// 가운데로 옮겨줌.
		setContentPane(contentPane);

		Field = new JTextField();//한줄짜리
		Field.setBounds(135, 55, 115, 20);
		contentPane.add(Field);

		Area = new JTextArea();//여러줄짜리
		Area.setEditable(false);//편집불가능
		Area.setBounds(20, 100, 450, 300);
		contentPane.add(Area);

		JButton button = new JButton("Search");
		button.setMnemonic(KeyEvent.VK_ENTER);// alt+enter로 눌러짐!
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Area.setText("");// 초기화?
				String a = Field.getText();// 위쪽에 작은거
				if (a.length() == 0) {
					Area.setText("찾으려는 학생의 학번을입력 하세요.");
				} else {

					try {
						int cnt = Integer.parseInt(a);// 입력된거 인트로!
						HaksaengList.SearchHaksaeng(Area, cnt);
					} catch (NumberFormatException ee) {//오오 된다! 갱장해! 예외 직접 찾아봄.@@@ 숫자가 아닌 예외였나
						Area.setText("바른 번호를 입력하여 주세요.");
					}
				}
				if (Area.getText().equals("")) {//꼼수라 해야하나? 진짜 있나 없나가 아니고, 없으면 출력 안되는걸 보고, 만듬.
					Area.setText("해당하는 학생이 없습니다.");
				}
			}
		});
		button.setBounds(264, 54, 106, 23);
		contentPane.add(button);

	}
}