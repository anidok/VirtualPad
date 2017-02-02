package p1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
		menu.setMnemonic(KeyEvent.VK_F);
		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		//menu.setActionCommand("A MenuItem");
		
		MyHandler handler = new MyHandler(frame);
		//creating the handler for handling events
		
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open File...", KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Save", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuItem = new JMenuItem("Save As...");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuBar.add(menu);
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuItem = new JMenuItem("About VirtualPad");
		menuItem.addActionListener(handler);
		menu.add(menuItem);
		menuBar.add(menu);
		
		return menuBar;
	}
}
