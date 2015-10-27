package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserListener implements Runnable{
	
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public UserListener(ObjectInputStream in, ObjectOutputStream out){
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		
		while(true){
			try {
				Object o = in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
