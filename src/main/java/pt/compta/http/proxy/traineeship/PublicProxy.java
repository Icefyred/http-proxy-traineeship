package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;

public class PublicProxy {
	private int publicProxyPort;
	private ServerSocket serverSocket;

	public PublicProxy(int portNumber) {
		this.publicProxyPort = portNumber;
		try {
			serverSocket = new ServerSocket(publicProxyPort);
			System.out.println("Public Proxy started on port..." + publicProxyPort);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void forward(String urlToForward) {
		System.out.println("Connected to " + urlToForward);
		try {
			URL obj = new URL(urlToForward);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder stringBuilder = new StringBuilder();

			String line = null;
			while ((line = input.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			System.out.println(stringBuilder);
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
