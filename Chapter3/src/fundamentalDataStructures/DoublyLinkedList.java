package fundamentalDataStructures;

public class DoublyLinkedList<E> {
	private Node<E> header; // header sentinel
	private Node<E> trailer; // trailer sentinel
	private int size = 0;

	/** Constructs a new empty list. */
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

	public void concatenateLinkedList(DoublyLinkedList<E> L, DoublyLinkedList<E> M) {
		if (L.size() == 0 || M.size() == 0)
			throw new IllegalArgumentException("LinkedLists cannot be empty");
		header = L.getHeader();
		trailer = L.getTrailer();
		Node<E> firstNodeOfSecondList = M.getHeader().getNext();
		Node<E> lastNodeOfSecondList = M.getTrailer().getPrev();

		// Set next pointer of last node of (1st list) to 1st node of (2nd list).
		trailer.getPrev().setNext(firstNodeOfSecondList);
		// Set previous pointer of first node of (2nd list) to last node of (1st list).
		firstNodeOfSecondList.setPrev(trailer.getPrev());
		// Set trailer's previous pointer to last node of (2nd list).
		trailer.setPrev(lastNodeOfSecondList);
		// Set next pointer of last node to trailer.
		lastNodeOfSecondList.setNext(lastNodeOfSecondList);

		size = L.getSize() + M.getSize();
	}

	public void swapNodes(Node<E> x, Node<E> y) {
		if (header.getNext() == trailer)
			throw new IllegalArgumentException("List is empty!");

		if (header.getNext() == trailer.getPrev())
			throw new IllegalArgumentException("List size must be > 1.");

		// Refactor this method.

		// Adjacent nodes.
		// x -->
		if (x.getNext() == y) {
			// Find prev of x.
			Node<E> xPrevNode = x.getPrev();
			// Find next of y.
			Node<E> yNextNode = y.getNext();
			// point y's prev to xPrev.
			y.setPrev(xPrevNode);
			// Point xPrevNext to y.
			xPrevNode.setNext(y);
			// Point y's next to x.
			y.setNext(x);
			// Point x' prev to y.
			x.setPrev(y);
			// Point x's next to yNext.
			x.setNext(yNextNode);
			// Point yNext prev to x.
			yNextNode.setPrev(x);
		}
		// y -->
		else if (y.getNext() == x) {
			// Find prev of x.
			Node<E> xPrevNode = y.getPrev();
			// Find next of y.
			Node<E> yNextNode = x.getNext();
			// point y's prev to xPrev.
			y.setPrev(xPrevNode);
			// Point xPrevNext to y.
			xPrevNode.setNext(y);
			// Point y's next to x.
			y.setNext(x);
			// Point x' prev to y.
			x.setPrev(y);
			// Point x's next to yNext.
			x.setNext(yNextNode);
			// Point yNext prev to x.
			yNextNode.setPrev(x);
		}

		// Unadjacent nodes.
		Node<E> xPrevNode = x.getPrev();
		Node<E> yPrevNode = y.getPrev();
		Node<E> xNextNode = x.getNext();
		Node<E> yNextNode = y.getNext();

		xPrevNode.setNext(y);
		y.setPrev(xPrevNode);
		y.setNext(xNextNode);
		xNextNode.setPrev(y);

		yPrevNode.setNext(x);
		x.setPrev(yPrevNode);
		x.setNext(yNextNode);
		yNextNode.setPrev(x);
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

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;

		DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
		if (size() != other.size())
			return false;

		Node<?> walkA = header.getNext();
		Node<?> walkB = other.header.getNext();
		while (walkA.getElement() != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}

	public static class Node<E> {
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

	public Node<E> getHeader() {
		return header;
	}

	public Node<E> getTrailer() {
		return trailer;
	}

	public int getSize() {
		return size;
	}

	public void setHeader(Node<E> header) {
		this.header = header;
	}

	public void setTrailer(Node<E> trailer) {
		this.trailer = trailer;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
