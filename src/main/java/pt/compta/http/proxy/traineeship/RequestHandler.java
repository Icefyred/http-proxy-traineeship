package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler implements Runnable {

	private final Socket clientSocket;

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
			int publicPortNumber = 3128;
			String hostName = "proxy.compta.pt";

			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String requestLine = input.readLine();

			String uriFromRequest = parseRequestUri(requestLine);

			PublicProxy publicProxy = new PublicProxy(hostName, publicPortNumber);
			publicProxy.forwardHttpRequestToPublicProxy(uriFromRequest);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}