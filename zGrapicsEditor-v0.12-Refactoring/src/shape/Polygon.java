package shape;

import java.awt.Graphics;

public class Polygon extends Shape{
	
	private final static int nMaxPoints = 20;
	int nPoints;
	int[] xPoints;
	int[] yPoints;
	
	public Polygon() {
		this.nPoints = 0;
		this.xPoints = new int [20];
		this.yPoints = new int [20];
	}
	
	public void setOrigin(int x, int y) {
		this.xPoints[this.nPoints]=x;
		this.yPoints[this.nPoints]=y;
		this.nPoints += 1;
		
		this.xPoints[this.nPoints]=x;
		this.yPoints[this.nPoints]=y;
		this.nPoints += 1;
	}
	public void setPoint(int x, int y) {
		this.xPoints[this.nPoints-1]=x;
		this.yPoints[this.nPoints-1]=y;
	}
	public void addPoint(int x, int y) {
		this.xPoints[this.nPoints]=x;
		this.yPoints[this.nPoints]=y;
		this.nPoints += 1;
	}
	
	public void draw(Graphics g) {
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

}
