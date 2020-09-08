import java.io.IOException;

public class Client1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		String ip = "127.0.0.1";
		Client.main(ip);
		Thread.sleep(15000);
		Client.main(ip);
	}
}