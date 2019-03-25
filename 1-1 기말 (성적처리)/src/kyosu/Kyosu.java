package kyosu;

import java.util.Scanner;

public class Kyosu {
	private int id;
	private String name ;

	public int getId() {return id;}
	public String getName() {return name;}

	public  void readFromfile(Scanner sc) {
		this.id = sc.nextInt();
		this.name = sc.next();
	}
}
