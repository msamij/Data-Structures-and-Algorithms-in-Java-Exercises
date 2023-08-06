package fundamentalDataStructures;

public class CircularlyLinkedList<E> {
	private Node<E> tail = null;// we store tail (but not head)// last node of the list (or null if empty)
	private int size = 0; // number of nodes in the list

	public CircularlyLinkedList() {
	} // constructs an initially empty list

	public int size() {
		return size;
	}

	public int sizeWithNoInstanceSizeVariable() {
		if (tail == null)
			return 0;
		int size = 1; // Assume we atleast have one element.
		Node<E> head = tail.getNext();
		while (head != tail) {
			head = head.getNext();
			size++;
		}
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return tail.getNext().getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	public void rotate() { // rotate the first element to the back of the list
		if (tail != null)
			tail = tail.getNext();
	}

	public void addFirst(E e) { // adds element e to the front of the list
		if (size() == 0) {
			tail = new Node<E>(e, null);
			tail.setNext(tail); // refers to itself.
		} else {
			// Node<E> newest = new Node<>(e, tail.getNext());
			// Reinforcement R-3.7
			tail.setNext(new Node<E>(e, tail.getNext()));
		}
		size++;
	}

	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> head = tail.getNext();
		if (head == tail) // must be the only node left
			tail = null;
		else
			tail.setNext(head.getNext()); // removes ”head” from the list
		size--;
		return head.getElement();
	}

	private static class Node<E> {
		private E element; // reference to the element stored at this node
		private Node<E> next; // reference to the subsequent node in the list

		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	}
}
