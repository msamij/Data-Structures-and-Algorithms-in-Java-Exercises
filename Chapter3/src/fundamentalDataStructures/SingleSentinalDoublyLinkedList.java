package fundamentalDataStructures;

/**
 * An Implementation of DoublyLinkedList class using only single sentinal node.
 */
public class SingleSentinalDoublyLinkedList<E> {
	private Node<E> header;
	private int size = 0;

	public SingleSentinalDoublyLinkedList() {
		header = new Node<>(null, null, null);
	}

	public int size() {
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
		return lastNode().getElement();
	}

	/** Returns (but does not remove) the last node of the list. */
	private Node<E> lastNode() {
		Node<E> lastNode = header.getNext();
		while (lastNode.getNext() != null) {
			lastNode = lastNode.getNext();
		}
		return lastNode;
	}

	/** Adds element to the front of the list */
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}

	/** Adds element to the end of the list */
	public void addLast(E e) {
		Node<E> lastNode = lastNode();
		addBetween(e, lastNode, null); // place just before the last node.
	}

	/** Removes and returns the first element of the list. */
	public E removeFirst() {
		if (isEmpty())
			return null;
		return remove(header.getNext());
	}

	/** Removes and returns the last element of the list. */
	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(lastNode());
	}

	/** Removes the given node from the list and returns its element. */
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		// Successor is found based on node position if it was last node then successor
		// will be null, Otherwise not.
		Node<E> successor = node.getNext() != null ? node.getNext() : null;
		predecessor.setNext(successor);
		size--;
		return node.getElement();
	}

	/** Adds element e to the linked list in between the given nodes. */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		if (successor != null) // When adding lastNode successor is null.
			successor.setPrev(newest);
		size++;
	}

	private static class Node<E> {
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

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

	}
}
