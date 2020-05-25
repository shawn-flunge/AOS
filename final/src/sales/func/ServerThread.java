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
				for(Socket vecSock:vec) { // for( A : B ) → B에서 차례대로 객체를 꺼내서 A에다가 넣겠다는 의미(B에서 더 이상 꺼낼 객체가 없을 때까지)
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
				// 연결된 클라이언트 소켓을 벡터에 삽입
				vec.add(socket);
				// 접속한 클라이언트의 스레드 실행
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