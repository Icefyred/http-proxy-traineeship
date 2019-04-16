package pt.compta.http.proxy.traineeship;

public class Application {
	private static final int PORT_NUMBER = 8080; // constant to define which port the server will be using

	public static void main(String[] args) {
		HttpServerProxy httpProxyServerProxy = new HttpServerProxy(PORT_NUMBER); // instantiation of the HttpServerProxy
																					// class
		httpProxyServerProxy.listening(); // call of the listening() method on the HttpServerProxy class
	}
}
