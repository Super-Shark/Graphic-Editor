package global;

import shape.Rectangle;
import shape.Shape;

public class Constants {//����� ���̴� ��ü�� �迭

	public enum EMainFrame{
		w(400),h(600);
		private int v;
		private EMainFrame(int v){this.v = v;}
		public int getVal() {return this.v;}
	}

	public enum EMenu{
		fileMenu("File"),
		editMenu("Edit");
		private String s;
		private EMenu(String s){this.s = s;}
		public String getVal() {return this.s;}
	}
	
	public enum EFileMenu{
		newItem("new"),
		openItem("open");
		private String s;
		private EFileMenu(String s){this.s = s;}
		public String getVal() {return this.s;}
	}
	
	public enum EToolBar{
		select("�׸�", new Rectangle()),
		rect("�׸�", new Rectangle()),
		ellipse("�׸�", new Rectangle()),
		line("�׸�", new Rectangle());
		private String s;
		private Shape shape;
		private EToolBar(String s, Shape shape) {
			this.s = s;
			this.shape = shape;
		}
		public String getVal() {
			return this.s;
		}
		public Shape getShape() {
			return this.shape;
		}
	}
}
