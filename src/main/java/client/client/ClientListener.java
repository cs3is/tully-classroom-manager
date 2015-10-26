package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientListener implements Runnable{
	ObjectOutputStream out;
	ObjectInputStream in;
	public ClientListener(ObjectOutputStream out, ObjectInputStream in){
		this.out = out;
		this.in = in;
		Thread t = new Thread();
		t.start();
	}

	@Override
	public void run() {
		while(true){
			
		}
		
	}
}
