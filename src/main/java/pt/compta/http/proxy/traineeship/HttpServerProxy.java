package pt.compta.http.proxy.traineeship;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerProxy {
	private static final int PORT_NUMBER = 8080;

	public void listening() {
		try (ServerSocket httpServerSocket = new ServerSocket(PORT_NUMBER)) {

			System.out.println("Listening on port " + PORT_NUMBER);

			ServerSocket serverSocket = httpServerSocket;
			Socket clientSocket = new Socket();

			if (didItConnect(serverSocket, clientSocket)) {
				RequestHandler requestHandler = new RequestHandler();
				requestHandler.request();
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public boolean didItConnect(ServerSocket serverSocket, Socket clientSocket) throws IOException {
		boolean control = false;
		{
			clientSocket = serverSocket.accept();
			control = true;
			return control;
		}
	}
}
