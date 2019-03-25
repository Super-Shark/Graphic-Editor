package GUI;

import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import exeption.GwamokNameNotFoundExeption;
import gangjwa.GangjwaList;

@SuppressWarnings("serial")//? ������ ����ٶ��� �س��� ��. // ����ȭ �� ���Ѱ�. ��������ġ�� ������ �����ص� �ȴ� ī����.
public class GUI_Gangjwa extends JFrame {

	private JPanel contentPane;
	static JTextArea gangjwa_textarea;
	static JScrollPane scrollPane;

	public static void main(GangjwaList gangjwalist) {
		//���³��Ӿ��� ���ܸ� ũ��/���� ���ιٲٱ� = Ʈ����ĳġ������, �߷ο��������� ��ºο� Ʈ����ĳġ
		//�л��� �л�gui-�л�����Ʈ-�л�-���¸���Ʈ-���� ���. �ٹٲ����. �̰Źٲٸ� �л���ġ�� �ٲ�.

		GUI_Gangjwa frame = new GUI_Gangjwa();
		frame.setVisible(true);
	
			try {//ũ��Ƽ��
				gangjwalist.printGangjwaInfo(gangjwa_textarea);
			} catch (GwamokNameNotFoundExeption e) {//������� ���Ŀ�带 �������� �Ϸ��� ������ ǥ�� �ΰ��� ���Ķ�. �������� �������� ��ġ��
				// TODO �ڵ� ������ catch ���
				e.printStackTrace(gangjwa_textarea);
			}
		
		//�� ������ ��ü info�� ����ϱ� ������, ���⼭ try/catch�ϸ�, �ٷ� ������ �ִ�.(ũ��Ƽ��)
		
		Runnable doScroll = new Runnable() {// �̰� ��¥ ã�� ��������.....��ũ�ѹ� ���� �ø���....
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		};
		SwingUtilities.invokeLater(doScroll);
	}

	public GUI_Gangjwa() {
		super("Gangjwa Information"); //��â�� ǥ���Ϸ���, contentPanel�̶� scrollPanel������ ����Ű���. 800��? ��ũ���� �ʹ� �۰�.
		setBounds(100, 100, 500, 300);//â���� x,-y�� 
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);// ����� �Ű���.
		setContentPane(contentPane);

		gangjwa_textarea = new JTextArea();
		scrollPane = new JScrollPane(gangjwa_textarea);
		scrollPane.setBounds(0, 0, 485, 262);//��ũ�ѹٴ� �ʿ��Ҷ��� ���ɴϴ�.//�ĴϿ��� x,-y��.
		contentPane.add(scrollPane);
		gangjwa_textarea.setEditable(false);
	}
}
