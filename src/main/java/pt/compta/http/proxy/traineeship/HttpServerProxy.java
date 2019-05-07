package pt.compta.http.proxy.traineeship;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HttpServerProxy implements Runnable {
	private int portNumber;
	private ServerSocket serverSocket;
	static ArrayList<Thread> servicingThreads;
	private boolean running; // Control unit

	public HttpServerProxy(int portNumber) { // constructor of the HttpServerProxy class
		this.portNumber = portNumber;
		servicingThreads = new ArrayList<>();
		new Thread(this).start();
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

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);

		String command;
		while (running) {
			command = scanner.nextLine();
			if (command.equalsIgnoreCase("close") || command.equalsIgnoreCase("f")) {
				running = false;
				closeServer();
			}
		}
		scanner.close();
	}

	private void closeServer() {
		try {
			// Close all servicing threads
			for (Thread thread : servicingThreads) {
				if (thread.isAlive()) {
					System.out.print("Waiting on " + thread.getId() + " to close..");
					thread.join();
					System.out.println(" closed");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Close server Socket
		try {
			System.out.println("Terminating Connection");
			serverSocket.close();
		} catch (Exception e) {
			System.out.println("Exception closing proxy's server socket");
			e.printStackTrace();
		}
	}
}