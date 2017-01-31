package p1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class MyHandler implements ActionListener {
	MyNotepad frame;
	
	public MyHandler(MyNotepad frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e){
		
		//Checking if action event is generated from a MenuItem
		if(e.getSource() instanceof JMenuItem){			
			JMenuItem menuItem = (JMenuItem)e.getSource();
			
			//******************Handling New operation********************
			if(menuItem.getText().equals("New")){
				frame.textArea.setText("");
				frame.setTitle("Untitled - Virtualpad");
				frame.FILE_OPENED = false;
			}
			
			//******************Handling Open operation*******************
			if(menuItem.getText().equals("Open File...")){
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
					 String line = null;
					 String text="";
		             File file = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            
					try {
						 FileReader fr = new FileReader(file);
						 BufferedReader br = new BufferedReader(fr);
						 while((line = br.readLine()) != null){
							 text+= line + "\n";
						 }	
						 
						 br.close();
						 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		             frame.textArea.setText(text);
		             frame.setTitle(file.getName());
		             frame.FILE_OPENED = true;
		             frame.FilePath = file;
				 }			
			}
				
			//******************Handling Save As operation*******************
			if(menuItem.getText().equals("Save As...")){
				JFileChooser fc = new JFileChooser();
				 int returnVal = fc.showSaveDialog(frame);
				 String str = frame.textArea.getText();
				 String line = null;
				 Scanner sc = new Scanner(str);
				 
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					try {
						if(!file.exists())
							file.createNewFile();
							
							
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
						while(sc.hasNextLine()){
							line = sc.nextLine();
							bw.write(line);
							bw.newLine();
						}
						
						bw.close();
						sc.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				frame.textArea.setText("");
				frame.setTitle("Untitled - Virtualpad");
				frame.FILE_OPENED = false;
				//notePad.textArea.append(file.getName() + "\n");
				//notePad.textArea.append(file.getPath() + "\n");
			}
			
			
			//******************Handling Save operation*******************
			if(menuItem.getText().equals("Save")){
				String str = frame.textArea.getText();
			    String line = null;
				Scanner sc = new Scanner(str);
				
				try {
					FileWriter fw = new FileWriter(frame.FilePath);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("");
					while(sc.hasNextLine()){
						line = sc.nextLine();
						bw.write(line);
						bw.newLine();
					}
					sc.close();
					bw.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		}	
		
		/*checking if event is generated from a toolbar item
		Handling action events generated from a toolbar item in a separate class(MyToolbarHandler),
		thus passing the  object of current ActionEvent 'e' and frame object in the handler class' constructor.*/
		else if (e.getActionCommand().equals("A toolbar button")){
			MyToolbarHandler toolbarHandler = new MyToolbarHandler(frame, e);
			toolbarHandler.actionHandler();		//invoking the method which is going to handle the event.
		}
		
		else if(e.getActionCommand().equals("A toolbar combobox")){
			new MyToolbarHandler(frame , e).actionHandler();
		}
	}
	
	
}
