package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class RequestHandler {

	public void request(Socket clientSocket) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String requestLine = input.readLine();

		String methodFromRequest = parseRequestMethod(requestLine);
		String uriFromRequest = parseRequestUri(requestLine);

		if (!(uriFromRequest.substring(0, 6).equals("http://"))) {
			uriFromRequest = "http://" + uriFromRequest;
		} else if (!(uriFromRequest.substring(0, 7).equals("https://"))) {
			uriFromRequest = "https://" + uriFromRequest;
		}

		if (methodFromRequest.equals("GET")) {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(uriFromRequest);
			client.execute(request);
		}
	}

	public String parseRequestMethod(String stringToExtract) {
		String[] arrString = stringToExtract.split(" ");
		return arrString[0];
	}

	public String parseRequestUri(String stringToExtract) {
		String[] arrString = stringToExtract.split(" ");
		return arrString[1];
	}
}
