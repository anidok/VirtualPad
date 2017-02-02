package p1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

public class MyToolbarCreator {
	MyNotepad frame;
	
	public MyToolbarCreator(MyNotepad frame){
		this.frame = frame;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JToolBar getToolBar(){
		JToolBar jtoolbar = new JToolBar();
		Dimension d = jtoolbar.getPreferredSize();
		jtoolbar.setMinimumSize(d);
		jtoolbar.setMaximumSize(d);
		jtoolbar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY , 1));		

		//Creating Comboboxes
		String [] size = {"8" , "9" , "10" , "11"  ,"12" , "14" , "16" , "18" ,
				"20" , "22" , "24" , "26" , "28" , "36" , "48", "72"};		
		JComboBox c1 = new JComboBox();
		BufferedReader br = null;
		String family = null;
		try{			
			br = new BufferedReader(new FileReader("font.txt"));
			while((family = br.readLine())!= null){
				c1.addItem(family);
			}
			br.close();
		
		} catch(IOException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		d = c1.getPreferredSize();
		d.height += 5;
		c1.setMinimumSize(d);
		c1.setMaximumSize(d);
		JComboBox c2 = new JComboBox(size);
		d = c2.getPreferredSize();
		c2.setMinimumSize(d);
		d.height += 6;
		c2.setMaximumSize(d);
		
		MyHandler handler = new MyHandler(frame);
		
		c1.addActionListener(handler);
		c2.addActionListener(handler);
		c1.setActionCommand("A toolbar combobox");
		c2.setActionCommand("A toolbar combobox");
		c1.setName("Family");
		c2.setName("Size");
		//setting name to combobox for later identification purpose
		c1.setMaximumRowCount(16);
		c2.setMaximumRowCount(16);
		c1.setSelectedItem("Serif");
		c2.setSelectedItem("14");
		
		//adding an invisible strut(object)of specified width at the start of toolbar so as to shift the components by right.
		jtoolbar.add(Box.createHorizontalStrut(70));
		jtoolbar.add(c1);
		jtoolbar.addSeparator(new Dimension(1,1));		
		jtoolbar.add(c2);
		jtoolbar.addSeparator(new Dimension(1,1));
		
		//Creating buttons
		JButton b1 = new JButton(new ImageIcon("images\\bold.gif"));
		JButton b2 = new JButton(new ImageIcon("images\\italic.gif"));
		JButton b3 = new JButton(new ImageIcon("images\\color.png"));
		b1.setSelected(false);
		b2.setSelected(false);
		b1.setActionCommand("Bold");
		b2.setActionCommand("Italic");
		b3.setActionCommand("Color");
		b1.addActionListener(handler);
		b2.addActionListener(handler);
		b3.addActionListener(handler);
		jtoolbar.add(b1);
		jtoolbar.addSeparator(new Dimension(1,1));		
		jtoolbar.add(b2);
		jtoolbar.addSeparator(new Dimension(1,1));		
		jtoolbar.add(b3);
		b1.setMnemonic(KeyEvent.VK_B);
		b2.setMnemonic(KeyEvent.VK_I);
				
				
		jtoolbar.setFloatable(false);
		jtoolbar.setRollover(true);

		return jtoolbar;
	}

}
