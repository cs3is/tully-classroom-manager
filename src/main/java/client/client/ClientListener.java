package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;

public class ClientListener implements Runnable{
	private ClientData cd = null;
	public ClientListener(ClientData cd){
		this.cd = cd;
		Thread t = new Thread();
		t.start();
		randomNameToChangeLater();
	}
	
	
	
	public void randomNameToChangeLater(){
		Thread th = new Thread(new Runnable() {
			public void run(){
				try {
					Object o = cd.in.readObject();
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
