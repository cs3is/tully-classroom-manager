package Admin;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminData {
	ObjectInputStream in;
	ObjectOutputStream out;

	public AdminData(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}
}
