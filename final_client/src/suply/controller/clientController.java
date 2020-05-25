package suply.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import suply.view.mainView;
import suply.func.*;


public class ClientController {

	public mainView mv;
    public Socket socket = null;

    Vector<Socket> vec = new Vector<Socket>();
    ClientHandler cHandler;

	
	public ClientController() {
		mv = new mainView();
		cHandler = new ClientHandler(this);
		mv.btnClient.addActionListener(cHandler);
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		new ClientController();
	}

	public mainView getMv() {
		return mv;
	}

	public void setMv(mainView mv) {
		this.mv = mv;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Vector<Socket> getVec() {
		return vec;
	}

	public void setVec(Vector<Socket> vec) {
		this.vec = vec;
	}
	
	
}
