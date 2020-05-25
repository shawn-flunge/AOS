package suply.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class mainView extends JFrame {

	public JTextArea txtA = new JTextArea();

    public JButton btnClient = new JButton("클라이언트");
    
    
    public JPanel p1 = new JPanel();
   
	public mainView() {
        add("Center", txtA);

   
    

        p1.add(btnClient);
        
        add("South", p1);

		setResizable(false);
		setTitle(" hi");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,0 , 300, 300);
		setVisible(true);
	}

	public JTextArea getTxtA() {
		return txtA;
	}

	public void setTxtA(JTextArea txtA) {
		this.txtA = txtA;
	}
	
	
}
