package oldServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionData {

	Socket connection;
	Integer selectedClass;
	Boolean connectionAccepted;
	Boolean admin;
	ObjectInputStream in;
	ObjectOutputStream out;

	public ConnectionData(Socket connection, Integer selectedClass, Boolean connectionAccepted, Boolean admin,
			ObjectInputStream in, ObjectOutputStream out) {
		this.connection = connection;
		this.selectedClass = selectedClass;
		this.connectionAccepted = connectionAccepted;
		this.admin = admin;
		this.in = in;
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public Integer getSelectedClass() {
		return selectedClass;
	}

	public void setSelectedClass(Integer selectedClass) {
		this.selectedClass = selectedClass;
	}

	public Boolean getConnectionAccepted() {
		return connectionAccepted;
	}

	public void setConnectionAccepted(Boolean connectionAccepted) {
		this.connectionAccepted = connectionAccepted;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
