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

		uriFromRequest = prependHttpProtocolIfNeeded(uriFromRequest);

		if (methodFromRequest.equals("GET")) {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(uriFromRequest);
			client.execute(request);
		}
	}

	private String prependHttpProtocolIfNeeded(String uriFromRequest) {
		String auxForHttpsProtocol = uriFromRequest.substring(0, 5);
		String auxForHttpProtocol = uriFromRequest.substring(0, 4);

		if (!doesItContainHttpProtocol(uriFromRequest, auxForHttpsProtocol, auxForHttpProtocol)) {
			uriFromRequest = "http://" + uriFromRequest;
		}
		return uriFromRequest;
	}

	private boolean doesItContainHttpProtocol(String uri, String auxForHttpsProtocol, String auxForHttpProtocol) {
		if (!auxForHttpsProtocol.equals("https") && !auxForHttpProtocol.equals("http")) {
			return false;
		}
		return true;
	}

	public String parseRequestMethod(String stringToExtract) {
		String[] arrString = splitRequestLine(stringToExtract);
		return arrString[0];
	}

	private String[] splitRequestLine(String stringToExtract) {
		String[] arrString = stringToExtract.split(" ");
		return arrString;
	}

	public String parseRequestUri(String stringToExtract) {
		String[] arrString = splitRequestLine(stringToExtract);
		return arrString[1];
	}
}
