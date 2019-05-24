package pt.compta.http.proxy.traineeship;

import java.io.OutputStreamWriter;
import java.net.Socket;

public class RequestHandler implements Runnable {

	private final Socket clientSocket;
	private Shovel shovelInput = null;
	private Shovel shovelOutput = null;

	public RequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		OutputStreamWriter writer = null;
		try {
			int publicPortNumber = 8080;
			String hostName = "157.230.212.164";
			PublicProxy publicProxy = new PublicProxy(hostName, publicPortNumber);

			shovelInput = new Shovel(this.clientSocket.getInputStream(), publicProxy.getOutputStream());
			shovelOutput = new Shovel(publicProxy.getInputStream(), this.clientSocket.getOutputStream());

			new Thread(shovelInput).start();
			new Thread(shovelOutput).start();

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
//TODO: Allow https requests