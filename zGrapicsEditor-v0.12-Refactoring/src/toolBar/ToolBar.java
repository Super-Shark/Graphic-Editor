package toolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import drawingPanel.DrawingPanel;
import global.Constants.EToolBar;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar{

	Vector<JButton> btns;
	DrawingPanel drawingPanel;
	
	public void associate(DrawingPanel dp) {this.drawingPanel=dp;}

	public ToolBar() {
		this.btns = new Vector<JButton>();
		ActionHandler actionHandler = new ActionHandler();
		for(EToolBar e : EToolBar.values()) {//iterator라고 합니다.
			JButton btn = new JButton(e.getVal());
			btn.addActionListener(actionHandler);
			btn.setActionCommand(e.name());
			btns.add(btn); this.add(btn);
		}
	}

	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(e.getActionCommand()));
		}
	}
}
