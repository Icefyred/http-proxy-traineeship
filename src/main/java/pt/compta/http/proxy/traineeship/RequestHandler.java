package pt.compta.http.proxy.traineeship;

import java.net.Socket;

public class RequestHandler implements Runnable {

	private final Socket clientSocket;
	private Shovel shovelInput = null;
	private Shovel shovelOutput = null;

	public RequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private String[] splitRequestLine(String stringToExtract) {
		return stringToExtract.split(" ");
	}

	private String parseRequestUri(String stringToExtract) {
		String[] arrString = splitRequestLine(stringToExtract);
		return arrString[1];
	}

	@Override
	public void run() {
		try {
			int publicPortNumber = 8080;
			String hostName = "188.166.54.103";
			PublicProxy publicProxy = new PublicProxy(hostName, publicPortNumber);

			shovelInput = new Shovel(clientSocket.getInputStream(), publicProxy.getOutputStream());
			shovelOutput = new Shovel(publicProxy.getInputStream(), clientSocket.getOutputStream());

			new Thread(shovelInput).start();
			new Thread(shovelOutput).start();

			while (true) {

			}

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}