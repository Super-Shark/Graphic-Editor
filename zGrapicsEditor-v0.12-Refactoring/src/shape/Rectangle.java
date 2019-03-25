package shape;

import java.awt.Graphics;

public class Rectangle extends Shape{
	public void draw(Graphics g) {
		g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
	}

}
