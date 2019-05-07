package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class PublicProxy {
	private int publicProxyPort;
	private Socket clientSocket;

	public PublicProxy(String hostName, int portNumber) {
		this.publicProxyPort = portNumber;
		try {
			clientSocket = new Socket(hostName, publicProxyPort);
			System.out.println("Public Proxy started on port " + publicProxyPort);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void forwardHttpRequestToPublicProxy(String urlToForward) {
		System.out.println("Connected to " + urlToForward);
		try {
			URL objToUseToRedirect = new URL(urlToForward);
			HttpURLConnection connection = (HttpURLConnection) objToUseToRedirect.openConnection();

			BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder streamWithResultFromConnection = new StringBuilder();
			String auxStream = null;

			while ((auxStream = inputStream.readLine()) != null) {
				streamWithResultFromConnection.append(auxStream);
			}
			inputStream.close();

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
// TODO: mandar de volta o result do streamWithResultOfConnection para o proxy
// TODO: Choose from a list, a random and open Public Proxy to use
// http://www.sohu.com/
