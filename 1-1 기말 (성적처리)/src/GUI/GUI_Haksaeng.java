package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import exeption.GwamokNameNotFoundExeption;
import haksaeng.HaksaengList;

@SuppressWarnings("serial") // ����ȭ �� ���Ѱ�. ��������ġ�� ������ �����ص� �ȴ� ī����.
public class GUI_Haksaeng extends JFrame {

	private JPanel contentPane;
	static JTextArea haksaeng_textarea;
	static JScrollPane scrollPane;

	public static void main(HaksaengList haksaengList) {
		
		GUI_Haksaeng frame = new GUI_Haksaeng();
		frame.setVisible(true);
		
			try {//�������  ũ��Ƽ�� //������� ���Ŀ�带 �������� �Ϸ��� ������ ǥ�� �ΰ��� ���Ķ�. �������� �������� ��ġ��
				haksaengList.printHaksaengInfo(haksaeng_textarea);
			} catch (GwamokNameNotFoundExeption e) {
				e.printStackTrace(haksaeng_textarea);
			}
		

		Runnable doScroll = new Runnable() {// �̰� ��¥ ã�� ��������.....��ũ�ѹ� ���� �ø���....
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
		setLocationRelativeTo(null);// ����� �Ű���.
		setContentPane(contentPane);

		haksaeng_textarea = new JTextArea();
		scrollPane = new JScrollPane(haksaeng_textarea);// ��ũ�ѹ� �߰��ϴ� ����.
		scrollPane.setBounds(0, 0, 485, 262);//������ �����ϱ� �����.
		contentPane.add(scrollPane);
		haksaeng_textarea.setEditable(false);
		haksaeng_textarea.setBounds(0, 0, 450, 300);
	}
}
