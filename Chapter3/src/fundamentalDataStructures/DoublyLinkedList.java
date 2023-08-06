package fundamentalDataStructures;

public class DoublyLinkedList<E> {
	private Node<E> header; // header sentinel
	private Node<E> trailer; // trailer sentinel
	private int size = 0;

	/* Constructs a new empty list. */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null); // trailer is preceded by header
		header.setNext(trailer); // header is followed by trailer
	}

	public int size() {
		return size;
	}

	public int sizeWithNoInstanceSizeVariable() {
		if (header.getNext() == trailer)
			return 0;
		int size = 1;
		while (header.getNext() != trailer.getPrev()) {
			header = header.getNext();
			size++;
		}
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/** Returns (but does not remove) the first element of the list. */
	public E first() {
		if (isEmpty())
			return null;
		return header.getNext().getElement(); // first element is beyond header.
	}

	/** Returns (but does not remove) the last element of the list. */
	public E last() {
		if (isEmpty())
			return null;
		return trailer.getPrev().getElement();
	}

	/** Returns (but does not remove) the middle element of the list. */
	public E middle() {
		if (header.getNext() == trailer) // No element in the list.
			return null;

		E middleElement = null;
		boolean continueWalk = true;
		Node<E> nextWalk = header.getNext();
		Node<E> prevWalk = trailer.getPrev();
		while (continueWalk) {
			if (nextWalk.getElement() == prevWalk.getElement() || nextWalk.getNext() == prevWalk) {
				middleElement = nextWalk.getElement();
				continueWalk = false;
			}
			nextWalk = nextWalk.getNext();
			prevWalk = prevWalk.getPrev();
		}
		return middleElement;
	}

	/** Adds element to the front of the list */
	public void addFirst(E e) {
		addBetween(e, header, header.getNext()); // place just after the header.
	}

	/** Adds element to the end of the list */
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer); // place just before the trailer.
	}

	/** Removes and returns the first element of the list. */
	public E removeFirst() {
		if (isEmpty())
			return null;
		return remove(header.getNext()); // first element is beyond header.
	}

	/** Removes and returns the last element of the list. */
	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(trailer.getPrev()); // last element is before trailer
	}

	/** Removes the given node from the list and returns its element. */
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}

	/** Adds element e to the linked list in between the given nodes. */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}

	private static class Node<E> {
		private E element; // reference to the element stored at this node.
		private Node<E> prev; // reference to the previous node in the list.
		private Node<E> next; // reference to the subsequent node in the list.

		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setPrev(Node<E> p) {
			prev = p;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	}
}
