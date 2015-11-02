package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientData {

	private String computerName;
	private String IPAddress;
	private boolean connected;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private boolean canAsk;
	
	
	public String getComputerName() {
		return computerName;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public boolean canAsk() {
		return canAsk;
	}
	public void setCanAsk(boolean canAsk) {
		this.canAsk = canAsk;
	}
	public boolean isConnected() {
		return connected;
	}
	public ObjectOutputStream getOut() {
		return out;
	}
	public ObjectInputStream getIn() {
		return in;
	}

}
