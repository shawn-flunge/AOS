package sales.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import sales.controller.salesController;





public class clientThread extends Thread {
	salesController sc;
	
	public clientThread(salesController sc) {
		this.sc = sc;
	}
	
	public void run() {
		BufferedReader br = null;
		String str = null;
		try {
	
			br = new BufferedReader(new InputStreamReader(sc.getSocket().getInputStream()));
			
			while(true) {
				str = br.readLine();
				if(str == null) break;
			}
		}
		catch(IOException ioe) {
			return;
		}
		finally {
			try {
				if(br != null) br.close();
                if(sc.getSocket() != null) sc.getSocket().close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}