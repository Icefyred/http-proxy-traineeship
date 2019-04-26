package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler {

	public void request(Socket clientSocket) throws IOException {
		int publicPortNumber = 8081;
		BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String requestLine = input.readLine();

		String uriFromRequest = parseRequestUri(requestLine);

		PublicProxy publicProxy = new PublicProxy(publicPortNumber);
		publicProxy.forward(uriFromRequest);

		/*
		 * //abrir socket para proxy public //socket para host e port //após seja bem
		 * sucedido pedir um request
		 * 
		 */
	}

	private String[] splitRequestLine(String stringToExtract) {
		return stringToExtract.split(" ");
	}

	public String parseRequestUri(String stringToExtract) {
		String[] arrString = splitRequestLine(stringToExtract);
		return arrString[1];
	}
}
