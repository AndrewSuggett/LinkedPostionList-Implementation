public class LinkedPositionList<E> implements PositionList<E>
{
	private DLNode<E> header;
	private DLNode<E> trailer;
	private int size = 0;

	public LinkedPositionList()
	{
		//Creates an emepty list
		header = new DLNode<E>(null, null, null);
		trailer = new DLNode<E>(header, null, null);
		header.setNext(trailer);

	}

	//Helper Methods
	protected DLNode<E> checkPosition(Position<E> p) throws IllegalPostionException
	{
		//Validates the postion and returns it as a node

		if(!(p instanceof DLNode)){ throw new IllegalPostionException("Invalid Position");}

		DLNode<E> node = (DLNode<E>) p;

		if(node.getNext() == null)
			{throw new IllegalPostionException("Position is no longer in the list.");}
		return node;

	}

	protected Position<E> position(DLNode<E> node)
	{
		//Returns a node as a position
		if(node == header || node == trailer)
			return null;
		return node;
	}

	//End of Helper Methods


	//Implmentation of PostionList Methods

	public int size(){return size;}
	public boolean isEmpty(){return size == 0;}

	public Position<E> first()
	{
		//Returns first position or null if empty
		return position(header.getNext());
	}

	public Position<E> last()
	{
		return position(trailer.getPrev());
	}

	public Position<E> before(Position<E> p)
	{
		DLNode<E> n = checkPosition(p);
		return position(n.getPrev());
	}

	public Position<E> after(Position<E> p)
	{
		DLNode<E> n = checkPosition(p);
		return (n.getNext());
	}

	public Position<E> addFirst(E e)
	{

		DLNode<E> second = header.getNext();
		DLNode<E> first = new DLNode<E>(header, second, e);
		header.setNext(first);
		second.setPrev(first);
		size++;
		return first;
	}

	public Position<E> addLast(E e)
	{
		DLNode<E> secondLast = trailer.getPrev();
		DLNode<E> last = new DLNode<E>(secondLast, trailer, e);
		secondLast.setNext(last);
		trailer.setPrev(last);
		size++;
		return last;
	}

	public Position<E> addBefore(Position<E> p, E e)
	{

		if(p == first())
		{
			return addFirst(e);
		}

		DLNode<E> n = checkPosition(p);
		DLNode<E> prev = n.getPrev();

		DLNode<E> newNode = new DLNode<E>(prev, n, e);
		prev.setNext(newNode);
		n.setPrev(newNode);
		size++;
		return newNode;
	}

	public Position<E> addAfter(Position<E> p, E e)
	{

		if(p == last())
		{
			return addLast(e);
		}

		DLNode<E> n = checkPosition(p);
		DLNode<E> next = n.getNext();

		DLNode<E> newNode = new DLNode<E>(n, next, e);
		next.setPrev(newNode);
		n.setNext(newNode);
		size++;

		return newNode;

	}

	public E set(Position<E> p, E e)
	{

		DLNode<E> n = checkPosition(p);
		E answer = n.getElement();
		n.setElement(e);
		return answer;

	}

	public E remove(Position<E> p)
	{
		DLNode<E> n = checkPosition(p);
		DLNode<E> before = n.getPrev();
		DLNode<E> after = n.getNext();

		before.setNext(after);
		after.setPrev(before);

		E answer = n.getElement();

		size--;
		return answer;

	}

	//Returns an iterable representation of the lists postions
	public Iterable<Position<E>> positions()
	{
		return new PositionIterable();
	}

	public Iterator<E> iterator()
	{
		return new ElementIter();
	}

	public String toString()
	{
		String data = " ";
		Iterator<E> iter = this.iterator();
		while(iter.hasNext())
		{
			String e = iter.next().toString();
			data += e + ", ";
		}
		String s = "[" + data + "]";
		return s;
	}
	//End of PostionList methods





	//Nested PostionIterator Class
	protected class PositionIterator implements Iterator<Position<E>>
	{

		//Creates an iterator for the list
		private Position<E> cursor = first();
		private Position<E> recent = null;

		public boolean hasNext(){return(cursor != trailer);}

		public Position<E> next() throws IllegalPostionException
		{
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
	}

	//Nested PositionIterable class
	protected class PositionIterable implements Iterable<Position<E>>
	{
		public Iterator<Position<E>> iterator() {return new PositionIterator();}
	}

	//Uses the iteration by positions() to return the elements
	protected class ElementIter implements Iterator<E>
	{
		Iterator<Position<E>> posIterator = new PositionIterator();

		public boolean hasNext(){return posIterator.hasNext();}

		public E next(){return posIterator.next().getElement();}
	}

}