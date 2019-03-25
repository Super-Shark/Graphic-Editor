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

@SuppressWarnings("serial")// ����ȭ �� ���Ѱ�. ��������ġ�� ������ �����ص� �ȴ� ī����.
public class GUI_Search extends JFrame {
//�л��� ����ѵ�, �Է��� ���ڸ� �а�, ����==id�� ����ϴ°� �ٸ���.
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
		contentPane.setLayout(null);//��������
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);// ����� �Ű���.
		setContentPane(contentPane);

		Field = new JTextField();//����¥��
		Field.setBounds(135, 55, 115, 20);
		contentPane.add(Field);

		Area = new JTextArea();//������¥��
		Area.setEditable(false);//�����Ұ���
		Area.setBounds(20, 100, 450, 300);
		contentPane.add(Area);

		JButton button = new JButton("Search");
		button.setMnemonic(KeyEvent.VK_ENTER);// alt+enter�� ������!
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Area.setText("");// �ʱ�ȭ?
				String a = Field.getText();// ���ʿ� ������
				if (a.length() == 0) {
					Area.setText("ã������ �л��� �й����Է� �ϼ���.");
				} else {

					try {
						int cnt = Integer.parseInt(a);// �ԷµȰ� ��Ʈ��!
						HaksaengList.SearchHaksaeng(Area, cnt);
					} catch (NumberFormatException ee) {//���� �ȴ�! ������! ���� ���� ã�ƺ�.@@@ ���ڰ� �ƴ� ���ܿ���
						Area.setText("�ٸ� ��ȣ�� �Է��Ͽ� �ּ���.");
					}
				}
				if (Area.getText().equals("")) {//�ļ��� �ؾ��ϳ�? ��¥ �ֳ� ������ �ƴϰ�, ������ ��� �ȵǴ°� ����, ����.
					Area.setText("�ش��ϴ� �л��� �����ϴ�.");
				}
			}
		});
		button.setBounds(264, 54, 106, 23);
		contentPane.add(button);

	}
}