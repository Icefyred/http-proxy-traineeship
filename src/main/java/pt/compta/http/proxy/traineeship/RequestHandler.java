package pt.compta.http.proxy.traineeship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler {

	public void request(Socket clientSocket) throws IOException {
		BufferedReader proxyToClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter printer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

	}
}
