package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientData {

	private String computerName;
	private String IPAddress;
	private boolean connected;
	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;
	private boolean canAsk;

}
