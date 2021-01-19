public class FavoriteList<E>  {

	protected PositionList<Entry<E>> fList;

	//Constructor
	public FavoriteList() { fList = new LinkedPositionList<Entry<E>>(); }

	protected E value(Position<Entry<E>> p){return p.getElement().value();}
	protected int key(Position<Entry<E>> p) {return p.getElement().key();}

	public int size() {return fList.size();}
	public boolean isEmpty() {return fList.isEmpty();}


	//Nested class to implement Entry
	protected static class MyEntry<E> implements Entry<E>
	{
		protected E value;
		protected int key = 0;

		public MyEntry(E val)
		{
			value = val;
		}

		public E value(){return value;}
		public int key(){return key;}
		public void incrementKey(){key++;}

		public String toString(){return "[" + key + "," + value + "]";}
	}



	protected Position<Entry<E>> findPosition(E e)
	{
		Position<Entry<E>> walk = fList.first();
		try{
			while(walk != null && !e.equals(value(walk)))
			{
				walk = fList.after(walk);
			}
			return walk;
		}catch(IllegalPostionException error) //Throws exception if the element is not in the list so catch it and return null
		{
			return null;
		}
	}

	protected void moveUp(Position<Entry<E>> p)
	{
		//Moves an entry at Poisiton p up the list based on the count of the postion
		int count = key(p);
		Position<Entry<E>> walk = p;

		while(walk != fList.first() && key(fList.before(walk)) < count)
			walk = fList.before(walk);
		if(walk != p)
			fList.addBefore(walk, fList.remove(p));
	}

	public void access(E e)
	{
		Position<Entry<E>> p = findPosition(e); //If this returns null add a new MyEntry<E> position to the list
		if(p == null)
		{
			fList.addLast(new MyEntry<E>(e));
			p = fList.last();
		}
		MyEntry<E> entry = (MyEntry<E>) p.getElement(); //Casts an entry into a myEntry to increament the key
		entry.incrementKey();
		moveUp(p);
	}

	public void remove(E e)
	{
		Position<Entry<E>> p = findPosition(e);
		if(p != null)
			fList.remove(p);
	}

	//Returns a Position list of the top (i) entrys
	public PositionList<Entry<E>> top(int i) throws IndexOutOfBoundsException
	{
		if(i < 0 || i > size())
			throw new IndexOutOfBoundsException("That index is not in the list");

		PositionList<Entry<E>> result = new LinkedPositionList<Entry<E>>();
		Position<Entry<E>> p = fList.first();
		for(int j = 0; j < i; j++)
		{
			result.addLast((MyEntry<E>) p.getElement());
			p = fList.after(p);
		}
		return result;
	}

	public String toString()
	{
		return fList.toString();
	}

}