
import java.io.*;
import java.net.*;
public class Client {
	
	public static void main(String... args)  throws IOException {
		islem(args[0]);
	}
	
	public static void islem(String ip) throws UnknownHostException, IOException {
		 Socket socket = null;
         PrintWriter out = null;
         BufferedReader in = null;
         try {
              socket = new Socket(ip, Server.port);
              out = new PrintWriter(socket.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     
              System.out.print("Enter a message: ");
              BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
              out.println(data.readLine());
              //do {
            	  System.out.println("Answer from other client: " + in.readLine());
              //} while(in.ready());
              //closed
              out.close();
              in.close();
              data.close();
              socket.close();
         } catch (Exception e) { // when the port do not run
              System.out.println("Port Error!");
         }
     }
}
