package pt.compta.http.proxy.traineeship;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerProxy {
	private int portNumber;

	public void listening() {
		try (ServerSocket httpServerSocket = new ServerSocket(portNumber)) { // definition of the port connection to the
																				// server

			System.out.println("Listening on port " + portNumber); // prints in the console what's stated in inverted
																	// commas

			ServerSocket serverSocket = httpServerSocket; // temp variable that keeps all the information from the
															// server
			Socket clientSocket = serverSocket.accept(); // client connected and blocks all incoming calls afterwards

			RequestHandler requestHandler = new RequestHandler(); // instantiation of the RequestHandler class
			requestHandler.request(clientSocket); // call of the request method() from the RequestHandler class

		} catch (Exception ex) { // if the port is either occupied or any other type of error occurs, this procs
			System.err.println(ex.getMessage()); // prints in the console the error occured
		}
	}

	public HttpServerProxy(int portNumber) { // constructor of the HttpServerProxy
		this.portNumber = portNumber;
	}

	public int getPortNumber() { // getter with the portNumber value
		return this.portNumber;
	}
}
