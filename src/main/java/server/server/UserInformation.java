package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserInformation {

	private String Hostname;
	private String userName;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public UserInformation(String userName, String Hostname, ObjectInputStream in, ObjectOutputStream out) {
		this.userName = userName;
		this.Hostname = Hostname;
		this.in = in;
		this.out = out;

	}

}
