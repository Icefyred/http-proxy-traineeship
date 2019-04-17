package pt.compta.http.proxy.traineeship;

public class Application {
	// constant to define which port the server will be using
	private static final int PORT_NUMBER = 8080;

	public static void main(String[] args) {
		// instantiation of the HttpServerProxy class
		HttpServerProxy httpProxyServerProxy = new HttpServerProxy(PORT_NUMBER);
		// call of the listening() method on the HttpServerProxy class
		httpProxyServerProxy.listening();
	}
}
