package pt.compta.http.proxy.traineeship;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerProxy {
	private int portNumber;
	private ServerSocket serverSocket;

	public HttpServerProxy(int portNumber) { // constructor of the HttpServerProxy class
		this.portNumber = portNumber;
		try {
			// starts the server socket bound to the port number
			serverSocket = new ServerSocket(portNumber);

			System.out.println("Started on port " + portNumber);
			// if the port is either occupied or any other type of error occurs, this procs
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

	}

	public void listening() {
		try {
			// call to accept() blocks until it receives an incoming client request
			Socket clientSocket = serverSocket.accept();

			// instantiation of the RequestHandler class
			RequestHandler requestHandler = new RequestHandler();
			// call of the request() method from the RequestHandler class
			requestHandler.request(clientSocket);

		} catch (Exception ex) {
			// prints in the console the error occured
			System.err.println(ex.getMessage());
		}

	}

}
