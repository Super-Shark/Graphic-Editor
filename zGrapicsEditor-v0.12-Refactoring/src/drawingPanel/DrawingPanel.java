package drawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
 
	MouseHandler mouseHandler;
	
	private Shape currentTool ;//불일치 가능성
	private Shape currentShape;
	private Vector<Shape> shapeVector;
	
	public void setCurrentTool(EToolBar eToolBar) {
		this.currentTool = eToolBar.getShape();
	}

	public DrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.setBackground(Color.WHITE);
		mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.shapeVector = new Vector<Shape>();
		currentTool = EToolBar.rect.getShape();
	}
	public void paint(Graphics g) {
		super.paint(g);
		for(Shape s : this.shapeVector) {
			s.draw(g);
		}
	}
	private enum EActionState{eReady, e2PDrawing, eNPDrawing}; //레디, 폴리곤(nPointDrawing) , 나머지(2PointDrawing)
	EActionState eActionState;
	
	private class MouseHandler implements MouseListener, MouseMotionListener {//함수 호출 만 하게 하라.
		public void mousePressed(MouseEvent e) {
			if(eActionState==EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState=EActionState.e2PDrawing;
			}
		}
		public void mouseReleased(MouseEvent e) {
			if(eActionState==EActionState.e2PDrawing) {
				finishDrawing(e.getX(), e.getY());
				eActionState=EActionState.eReady;
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(eActionState==EActionState.e2PDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		public void mouseMoved(MouseEvent e) {
			if(eActionState==EActionState.eNPDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==1) {
				mouse1Clicked(e);
			}else if(e.getClickCount()==2) {
				mouse2Clicked(e);
			}
		}
		private void mouse1Clicked(MouseEvent e) {
			if(eActionState==EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState=EActionState.eNPDrawing;
			} else if((eActionState==EActionState.eNPDrawing) ) {
				continueDrawing(e.getX(), e.getY());
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if(eActionState==EActionState.eNPDrawing) {
				finishDrawing(e.getX(), e.getY());
				eActionState=EActionState.eReady;
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	
	public void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentShape.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		currentShape.setOrigin(x, y);
		drawShape();
	}

	private void keepDrawing(int x, int y) {
		drawShape();
		currentShape.setPoint(x, y);
		drawShape();
	}
	
	private void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}
	
	private void finishDrawing(int x, int y) {
		this.shapeVector.add(currentShape);
	}
}
