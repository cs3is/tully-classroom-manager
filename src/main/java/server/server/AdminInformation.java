package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminInformation extends Info {
	public AdminInformation(int classroom, String userName, String Hostname, ObjectInputStream in,
			ObjectOutputStream out) {
		super(classroom, userName, Hostname, in, out);
	}
}
