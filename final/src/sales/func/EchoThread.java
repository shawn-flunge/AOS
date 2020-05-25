package sales.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class EchoThread extends Thread {
	Socket clientSock;
    Vector<Socket> vec;
    
    public EchoThread(Socket clientSock, Vector<Socket> vec){
    	this.clientSock = clientSock;
    	this.vec = vec;
    }

	public void run() {
    	BufferedReader br = null;
		String str = null;
    	try {
    		br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
    		while(true) {
    			str = br.readLine();
//    			System.out.println(str);
    			if(str == null) {
    				sleep(500);
    				vec.remove(clientSock);
    				break;
    			}
    			msgEcho(str);
    		}
    	}
    	catch(IOException ioe) {
    		return;
		} 
    	catch (InterruptedException ie) {
    		ie.printStackTrace();
		}
    	finally {
    		try {
               if(br != null) br.close();
               if(clientSock != null) clientSock.close();
            }
    		catch(IOException ioe){
    			ioe.printStackTrace();
            }
    	}
    }
    

    public void msgEcho(String str) {
        PrintWriter pw = null;
    	try {
    		for(Socket vecSock:vec) { 
    			if(vecSock != this.clientSock) { 
    				pw = new PrintWriter(vecSock.getOutputStream(), true);
    				
    				pw.println(str);
    				pw.flush();
    			}
    		}
    	}
    	catch (IOException ioe) {
    		pw.close();
    		ioe.printStackTrace();
		}
    }
}
