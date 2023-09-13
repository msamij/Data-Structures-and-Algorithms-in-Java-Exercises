package fundamentalDataStructures;

import fundamentalDataStructures.util.SwapListNodes;

/**
 * DoublyLinkedList class implementation containing header and trailer sentinal
 * nodes.
 * <p>
 * <i>header</i> sentinal is a node in it's own not a pointer containing both
 * next and previous pointers. Same is the case with <i>trailer</i> sentinal.
 */
public class DoublyLinkedList<E> {
	private Node<E> header;
	private Node<E> trailer;
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

	/**
	 * @return size of list without using instance <i>size</i> variable defined in
	 *         list.
	 */
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

	/**
	 * Concatenate two doubly linked lists into a single one.
	 * 
	 * @param L list one
	 * @param M list two
	 */
	public void concatenateList(DoublyLinkedList<E> L, DoublyLinkedList<E> M) {
		if (L.size() == 0 || M.size() == 0)
			throw new IllegalArgumentException("One of the two list is empty");
		if (L == M)
			throw new IllegalArgumentException("Both lists refer to the same underlying list.");

		header = L.getHeader();
		trailer = L.getTrailer();

		Node<E> firstNodeOfSecondList = M.getHeader().getNext();
		Node<E> lastNodeOfSecondList = M.getTrailer().getPrev();
		Node<E> lastNodeOfFirstList = L.getTrailer().getPrev();

		// Last node's next pointer of (first list) points to first node of second list.
		lastNodeOfFirstList.setNext(firstNodeOfSecondList);

		// First node's previous pointer of (second list) point to last node of first
		// list.
		firstNodeOfSecondList.setPrev(lastNodeOfFirstList);

		// Set trailer's previous pointer to last node of (2nd list).
		trailer.setPrev(lastNodeOfSecondList);

		// Set next pointer of last node to trailer.
		lastNodeOfSecondList.setNext(trailer);

		size = L.getSize() + M.getSize();
	}

	public void printList() {
		Node<E> walk = header.getNext();
		while (walk != trailer) {
			System.out.println(walk.getElement());
			walk = walk.getNext();
		}
	}

	public void printListReverse() {
		Node<E> walk = trailer.getPrev();
		while (walk != header) {
			System.out.println(walk.getElement());
			walk = walk.getPrev();
		}
	}

	public void swapNodes(Node<E> x, Node<E> y) {
		if (header.getNext() == trailer)
			throw new IllegalArgumentException("List is empty!");

		if (header.getNext() == trailer.getPrev())
			throw new IllegalArgumentException("List size must be > 1.");

		if (x == y)
			throw new IllegalArgumentException("Both parameters refers to same node.");

		if (x == null || y == null)
			throw new NullPointerException("Parameters nodes cannot be null.");

		new SwapDoublyLinkedList<>(x, y).swapNodes();
		// Refactor this method.

		// Adjacent nodes.
		// x -->
		// if (x.getNext() == y) {
		// Node<E> xPrevNode = x.getPrev();
		// Node<E> yNextNode = y.getNext();
		// y.setPrev(xPrevNode);
		// xPrevNode.setNext(y);
		// y.setNext(x);
		// x.setPrev(y);
		// x.setNext(yNextNode);
		// yNextNode.setPrev(x);
		// }
		// // y -->
		// else if (y.getNext() == x) {
		// Node<E> yPrevNode = y.getPrev();
		// Node<E> xNextNode = x.getNext();
		// x.setPrev(yPrevNode);
		// yPrevNode.setNext(x);
		// x.setNext(y);
		// y.setPrev(x);
		// y.setNext(xNextNode);
		// xNextNode.setPrev(y);
		// } else {
		// // Unadjacent nodes.
		// Node<E> xPrevNode = x.getPrev();
		// Node<E> yPrevNode = y.getPrev();
		// Node<E> xNextNode = x.getNext();
		// Node<E> yNextNode = y.getNext();

		// xPrevNode.setNext(y);
		// y.setPrev(xPrevNode);
		// y.setNext(xNextNode);
		// xNextNode.setPrev(y);

		// yPrevNode.setNext(x);
		// x.setPrev(yPrevNode);
		// x.setNext(yNextNode);
		// yNextNode.setPrev(x);
		// }
	}

	private static final class SwapDoublyLinkedList<E> {
		private Node<E> firstNode = null;
		private Node<E> secondNode = null;

		private Node<E> firstNodeNextPointer = null;
		private Node<E> firstNodePrevPointer = null;

		private Node<E> secondNodeNextPointer = null;
		private Node<E> secondNodePrevPointer = null;

		public SwapDoublyLinkedList(Node<E> firstNode, Node<E> secondNode) {
			this.firstNode = firstNode;
			this.secondNode = secondNode;
			this.firstNodeNextPointer = firstNode.getNext();
			this.secondNodeNextPointer = secondNode.getNext();
			this.firstNodePrevPointer = firstNode.getPrev();
			this.secondNodePrevPointer = secondNode.getPrev();
		}

		public void swapNodes() {
			if (nodesAdjacent(firstNode, secondNode)) {
				swapAdjacentNodes(firstNodePrevPointer, firstNode, secondNode, secondNodeNextPointer);
			} else if (nodesAdjacent(secondNode, firstNode)) {
				swapAdjacentNodes(secondNodePrevPointer, secondNode, firstNode, firstNodeNextPointer);
			} else {
				swapUnAdjacentNodes();
			}
		}

		private boolean nodesAdjacent(Node<E> n1, Node<E> n2) {
			return n1.getNext() == n2;
		}

		/**
		 * 
		 * @param firstNodePredecessor
		 * @param firstNode
		 * @param secondNode
		 * @param secondNodeSuccessor
		 */
		private void swapAdjacentNodes(Node<E> firstNodePredecessor, Node<E> firstNode,
				Node<E> secondNode, Node<E> secondNodeSuccessor) {
			secondNode.setPrev(firstNodePredecessor);
			firstNodePredecessor.setNext(secondNode);
			secondNode.setNext(firstNode);
			firstNode.setPrev(secondNode);
			firstNode.setNext(secondNodeSuccessor);
			secondNodeSuccessor.setPrev(firstNode);
		}

		private void swapUnAdjacentNodes() {
			// firstNodePrevPointer.setNext(secondNode);
			// secondNode.setPrev(firstNodePrevPointer);
			// secondNode.setNext(firstNodeNextPointer);
			// firstNodeNextPointer.setPrev(secondNode);

			swapUnAdjacentNodes(secondNode, firstNodePrevPointer, firstNodeNextPointer);

			// secondNodePrevPointer.setNext(firstNode);
			// firstNode.setPrev(secondNodePrevPointer);
			// firstNode.setNext(secondNodeNextPointer);
			// secondNodeNextPointer.setPrev(firstNode);

			swapUnAdjacentNodes(firstNode, secondNodePrevPointer, secondNodeNextPointer);
		}

		private void swapUnAdjacentNodes(Node<E> newNode, Node<E> nodePrevPointer, Node<E> nodeNextPointer) {
			nodePrevPointer.setNext(newNode);
			newNode.setPrev(nodePrevPointer);
			newNode.setNext(nodeNextPointer);
			nodeNextPointer.setPrev(newNode);
		}
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

	public static final class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;

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
