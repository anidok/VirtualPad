package p1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

public class MyHandler implements ActionListener {
	MyNotepad frame;
	JTextPane textPane;
	final String aboutText=
			"<html><big>VirtualPad</big><hr>"
			+"<p align=left>IDE used - Eclipse - Mars.2 Release (4.5.2)"
			+"<p align=left>Environment - JavaSE-1.8"
			+"<hr><hr><br><p align=left><bold>VirtualPad</bold> is a formatted text editor which supports both<br>"
			+"RTF and plain text files. You can make changes using the<br>"
			+"tools provided and impose the changes to the current file<br>"
			+"or create a new one."
			+"<hr><p align=right><big>Created by - Aniket</big><hr><html>";
	
	public MyHandler(MyNotepad frame){
		this.frame = frame;
		this.textPane = frame.textPane;
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		//Checking if action event is generated from a MenuItem
		if(e.getSource() instanceof JMenuItem)
		{
			JMenuItem menuItem = (JMenuItem)e.getSource();		

			if(menuItem.getText().equals("New"))
			{
				createNewFile();
			}			
	
			else if(menuItem.getText().equals("Open File..."))
			{
				openFile();		                          
		    }				
	
			else if(menuItem.getText().equals("Save As..."))
			 {
				saveAs();			 
			 }			
			
			else if(menuItem.getText().equals("Save"))
			 {
				 saveFile();				 	
			 }
			
			else if(menuItem.getText().equals("Exit"))
			 {
				 System.exit(0);
			 }
		
			else if(menuItem.getText().equals("About VirtualPad"))
			{
				JOptionPane.showMessageDialog(frame ,aboutText,"About VirtualPad",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("New"))
			createNewFile();
		else if(e.getActionCommand().equals("Open"))
			openFile();
		else if(e.getActionCommand().equals("Save"))
			saveFile();
		
		/*checking if event is generated from a toolbar item
		Handling action events generated from a toolbar item in a separate class(MyToolbarHandler),
		thus passing the  object of current ActionEvent 'e' and frame object in the handler class' constructor.*/	
		else if (e.getActionCommand().equals("Bold") | e.getActionCommand().equals("Italic") | e.getActionCommand().equals("Color") | e.getActionCommand().equals("Underline"))
		{		
			MyToolbarHandler toolbarHandler = new MyToolbarHandler(frame, e);
			toolbarHandler.actionHandler();		//invoking the method which is going to handle the event.
		}		
		
		else if(e.getActionCommand().equals("A toolbar combobox"))
		{
			new MyToolbarHandler(frame , e).actionHandler();
		}
	}
	
	//-----------------------------------Methods Bodies--------------------------------
	
	private void savePlainText(File file)
	{
		String str = null;
		 String line = null;
		
		try {
			str = textPane.getDocument().getText(0, textPane.getDocument().getLength());
		}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		 Scanner sc = new Scanner(str);
		 try 
		 {
			 if(!file.exists())
				 file.createNewFile();
										
			 FileWriter fw = new FileWriter(file);
			 BufferedWriter bw = new BufferedWriter(fw);
			 while(sc.hasNextLine())
			 {
				 line = sc.nextLine();
				 bw.write(line);
				 bw.newLine();
			 }
			
			 bw.close();
			 sc.close();
		}
		 catch (IOException e1) 
		 {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 }
	}
	
	private void saveRTF(File file)
	{
		try {
			if(!file.exists())
				file.createNewFile();
			Document doc = textPane.getDocument();
			EditorKit rtf = textPane.getEditorKit();
			FileOutputStream fo = new FileOutputStream( file.getAbsolutePath() );
			rtf.write(fo , doc , 0 , doc.getLength()); 
			
		}
		catch (IOException | BadLocationException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	//------------------------------New File Method---------------------------
	private void  createNewFile(){
		frame.textPane.setText("");
		frame.setTitle("Untitled - Virtualpad");
		frame.FILE_OPENED = false;
		frame.OpenedFilePath = null;	
	}
	
	//------------------------------Open File Method---------------------------
	private void openFile(){
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new filter1());
		fc.addChoosableFileFilter(new filter2());
		fc.addChoosableFileFilter(new filter3());
		int returnVal = fc.showOpenDialog(frame);
		
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
			 File file = fc.getSelectedFile();
			 //This is where a real application would open the file.
			 fc.addChoosableFileFilter(new filter1());
			 fc.addChoosableFileFilter(new filter2());
			 fc.addChoosableFileFilter(new filter3());
			 textPane.setText("");
			 
             
              String page = "file:///" + file.getAbsolutePath ( );
	          try 
	          {
	        	  textPane.setPage(page);
	          } 
	          catch (IOException e1) 
	          {
	        	  // TODO Auto-generated catch block
	        	  e1.printStackTrace();
	          }
	          
	          frame.setTitle(file.getName());
	          frame.OpenedFilePath = file;	            	
	           	//storing path of opened file
		 } 		
	}
	
	//------------------------------SaveAs File Method---------------------------
	private void saveAs(){
		boolean FILE_SAVED = false;		//used to store status of file saving.
		 do
		 {
			 JFileChooser fc = new JFileChooser();
			 fc.addChoosableFileFilter(new filter1());
			 fc.addChoosableFileFilter(new filter2());
		 	 fc.addChoosableFileFilter(new filter3());	 
			 int returnVal = fc.showSaveDialog(frame);				 
		 
			 if (returnVal == JFileChooser.APPROVE_OPTION)		//if clicked on save button					 
			 {
				 File file = fc.getSelectedFile();
				 fc.addChoosableFileFilter(new filter1());
				 fc.addChoosableFileFilter(new filter2());
				 fc.addChoosableFileFilter(new filter3());
				 
				 if(frame.getTitle().endsWith(".txt") | frame.getTitle().startsWith("Untitled") | frame.getTitle().endsWith(".java"))
					//checking source file extension
				 {
					savePlainText(file);		//invoking method
					FILE_SAVED = true;
					frame.setTitle(file.getName());
					frame.OpenedFilePath = file;
				 }
				 
				 else if(frame.getTitle().endsWith(".rtf"))		//checking source file extension 
				 {						 			 
					 if(file.getName().endsWith(".rtf"))		//checking destination file extension
					 {
						 saveRTF(file);		//invoking
						 FILE_SAVED = true;
						 frame.setTitle(file.getName());		
						 frame.OpenedFilePath = file;
					 } 
						 
					 else if(file.getName().endsWith(".txt"))	//rtf to plain text conversion
					 {
						 String msg = "You are about to save the document in a Text-Only format, "
								      +"which will\n remove all formatting. Are you sure you want to do this?"
								      +"\n\nTo save in other format, click No.";
						 int result = JOptionPane.showConfirmDialog(fc, msg, "VirtualPad", JOptionPane.YES_NO_OPTION );
						 
						 if(result == JOptionPane.YES_OPTION)
						 {
							  savePlainText(file);
							  FILE_SAVED = true;
							  frame.setTitle(file.getName());
							  frame.OpenedFilePath = file;
						 }								 
							 
					 }						
					
				 }					
				 
			 }	
			 else		//if clicked on cancel
				 break;
		 }while(!FILE_SAVED);
	}
	
	//------------------------------Save File Method---------------------------
	private void saveFile(){
		if(frame.getTitle().startsWith("Untitled"))
		 {
			 boolean FILE_SAVED = false;
			 do
			 {
				 JFileChooser fc = new JFileChooser();
				 fc.addChoosableFileFilter(new filter1());
				 fc.addChoosableFileFilter(new filter2());
				 fc.addChoosableFileFilter(new filter3());
				 int returnVal = fc.showSaveDialog(frame);
				 
				 if (returnVal == JFileChooser.APPROVE_OPTION)
				 {
					 File file = fc.getSelectedFile();
					 fc.addChoosableFileFilter(new filter1());
					 fc.addChoosableFileFilter(new filter2());
					 fc.addChoosableFileFilter(new filter3());
					 
					 if(file.getName().endsWith(".rtf"))	//if file is to be saved in rtf.
					 {
						 textPane.setEditorKit(new RTFEditorKit());
						 saveRTF(file);
						 FILE_SAVED = true;
						 frame.setTitle(file.getName());
						 frame.OpenedFilePath = file;
					 }
					 
					 else if(file.getName().endsWith(".txt") | file.getName().endsWith(".java"))
					 {
						 savePlainText(file);
						 FILE_SAVED = true;
						 frame.setTitle(file.getName());
						 frame.OpenedFilePath = file;	 
					 }
						 
				 }
				 
				 else
					 break;
				 
			 }while(!FILE_SAVED);
			 
		 }
		 
		 else if(frame.getTitle().endsWith(".txt") | frame.getTitle().endsWith(".java"))
			 savePlainText(frame.OpenedFilePath);
		 
		 else if(frame.getTitle().endsWith(".rtf"))
			 saveRTF(frame.OpenedFilePath);	
	}
	
	
	//-------------------------Filters to file choosers----------------------
	 class filter1 extends FileFilter {
		public boolean accept(File fileobj){
			String extension = "";
			if(fileobj.getPath().lastIndexOf('.')>0)
				extension = fileobj.getPath().substring(fileobj.getPath().lastIndexOf('.') + 1).toLowerCase();
			if(extension != "")
				return extension.equals("rtf");
			else
				return fileobj.isDirectory();
		}
		public String getDescription(){
			return "RTF Text Format (RTF) (*.rtf)";
		}		
	}	

	 class filter2 extends FileFilter {
		public boolean accept(File fileobj){
			String extension = "";
			if(fileobj.getPath().lastIndexOf('.')>0)
				extension = fileobj.getPath().substring(fileobj.getPath().lastIndexOf('.') + 1).toLowerCase();
			if(extension != "")
				return extension.equals("txt");
			else
				return fileobj.isDirectory();
		}
		public String getDescription(){
			return "Text Document (*.txt)";
		}		
	}
	 
	 class filter3 extends FileFilter {
		public boolean accept(File fileobj){
			String extension = "";
			if(fileobj.getPath().lastIndexOf('.')>0)
				extension = fileobj.getPath().substring(fileobj.getPath().lastIndexOf('.') + 1).toLowerCase();
			if(extension != "")
				return extension.equals("java");
			else
				return fileobj.isDirectory();
		}
		public String getDescription(){
			return "Java Document (*.java)";
		}		
	}
	 
}
	

