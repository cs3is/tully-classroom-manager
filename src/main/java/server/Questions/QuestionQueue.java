package Questions;

import Log;
import QueueInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class QuestionQueue implements QueueInterface<E>
{
	ArrayList<E> q = new ArrayList<E>();

	public String toString()
	{
		String a = "";
		for (int i = 0; i < q.size(); i++)
		{
			String s = (String) q.get(i);
			a += s + "\n";
		}
		return a;
	}

	public boolean add(E o)
	{
		q.add(o);
		return false;
	}

	public boolean offer(E o)
	{
		q.add(o);
		return false;
	}

	public boolean empty()
	{
		if (q.size() == 0)
			return true;
		else
			return false;
	}

	public int size()
	{
		return q.size();
	}

	public E peek()
	{
		if (!(q.size() == 0))
			return q.get(0);
		else
			return null;
	}

	public E element()
	{
		return null;
	}

	public E poll()
	{
		if(q.size() != 0){
		E o = q.get(0);
		return q.remove(0);
		}
		else return null;
	}

	public E remove()
	{
		E o = q.get(0);
		q.remove(0);
		return o;
	}

	public E get(int x)
	{
		try
		{
			return q.get(x);
		} catch (Exception e)
		{
			Log.print("Invalid Value", 'e');
		}
		return null;
	}

}