package pt.compta.http.proxy.traineeship;

import java.util.Scanner;

public class Application {
	// constant to define which port the server will be using
	private static final int PORT_NUMBER = 8080;

	private boolean running = true;

	private String shutdownKeyword = "exit";
	private final HttpServerProxy proxy;

	private Application() {
		proxy = new HttpServerProxy(PORT_NUMBER);
	}

	public static void main(String[] args) {
		new Application().start();
	}

	private void start() {
		proxy.listening();
		Scanner input = new Scanner(System.in);
		while (running) {
			String commandToShutDown = input.nextLine();

			if (shutdownKeyword.equalsIgnoreCase(commandToShutDown)) {
				System.out.println("Exiting...");
				proxy.closeServer();
				running = false;
			} else {
				System.out.println("Command not found! If you're trying to shutdown, I suggest writing \"exit\"");
			}
		}
		input.close();
		System.out.println("Terminated.");
	}
}
