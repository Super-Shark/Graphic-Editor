package exeption;

import javax.swing.JTextArea;

public class GwamokNameNotFoundExeption extends Exception {
	private static final long serialVersionUID = 1L;

	public GwamokNameNotFoundExeption() {
		super("�� ���̵� ���� ������ �����");
	}

	public void printStackTrace(JTextArea gangjwa_textarea) {
		gangjwa_textarea.append("&����% ������� �����ϴ�."+"\r\n");
	}
}
