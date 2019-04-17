package pt.compta.http.proxy.traineeship;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerProxy {
	private int portNumber;
	private ServerSocket serverSocket;

	public void listening() {
		try {
			System.out.println("Listening on port " + portNumber); // prints in the console what's stated in inverted
																	// commas

			Socket clientSocket = serverSocket.accept(); // call to accept() blocks until it receives an incoming client
															// request

			RequestHandler requestHandler = new RequestHandler(); // instantiation of the RequestHandler class
			requestHandler.request(clientSocket); // call of the request() method from the RequestHandler class

		} catch (Exception ex) {
			System.err.println(ex.getMessage()); // prints in the console the error occured
		}

	}

	public HttpServerProxy(int portNumber) { // constructor of the HttpServerProxy class
		this.portNumber = portNumber;
		try {
			serverSocket = new ServerSocket(portNumber);// definition of the port connection to the
			// server
		} catch (Exception ex) {// if the port is either occupied or any other type of error occurs, this procs
			throw new RuntimeException(ex.getMessage(), ex);
		}

	}
}
