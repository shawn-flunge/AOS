package sales.func;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;


public class serverThread extends Thread{

	static final int PORT = 1369;
    ServerSocket server = null;
    Socket socket = null;
    Vector<Socket> vec = new Vector<Socket>();
    
    boolean available = false;
    
    public serverThread(boolean available) {
        try {
        	this.available = available;
        	server = new ServerSocket(PORT);
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        }
	}
	
	public void close() {
		try {
			available = false;
			if(!vec.isEmpty()) {
				for(Socket vecSock:vec) { // for( A : B ) �� B���� ���ʴ�� ��ü�� ������ A���ٰ� �ְڴٴ� �ǹ�(B���� �� �̻� ���� ��ü�� ���� ������)
					vecSock.close();
	    		}
			}
			vec.clear();
			server.close();
		} catch (IOException ioe) {
			return;
		}
		
	}

	@Override
	public void run() {
		while(available) {
			try {
				socket = server.accept();
				if(socket != null) {
					System.out.println(socket.getLocalAddress());
				}
				// ����� Ŭ���̾�Ʈ ������ ���Ϳ� ����
				vec.add(socket);
				// ������ Ŭ���̾�Ʈ�� ������ ����
				new EchoThread(socket, vec).start();
			} 
			catch (SocketException se) {
				return;
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
	}
}