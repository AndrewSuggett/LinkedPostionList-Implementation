public class DLNode<E> implements Position<E>
{
	private E element;
	private DLNode<E> prev;
	private DLNode<E> next;

	public DLNode(DLNode<E> p, DLNode<E> n, E e)
	{
		element = e;
		prev = p;
		next = n;
	}

	public DLNode<E> getPrev(){return prev;}
	public DLNode<E> getNext(){return next;}

	public E getElement() throws IllegalPostionException{

		if(element == null)
			throw new IllegalPostionException("Position is no longer valid");
		return element;

	}

	public void setPrev(DLNode<E> p){prev = p;}
	public void setNext(DLNode<E> n){next = n;}
	public void setElement(E e){element = e;}

}