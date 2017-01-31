package p1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class MyNotepad extends JFrame{
	
	
	protected JTextPane textPane;		//can't keep private as it is going to get referenced in non-sub classes.
	protected File FilePath;		//used to store path of opened file
	protected boolean FILE_OPENED = false;
	protected Font font = (new Font("Serif" , Font.PLAIN  , 14));
	protected Color fontcolor = Color.BLACK;
	
	public MyNotepad(String title){
		super(title);
		Container contentPane = getContentPane();
		//setLayout(new BorderLayout());
		textPane = new JTextPane();
		textPane.setMargin(new Insets(5,5,5,5));
		textPane.setEditable(true);
		JScrollPane scrollPane = new JScrollPane(textPane); 
		//add(scrollPane, BorderLayout.CENTER);
		contentPane.add(scrollPane , BorderLayout.CENTER);
		
		//creating Toolbar
		MyToolbarCreator toolbarCreator = new MyToolbarCreator(this);
		JToolBar jtoolbar = toolbarCreator.getToolBar();
		contentPane.add(jtoolbar , BorderLayout.PAGE_START);
	}
	
	
		
	private static void createGUI(){
		MyNotepad frame = new MyNotepad("Untitled - Virtualpad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyMenu myMenu = new MyMenu(frame);		//passing notePad to MyMenu which in turn will pass the reference to MyHandler
		frame.setJMenuBar(myMenu.createMenuBar());
		frame.setSize(600,480);
		frame.setLocation(150, 50);
		
		//************Setting Focus to text area when window is opened*********
		frame.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        frame.textPane.requestFocus();
		    }
		});
		frame.setVisible(true);
	}
	
	//Method to set the font of JTextPane
	public static void setJTextPaneFont(JTextPane textPane , Font font , Color c){
		MutableAttributeSet attrs = textPane.getInputAttributes();
		StyleConstants.setFontFamily(attrs , font.getFamily());
		StyleConstants.setFontSize(attrs , font.getSize());
		StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD)!= 0);
		StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC)!= 0);
		StyleConstants.setForeground(attrs, c);
		
		//setting attributs to current doc 
		StyledDocument doc = textPane.getStyledDocument();
		doc.setCharacterAttributes(doc.getLength(), 5, attrs, false);
		
	}
	
	public static void main(String[] args){
		//this is a new line in git
		//another line in git
		createGUI();
	}
}
