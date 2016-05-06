package shared.networking;

import java.io.Serializable;

public class Task implements Serializable {

	private TaskEnum toDo;
	private Object o;
	private String text;

	public Task(TaskEnum toDO) {
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

}
