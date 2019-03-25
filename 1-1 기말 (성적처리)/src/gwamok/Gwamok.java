package gwamok;

import java.util.Scanner;

import javax.swing.JTextArea;

public class Gwamok {
	private int id;
	private String name;

	public int getId() {return id;}
	public String getName() {return name;}

	public void readFromFile(Scanner scanner) {
		this.id = scanner.nextInt();
		this.name = scanner.next();
	}

	public void printinfo(JTextArea textArea) {
		textArea.append(this.id + " " + this.name+"\r\n");
	}
}
