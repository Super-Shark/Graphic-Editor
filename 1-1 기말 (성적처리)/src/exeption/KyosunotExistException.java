package exeption;

import javax.swing.JTextArea;

public class KyosunotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public KyosunotExistException(int haksaengID) {
		super(haksaengID + "라는 ID를 가진 교수는 없습니다.");
	}

	public void printStackTrace(JTextArea haksaeng_textarea, int kyosuID) {
		haksaeng_textarea.append("&오류% ID 가 "+kyosuID+ "인 교수는 없습니다."+"\r\n");
	}//강좌에서 쓸때는 줄넘김이 필요하고, 학생과 서치에서는 불필요하다. 수정ㄱ
}
