package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanel.DrawingPanel;
import global.Constants.EMainFrame;
import menu.MenuBar;
import toolBar.ToolBar;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	//Component = 요녀석 안에서 new를 하면 된다.
	private MenuBar menuBar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;
	
	public MainFrame(){
		// attributes
		this.setSize(EMainFrame.w.getVal(),EMainFrame.h.getVal());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		// components
		this.menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		this.toolBar = new ToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	
		//Associations
		this.toolBar.associate(this.drawingPanel);
		this.menuBar.associate(this.drawingPanel);
		
	}
	
	
}
