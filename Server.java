
import java.io.*;
import java.net.*;
public class Server {
	public static final int port = 28540;   //port number

	public static void main(String[] args) throws IOException {
        MySocket mySocket = new MySocket();
        mySocket.start();
	}
	
	private static class MySocket implements Runnable {
		
		ServerSocket sSocket = null;
		Thread sThread = null;
		boolean isRun = false;
		String client1Msg = " ";
		String client2Msg = " ";
		//IP address
		final String client1IP = "127.0.0.1";
		final String client2IP = "10.0.2.2";
		
		MySocket() throws IOException {
			sThread = new Thread(this);
			sSocket = new ServerSocket(port);
		}
		
		private void start() {
			isRun = true;
			sThread.start();
		}
		
		@SuppressWarnings("deprecation")
		private void stop() {
			isRun = false;
			sThread.stop();
		}

		@Override
		public void run() {
			while(isRun) {  //received message
				if(sSocket.isBound()) {
					try {
						Socket connection = sSocket.accept();
						String remote = connection.getRemoteSocketAddress().toString();
						System.out.println("Accept: " + remote);
						byte[] dataBuf = new byte[8192];
						InputStream input = connection.getInputStream();
						input.read(dataBuf);
						String data = new String(dataBuf,"UTF-8");
						PrintStream printer = new PrintStream(connection.getOutputStream());
						if(remote.contains(client1IP)) {
							printer.println("Message: "+client2Msg);
							client2Msg = " ";
							if(data.length() > 1) {
								client1Msg = data;
								System.out.println("Message received: "+data);
							}
						} else if (remote.contains(client2IP)) {
							printer.println("Message: "+client1Msg);
							client1Msg = " ";
							if(data.length() > 1) {
								client2Msg = data;
								System.out.println("Message received: "+data);
							}
						}
						connection.close();				
						} 
					catch(Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
		
}}
