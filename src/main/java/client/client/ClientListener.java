package client;

import java.io.IOException;
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
		randomNameToChangeLater();
	}
	
	public void addQuestion(){
		
	}
	
	public void randomNameToChangeLater(){
		Thread th = new Thread(new Runnable() {
			public void run(){
				try {
					Object o = in.readObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void run() {
		while(true){
			try {
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
