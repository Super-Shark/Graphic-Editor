package exeption;

import javax.swing.JTextArea;

public class KyosunotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public KyosunotExistException(int haksaengID) {
		super(haksaengID + "��� ID�� ���� ������ �����ϴ�.");
	}

	public void printStackTrace(JTextArea haksaeng_textarea, int kyosuID) {
		haksaeng_textarea.append("&����% ID �� "+kyosuID+ "�� ������ �����ϴ�."+"\r\n");
	}//���¿��� ������ �ٳѱ��� �ʿ��ϰ�, �л��� ��ġ������ ���ʿ��ϴ�. ������
}
