package haksaeng;

import java.util.Scanner;

import javax.swing.JTextArea;

import exeption.GwamokNameNotFoundExeption;
import gangjwa.GangjwaList;

public class Haksaeng {

	private int id;
	private String name;

	public int getId() {return id;}
	public String getName() {return name;}

	public void readFromfile(Scanner sc) {
		this.id = sc.nextInt();
		this.name = sc.next();
	}

	public void printinfo(GangjwaList gangjwalist, JTextArea haksaeng_textarea) throws GwamokNameNotFoundExeption   {//학생인포
		haksaeng_textarea.append(this.id + " " + this.name + "\r\n");
		gangjwalist.printhaksaengheader(this.id, haksaeng_textarea);
	}
	
}