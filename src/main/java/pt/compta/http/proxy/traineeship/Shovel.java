package pt.compta.http.proxy.traineeship;

import java.io.InputStream;
import java.io.OutputStream;

public class Shovel implements Runnable {

	private InputStream input;
	private OutputStream output;

	public Shovel(InputStream input, OutputStream output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void run() {
		try {
			byte[] buffer = new byte[4096];
			int read;
			do {
				read = this.input.read(buffer);
				if (read > 0) {
					this.output.write(buffer, 0, read);
					if (this.input.available() < 1) {
						this.output.flush();
					}
				}
			} while (read >= 0);
		} catch (Exception ex) {
		}
	}
}
