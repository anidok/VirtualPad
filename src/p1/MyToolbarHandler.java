package p1;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
					setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);	
				}
				
				else{					
					Font f = frame.font.deriveFont(frame.font.getStyle() &  ~Font.BOLD);
					// ^ operator is used to toggle style. i.e, BOLD/UNBOLD
					setJTextPaneFont(textPane, f, frame.fontcolor);
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
					setJTextPaneFont(textPane, f, frame.fontcolor);
					frame.font = f;
					button.setSelected(true);
					int r=137, g=152, b=198;
					Color color = new Color(r,g,b);
					button.setBackground(color);										
				}
				
				else{					
					Font f = frame.font.deriveFont(frame.font.getStyle() &  ~Font.ITALIC);
					// ^ operator is used to toggle style. i.e, ITALIC/NON-ITALIC
					setJTextPaneFont(textPane, f, frame.fontcolor);
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
				setJTextPaneFont(textPane, f, frame.fontcolor);
				frame.font = f;
				textPane.requestFocus();				
			}
			
			else if(jcombobox.getName().equals("Family")){
				Font f = new Font(item , frame.font.getStyle() , frame.font.getSize());
				setJTextPaneFont(textPane, f, frame.fontcolor);
				frame.font = f;
				textPane.requestFocus();
			}
			
		}
	}
	
	//Method to set the font of JTextPane
	private static void setJTextPaneFont(JTextPane textPane , Font font , Color c){
		MutableAttributeSet attrs = textPane.getInputAttributes();
		StyleConstants.setFontFamily(attrs , font.getFamily());
		StyleConstants.setFontSize(attrs , font.getSize());
		StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD)!= 0);
		StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC)!= 0);
		StyleConstants.setForeground(attrs, c);
		
		//setting attribute to current doc (Start index , length , attribute set , boolean)
		StyledDocument doc = textPane.getStyledDocument();
		int start = textPane.getSelectionStart();
		int end = textPane.getSelectionEnd();
		doc.setCharacterAttributes(start, (end-start+1), attrs, false);
		//doc.setCharacterAttributes(doc.getLength(), 1 , attrs, false);
			
	}
	

}
