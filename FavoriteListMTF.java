public class FavoriteListMTF<E> extends FavoriteList<E>
{
	public FavoriteListMTF()
	{
		super();
	}
	//OverRides MoveUp in FavoriteList to add the mostly recently added Entry to the front of the list
	protected void moveUp(Position<Entry<E>> p)
	{
		if(p != fList.first())
			fList.addFirst(fList.remove(p));
	}

	public PositionList<Entry<E>> top(int i)
	{
		if(i < 0 || i > size())
			throw new IndexOutOfBoundsException("That index is not in the list");

		PositionList<Entry<E>> temp = new LinkedPositionList<Entry<E>>();
		Iterator<Entry<E>> iter = fList.iterator();
		while(iter.hasNext())
			temp.addLast(iter.next());

		PositionList<Entry<E>> topEntries = new LinkedPositionList<Entry<E>>();

		for(int j = 0; j < i; j++)
		{
			Position<Entry<E>> highEntry = temp.first();
			Position<Entry<E>> walk = temp.after(highEntry);

			while(walk != null)
			{
				try{
					if(key(highEntry) < key(walk))
						highEntry = walk;
					walk = temp.after(walk); //Throws error if walk = null
				}catch(IllegalPostionException error)
				{
					walk = null;
				}
			}

			topEntries.addLast((MyEntry<E>) highEntry.getElement());
			temp.remove(highEntry);
		}

		return topEntries;

	}


}