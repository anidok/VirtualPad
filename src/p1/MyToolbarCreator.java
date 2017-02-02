package p1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
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
		//jtoolbar.setBackground(Color.LIGHT_GRAY);
		jtoolbar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY , 1));
		
		JButton toolbarButton = new JButton("B");
		toolbarButton.setSelected(false);
		int r=249, g= 247, b=244;
		Color color = new Color(r,g,b);
		toolbarButton.setActionCommand("A toolbar button");
		//button.setSize(new Dimension(d.width , d.height+6));
		MyHandler handler = new MyHandler(frame);
		toolbarButton.addActionListener(handler);
		jtoolbar.add(toolbarButton);
		jtoolbar.addSeparator(new Dimension(-1,1));
		toolbarButton = new JButton("I");
		toolbarButton.setSelected(false);
		toolbarButton.setBackground(color);
		toolbarButton.setActionCommand("A toolbar button");
		toolbarButton.setFont(new Font("Serif" , Font.PLAIN , 12));
		toolbarButton.addActionListener(handler);
		jtoolbar.add(toolbarButton);
		jtoolbar.addSeparator(new Dimension(-1,1));
		
		String [] size = {"8" , "9" , "10" , "11"  ,"12" , "14" , "16" , "18" ,
							"20" , "22" , "24" , "26" , "28" , "36" , "48", "72"};				
		JComboBox jcombobox = new JComboBox(size);
		//Previously jcombobox was taking whole lot of space of the toolbar, thus filling up the entire width
	    d = jcombobox.getPreferredSize();
		jcombobox.setMinimumSize(d);
		jcombobox.setMaximumSize(new Dimension(d.width , d.height+6));
		jcombobox.addActionListener(handler);
		jcombobox.setActionCommand("A toolbar combobox");
		jcombobox.setName("Size");
		//setting name to combobox for later identification purpose
		jcombobox.setMaximumRowCount(16);
		jcombobox.setSelectedItem("14");
		jtoolbar.add(jcombobox);
		
		jcombobox = new JComboBox();
		BufferedReader br = null;
		String family = null;
		try{			
			br = new BufferedReader(new FileReader("font.txt"));
			while((family = br.readLine())!= null){
				jcombobox.addItem(family);
			}
			br.close();
		
		} catch(IOException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		d = jcombobox.getPreferredSize();
		jcombobox.setMinimumSize(d);
		jcombobox.setMaximumSize(d);
		jcombobox.addActionListener(handler);
		jcombobox.setActionCommand("A toolbar combobox");
		jcombobox.setName("Family");
		//setting name to combobox for later identification purpose
		jcombobox.setMaximumRowCount(16);
		jcombobox.setSelectedItem("Serif");
		jtoolbar.add(jcombobox);
		
		
				
		jtoolbar.setFloatable(false);
		jtoolbar.setRollover(true);
		return jtoolbar;
	}

}
