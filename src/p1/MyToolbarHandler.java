package p1;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class MyToolbarHandler {
	MyNotepad frame;
	ActionEvent e;
	JTextPane textPane;
	
	public MyToolbarHandler(MyNotepad frame , ActionEvent e){
		this.frame = frame;
		this.e = e;
		this.textPane = frame.textPane;
	}
	
	public void actionHandler(){
		if(e.getSource() instanceof JButton){
			JButton button = (JButton)e.getSource();
			
			if(button.getText().equals("B")){				
				if(!button.isSelected()){
					Font f = frame.font.deriveFont(frame.font.getStyle() | Font.BOLD);										
					MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);	
					System.out.println("bold toolbar");
				}
				
				else{					
					Font f = frame.font.deriveFont(frame.font.getStyle() &  ~Font.BOLD);
					// ^ operator is used to toggle style. i.e, BOLD/UNBOLD
					MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(false);
					int r=249, g= 247, b=244;
					Color color = new Color(r,g,b);
					button.setBackground(color);
										
				}
				//transfer focus back to text area after button is pressed.
				textPane.requestFocus();
			}
			
			
			else if(button.getText().equals("I")){				
				if(!button.isSelected()){
					Font f = frame.font.deriveFont(frame.font.getStyle() | Font.ITALIC);
					// ^ operator is used to toggle style. i.e, ITALIC/NON-ITALIC
					MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);										
				}
				
				else{					
					Font f = frame.font.deriveFont(frame.font.getStyle() &  ~Font.ITALIC);
					// ^ operator is used to toggle style. i.e, ITALIC/NON-ITALIC
					MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(false);
					int r=249, g= 247, b=244;
					Color color = new Color(r,g,b);
					button.setBackground(color);
					
				}
				//transfer focus back to text area after button is pressed.
				textPane.requestFocus();
				
			}
		}
		
		//handling combobox items of toolbar
		if(e.getSource() instanceof JComboBox){
			@SuppressWarnings("rawtypes")
			JComboBox jcombobox = (JComboBox)e.getSource();
			String item = (String)jcombobox.getSelectedItem();
			
			if(jcombobox.getName().equals("Size")){
				int size = Integer.parseInt(item);
				Font f = frame.font.deriveFont((float)size);
				MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
				frame.font = f;
				textPane.requestFocus();				
			}
			
			else if(jcombobox.getName().equals("Family")){
				Font f = new Font(item , frame.font.getStyle() , frame.font.getSize());
				MyNotepad.setJTextPaneFont(textPane, f, frame.fontcolor);
				frame.font = f;
				textPane.requestFocus();
			}
			
		}
	}

}
