package exeption;

import javax.swing.JTextArea;

public class HaksaengnamenotExistExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public HaksaengnamenotExistExeption(int id) {
		super(id+"�̶�� �й��� �ش��ϴ� �̸��� �����ϴ�.");
	}

	public void printStackTrace(JTextArea textArea, int haksaengID) {
		textArea.append("&����% �й� "+haksaengID+ "�� �ش��ϴ� �л��� �����ϴ�."+"\r\n");
	}

}
