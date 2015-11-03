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
					Object o = cd.getIn().readObject();
						if (o instanceof Task){
							actOnTask(o);
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void actOnTask(Object o) {
				Task t = (Task) o;
				switch (t.getTask()) {

				case Task.QUESTION_ADDED:
						cd.setQuestionAdded(true);
					break;

				
				}

			}
		});
	}

	@Override
	public void run() {
		while(true){
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
