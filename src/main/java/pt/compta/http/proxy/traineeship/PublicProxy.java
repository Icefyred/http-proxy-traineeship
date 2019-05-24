package pt.compta.http.proxy.traineeship;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PublicProxy {
	private int publicProxyPort;
	// private SSLSocket clientSSLSocket;
	private Socket clientSocket;

	public PublicProxy(String hostName, int portNumber) {
		this.publicProxyPort = portNumber;

		/*
		 * try { SSLSocketFactory factory = (SSLSocketFactory)
		 * SSLSocketFactory.getDefault(); clientSSLSocket = (SSLSocket)
		 * factory.createSocket(hostName, publicProxyPort);
		 * clientSSLSocket.startHandshake(); } catch (Exception ex) {
		 * System.out.println("Could not create ssl socket"); }
		 */

		try {
			clientSocket = new Socket(hostName, publicProxyPort);
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
}
// TODO: Choose from a list, a random and open Public Proxy to use