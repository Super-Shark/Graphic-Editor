package exeption;

import javax.swing.JTextArea;

public class GwamokNameNotFoundExeption extends Exception {
	private static final long serialVersionUID = 1L;

	public GwamokNameNotFoundExeption() {
		super("이 아이디를 가진 과목이 없어요");
	}

	public void printStackTrace(JTextArea gangjwa_textarea) {
		gangjwa_textarea.append("&오류% 과목명이 없습니다."+"\r\n");
	}
}
