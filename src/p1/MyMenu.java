package p1;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenu {
	protected MyNotepad frame;
	
	public MyMenu(MyNotepad frame){
		this.frame = frame;			//obtained the reference for Original MyNotepad object which is handling Events.
	}

	public JMenuBar createMenuBar(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuItem = new JMenuItem("New");
		//menu.setActionCommand("A MenuItem");
		
		MyHandler handler = new MyHandler(frame);
		//creating the handler for handling events
		
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open File...");
		//menu.setActionCommand("A MenuItem");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Save");
		//menu.setActionCommand("A MenuItem");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuItem = new JMenuItem("Save As...");
		//menu.setActionCommand("A MenuItem");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuBar.add(menu);
		
		return menuBar;
	}
}
