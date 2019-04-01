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
		this.eActionState = EActionState.eReady;
		this.setBackground(Color.WHITE);
		mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		currentTool = EToolBar.rect.getShape();
	}
	
	private enum EActionState{eReady, eCMCDrawing, ePDRDrawing};//레디, 폴리곤(nPointDrawing) , 나머지(2PointDrawing)
	EActionState eActionState;
	
	private class MouseHandler implements MouseListener, MouseMotionListener {//함수 호출 만 하게 하라.
		public void mousePressed(MouseEvent e) {
			if(eActionState==EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState=EActionState.ePDRDrawing;
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(eActionState==EActionState.ePDRDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		public void mouseReleased(MouseEvent e) {
			if(eActionState==EActionState.ePDRDrawing) {
				initDrawing(e.getX(), e.getY());
				eActionState=EActionState.eReady;
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
				eActionState=EActionState.eCMCDrawing;
			}else if(eActionState==EActionState.eCMCDrawing){
				finishDrawing(e.getX(), e.getY());
				eActionState=EActionState.eReady;
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			if(eActionState==EActionState.eCMCDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
	}
	
	public void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		currentTool.setOrigin(x, y);
		drawShape();
	}

	private void keepDrawing(int x, int y) {
		drawShape();
		currentTool.setPoint(x, y);
		drawShape();
	}
	
	private void continueDrawing(int x, int y) {
		currentTool.addPoint(x, y);
	}
	
	private void finishDrawing(int x, int y) {
//		drawShape();
//		currentTool.setPoint(x, y);
//		drawShape();
	}
}
