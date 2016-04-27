package Admin;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Questions.Question;

public class AdminData {
	public static ObjectInputStream in;
	public static ObjectOutputStream out;
	public static Boolean questionIsRemoved=false;
	public static Question q;
	public AdminData(ObjectInputStream in, ObjectOutputStream out) {
		AdminData.in = in;
		AdminData.out = out;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public Boolean isQuestionRemoved() {
		boolean temp = questionIsRemoved;
		questionIsRemoved = false;
		return temp;
	}

	public void questionIsRemoved() {
		questionIsRemoved = true;
	}
	public Question getQuestion(){
		Question temp = q;
		q = null;
		return temp;
	}
	public void setQuestion(Question q){
		this.q = q;
	}
}
