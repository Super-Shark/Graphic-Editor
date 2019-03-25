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

@SuppressWarnings("serial")//�����Ҷ��� �����ִٺôµ�, ��Ծ���. // ����ȭ �� ���Ѱ�. ��������ġ�� ������ �����ص� �ȴ� ī����.
public class GUImain extends JFrame {

	private JPanel contentPane;// �гθ����
	
	GwamokList gwamokList;
	HaksaengList haksaengList;
	GangjwaList gangjwalist;

	public void make() {
		GUImain frame = new GUImain(gwamokList, haksaengList,  gangjwalist);//2. ���⼭ �Ʒ��� �� ��ü �����.
		frame.setVisible(true);
	}
	
	public GUImain(GwamokList gwamokList, HaksaengList haksaengList, GangjwaList gangjwalist) {//1. �ϴ� ���Ⱑ �����. 
																													
		super("���� ���б� ���� ó�� ���α׷� v43");//����?
		this.gwamokList = gwamokList;
		this.haksaengList = haksaengList;
		this.gangjwalist = gangjwalist;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x������ ���α׷� ����.
		setBounds(100, 100, 500, 300);// (x��ǥ,-y��ǥ,����,����) ��ǻ�� ȭ�� ��ü����.
		setLocationRelativeTo(null);// ����� �Ű���. ���� ��ǥ�� ���������. �ٵ� ������ ����� �������� ������ ���ܳ���.
		contentPane = new JPanel();// ��üȭ
		contentPane.setLayout(null);//������������ �ƹ����� ����ְ� ����.(��ǥ��)
		contentPane.setBackground(Color.WHITE);//��
		setContentPane(contentPane);//������ �����ϴ� ����?

		JButton gwamokbtn = new JButton("Gawmok Information");//���� ��ư
		gwamokbtn.setBackground(Color.WHITE);//��
		gwamokbtn.addActionListener(new ActionListener() {//�������� ����Ǵ°�.
			public void actionPerformed(ActionEvent arg0) {
				GUI_Gwamok.main(gwamokList);
			}
		});
		gwamokbtn.setBounds(250, 60, 176, 23);//��ư�� ��ġ
		contentPane.add(gwamokbtn);//�����ǿ� �ֱ�

		JButton gangjwabtn = new JButton("Gangjwa Information");//���� ��ư
		gangjwabtn.setBackground(Color.WHITE);// �߾��̴»��� ¥��� �����ϴ�
		gangjwabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Gangjwa.main(gangjwalist);
			}
		});
		gangjwabtn.setBounds(250, 90, 176, 23);//�����ǿ���(x,-y,��,��)
		contentPane.add(gangjwabtn);

		JButton haksaengbtn = new JButton("Haksaeng Information");//�л� ��ư
		haksaengbtn.setBackground(Color.WHITE);
		haksaengbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Haksaeng.main(haksaengList);
			}
		});
		haksaengbtn.setBounds(250, 120, 176, 23);
		contentPane.add(haksaengbtn);
		
		JButton search = new JButton("Search Hakseang");//��ġ ��ư
		search.setBackground(Color.WHITE);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Search.main(haksaengList);
			}
		});
		search.setBounds(250, 150, 176, 23);
		contentPane.add(search);

		JLabel image = new JLabel(new ImageIcon("src/aa.png"));//�̹��� ����
		image.setBounds(40, 10, 190, 200);//��ġ,������,
		contentPane.add(image);
	}

}
