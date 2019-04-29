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

	public void forward(String urlToForward) {
		System.out.println("Connected to " + urlToForward);
		try {
			URL objToUseToRedirect = new URL(urlToForward);
			HttpURLConnection connection = (HttpURLConnection) objToUseToRedirect.openConnection();

			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder streamWithResultOfConnection = new StringBuilder();

			String line = null;
			while ((line = input.readLine()) != null) {
				streamWithResultOfConnection.append(line);
			}
			// TODO mandar de volta o result do streamWithResultOfConnection para o proxy
			// System.out.println(streamWithResultOfConnection); //debug purpose
			input.close();

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// TODO: Choose from a list a random and open Public Proxy to use
	// inicializar para um proxy (check)
	// sendMessage(stream) (check)
}
// http://www.sohu.com/
