public interface PositionList<E>
{
	int size(); //Returns size of list
	boolean isEmpty(); //Returns true if list is empty

	Position<E> first(); //Returns first position
	Position<E> last(); //Returns last position

	Position<E> before(Position <E> p); //Adds a position before another position
	Position<E> after(Position <E> p); //Adda a position after another position

	Position<E> addFirst(E e); //Adds an element at the first and list
	Position<E> addLast(E e); //Adds an element at the last of the list

	Position<E> addBefore(Position<E> p, E e); //Inserts element before a position
	Position<E> addAfter(Position<E> p, E e); //Inserts an element after a position

	E set(Position<E> p, E e); //Replaces the element stored at Postion P and returns the replaced element
	E remove(Position<E> p); //Removes the stored element and returns it

	Iterator<E> iterator(); //Returns an iterator of all elements in the list
	Iterable<Position<E>> positions(); //Returns an iterator of all postions in the list

}