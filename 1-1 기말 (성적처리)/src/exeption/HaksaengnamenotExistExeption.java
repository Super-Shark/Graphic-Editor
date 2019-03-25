package exeption;

import javax.swing.JTextArea;

public class HaksaengnamenotExistExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public HaksaengnamenotExistExeption(int id) {
		super(id+"이라는 학번에 해당하는 이름이 없습니다.");
	}

	public void printStackTrace(JTextArea textArea, int haksaengID) {
		textArea.append("&오류% 학번 "+haksaengID+ "에 해당하는 학생은 없습니다."+"\r\n");
	}

}
