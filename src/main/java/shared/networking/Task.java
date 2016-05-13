package shared.networking;

import java.io.Serializable;

public class Task implements Serializable {

	private TaskEnum toDo;
	private Object o;
	private String text;
	private int compNum;

	public Task(TaskEnum toDo) {
		this.toDo = toDo;
	}

	public Task(TaskEnum toDo, String text) {
		this.toDo = toDo;
		this.text = text;
	}

	public Task(TaskEnum toDo, Object o) {
		this.toDo = toDo;
		this.o = o;
	}
	
	public Task(TaskEnum toDo, int compNum, String text){
		this.toDo = toDo;
		this.text = text;
		this.setCompNum(compNum);
	}
	
	public Task(TaskEnum toDo, int compNum, Object o){
		this.toDo = toDo;
		this.o = o;
		this.setCompNum(compNum);
	}

	public TaskEnum getTask() {
		return toDo;
	}

	public String getText() {
		return text;
	}

	public Object getObject() {
		return o;
	}

	public String toString() {
		if (text != null)
			return "tried to print a Task with a value of " + toDo + ", and a message of \"" + text + "\"";
		else
			return "tried to print a Task with a value of " + toDo + ", and a message of \"" + o.toString() + "\"";
	}

	public int getCompNum() {
		return compNum;
	}

	public void setCompNum(int compNum) {
		this.compNum = compNum;
	}

}
