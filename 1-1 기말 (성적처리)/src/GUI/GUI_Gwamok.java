package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import gwamok.GwamokList;

@SuppressWarnings("serial") // ���� �𸣴µ� �̰ž����� ����ٶ�. �ִٰ� ���������� ����. // ����ȭ �� ���Ѱ�. ��������ġ�� ������ �����ص� �ȴ� ī����.
public class GUI_Gwamok extends JFrame {

	private JPanel contentPane;
	static JTextArea textArea;
	static JScrollPane scrollPane;

	public static void main(GwamokList gwamokList) {

		GUI_Gwamok frame = new GUI_Gwamok();
		frame.setVisible(true);
		gwamokList.printGwamokinfo(textArea);

		Runnable doScroll = new Runnable() {// �̰� ��¥ ã�� ��������.....��ũ�ѹ� ���� �ø���....
			public void run() {//���!
				scrollPane.getVerticalScrollBar().setValue(0);//�̰�, area�� ������,append��Ų�Ŷ� �׷���,��ũ���� �Ʒ��ִµ�, ����� ���Ŀ� �ø��°� �������.
			}//������ �ƽ����ϸ� �ǾƷ����� �ϴµ�, ����� ��Ų�Ŷ� ���ص� �ǾƷ���. �������� �ʰ�.
		};
		SwingUtilities.invokeLater(doScroll);//�۾� �Ϸ��� ����.
	}

	public GUI_Gwamok() {
		super("Gawmok Information");
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);// ����� �Ű���.
		setContentPane(contentPane);

		textArea = new JTextArea();// fanel�� ����,area�� ������.
		scrollPane = new JScrollPane(textArea);// ��ũ�ѹ� �߰��ϴ� ����.
		scrollPane.setBounds(0, 0, 485, 270);// ��ġ��Ⱑ �����.
		contentPane.add(scrollPane);

		textArea.setEditable(false);// ����ų� ���� �ȵǰ�. ���������̴ϱ�.���� ������ ����� ����.�б⵵ �����ѵ� �����Ҷ�� �� �ݴ�� �� �ؾ��ϴ°� �ƴѰ�?

	}

}
