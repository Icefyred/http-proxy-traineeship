package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

	public InputStream getInputStream() {
		try {
			return clientSocket.getInputStream();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public OutputStream getOutputStream() {
		try {
			return clientSocket.getOutputStream();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public void forwardHttpRequestToPublicProxy(String urlToForward, OutputStream clientStream) {
		System.out.println("Connected to " + urlToForward);
		try {
			URL objToUseToRedirect = new URL(urlToForward);

			HttpURLConnection connection = (HttpURLConnection) objToUseToRedirect.openConnection();

			BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			inputStream.close();

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
// TODO: Choose from a list, a random and open Public Proxy to use
// http://www.sohu.com/
