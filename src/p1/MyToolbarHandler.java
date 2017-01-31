package p1;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class MyToolbarHandler {
	MyNotepad frame;
	ActionEvent e;
	JTextArea textArea;
	
	public MyToolbarHandler(MyNotepad frame , ActionEvent e){
		this.frame = frame;
		this.e = e;
		textArea = frame.textArea;
	}
	
	public void actionHandler(){
		if(e.getSource() instanceof JButton){
			JButton button = (JButton)e.getSource();
			
			if(button.getText().equals("B")){				
				if(!button.isSelected()){
					Font f = frame.font.deriveFont(frame.font.getStyle() ^ Font.BOLD);
					// ^ operator is used to toggle style. i.e, BOLD/UNBOLD
					textArea.setFont(f);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);										
				}
				
				else{
					Font f = frame.font.deriveFont(frame.font.getStyle() ^ Font.BOLD);
					textArea.setFont(f);
					frame.font = f;
					button.setSelected(false);
					int r=249, g= 247, b=244;
					Color color = new Color(r,g,b);
					button.setBackground(color);
					
				}
				//transfer focus back to text area after button is pressed.
				textArea.requestFocus();
			}
			
			
			else if(button.getText().equals("I")){
				if(!button.isSelected()){
					Font f = frame.font.deriveFont(frame.font.getStyle() ^ Font.ITALIC);
					// ^ operator is used to toggle style. i.e, ITALIC/NON-ITALIC
					textArea.setFont(f);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);										
				}
				
				else{
					Font f = frame.font.deriveFont(frame.font.getStyle() ^ Font.ITALIC);
					textArea.setFont(f);
					frame.font = f;
					button.setSelected(false);
					int r=249, g= 247, b=244;
					Color color = new Color(r,g,b);
					button.setBackground(color);
					
				}
				//transfer focus back to text area after button is pressed.
				textArea.requestFocus();
				
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
				textArea.setFont(f);
				frame.font = f;
				textArea.requestFocus();				
			}
			
			else if(jcombobox.getName().equals("Family")){
				Font f = new Font(item , frame.font.getStyle() , frame.font.getSize());
				textArea.setFont(f);
				frame.font = f;
				textArea.requestFocus();
			}
			
		}
	}

}
