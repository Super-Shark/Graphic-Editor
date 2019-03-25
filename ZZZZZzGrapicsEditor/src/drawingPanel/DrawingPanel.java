package drawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
 
	MouseHandler mouseHandler;
	
	private Shape currentTool ;//불일치 가능성
	public void setCurrentTool(EToolBar eToolBar) {
		this.currentTool = eToolBar.getShape();
	}

	public DrawingPanel() {
		this.setBackground(Color.WHITE);
		mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		currentTool = EToolBar.rect.getShape();
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {//함수 호출 만 하게 하라.
		public void mousePressed(MouseEvent e) {
			drawShape(e.getX(), e.getY());
		}
		public void mouseDragged(MouseEvent e) {
			moveShape(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
	}
	
	public void drawShape(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.setOrigin(x, y);
		currentTool.draw(graphics);
	}

	public void moveShape(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.draw(graphics);
		currentTool.setPoint(x, y);
		currentTool.draw(graphics);
	}
}
