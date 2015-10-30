package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientData {

	private String computerName;
	private String IPAddress;
	private boolean connected;
	ObjectOutputStream out;
	ObjectInputStream in;
	private boolean canAsk;

}
