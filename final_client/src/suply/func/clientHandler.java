package suply.func;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import suply.controller.ClientController;

public class ClientHandler implements ActionListener{

	
	ClientController cc;
	
	
	
	public ClientHandler(ClientController cc) {
		super();
		this.cc = cc;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		try {
            cc.socket = new Socket("127.0.0.1", 1369);
            new ClientThread(cc).start();
            System.out.println("dd");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

	
	
}
