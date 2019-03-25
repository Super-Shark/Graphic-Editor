package gwamok;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTextArea;

import exeption.GwamokNameNotFoundExeption;

public class GwamokList {

	private Vector<Gwamok> GwamokVector;

	public GwamokList() {
		this.GwamokVector = new Vector<Gwamok>();
	}

	public void readFromFile() throws FileNotFoundException {
		File file = new File("data/Gwamok.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			Gwamok gwamok = new Gwamok();
			gwamok.readFromFile(scanner);
			this.GwamokVector.add(gwamok);
		}
		scanner.close();
	}

	public String getGwamokName(int gwamokID) throws GwamokNameNotFoundExeption {
		for (Gwamok gwamok : this.GwamokVector) {
			if (gwamok.getId() == gwamokID) {
				return gwamok.getName();
			}
		}
		throw new GwamokNameNotFoundExeption();
	}
	
	public void printGwamokinfo(JTextArea textArea) {
		for (Gwamok gwamok : this.GwamokVector) {
			gwamok.printinfo(textArea);
		}
	}
}
