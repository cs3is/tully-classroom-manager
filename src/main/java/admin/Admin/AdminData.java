package Admin;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminData {
	public static ObjectInputStream in;
	public static ObjectOutputStream out;

	public AdminData(ObjectInputStream in, ObjectOutputStream out) {
		AdminData.in = in;
		AdminData.out = out;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}
}
