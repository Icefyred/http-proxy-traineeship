package pt.compta.http.proxy.traineeship;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HttpServerProxy implements Runnable {

	private ServerSocket serverSocket;
	static ArrayList<Thread> servicingThreads;
	// Control unit
	private boolean running = true;

	public HttpServerProxy(int portNumber) {

		servicingThreads = new ArrayList<>();
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
		System.out.println("Listening...");
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (running) {
			try {
				// call to accept() blocks until it receives an incoming client request
				Socket clientSocket = serverSocket.accept();

				Thread requestHandler = new Thread(new RequestHandler(clientSocket));

				// Adding thread to list of servicing threads
				servicingThreads.add(requestHandler);

				requestHandler.start();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	void closeServer() {
		System.out.println("Terminating HttpServer");
		running = false;

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