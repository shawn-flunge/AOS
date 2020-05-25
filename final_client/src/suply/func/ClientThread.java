package suply.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

import suply.controller.ClientController;
import suply.view.mainView;

public class ClientThread extends Thread {
	ClientController cc;
	
	public ClientThread(ClientController cc) {
		this.cc = cc;
	}
	
	public void run() {
		BufferedReader br = null;
		String str = null;
		try {
			// 서버로부터 전송받은 문자열을 담는 스트림 객체 생성
			br = new BufferedReader(new InputStreamReader(cc.getSocket().getInputStream()));
			
			while(true) {
				// 받은 문자열을 한 줄씩 읽음
				str = br.readLine();		
				if(str == null) {
					break;
				}
				else {
					cc.mv.txtA.append(str + "\n");
				}

				
			}
		}
		catch(IOException ioe) {
			return;
		}
		finally {
			try {
				if(br != null) br.close();
                if(cc.getSocket() != null) cc.getSocket().close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}