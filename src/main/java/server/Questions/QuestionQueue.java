package Questions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import utils.ServerLog;

public class QuestionQueue<E> implements Queue<E>
{
	ArrayList<E> q = new ArrayList<E>();
	
	E e;

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


	public E get(int x)
	{
		try
		{
			return q.get(x);
		} catch (Exception e)
		{
//			ServerLog.log("Invalid Value", 'e');
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

}