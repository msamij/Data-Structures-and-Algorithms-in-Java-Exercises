package fundamentalDataStructures;

/**
 * An implementation of DoublyLinkedList with a circular version without any
 * sentinal nodes. This list contains nodes where every node has prev and next
 * pointer.
 * <p/>
 * Last node contains next pointer pointing to first node similarly first node's
 * prev pointer pointing to last node, Forming a complete circular structure.
 */
public class CircularlyDoublyLinkedList<E> {
	private int size = 0;
	private Node<E> tail = null;

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

	public Node<E> firstNode() {
		if (isEmpty())
			return null;
		return tail.getNext();
	}

	public Node<E> lastNode() {
		if (isEmpty())
			return null;
		return tail;
	}

	/** Adds element to the front of the list */
	public void addFirst(E e) {
		if (isEmpty()) {
			tail = new Node<E>(e, null, null);
			tail.setNext(tail);
		} else {
			Node<E> node = new Node<E>(e, tail, tail.getNext());
			tail.setNext(node);
		}
		size++;
	}

	/** Adds element to the end of the list */
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}

	/** Removes and returns the first element of the list. */
	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> node = firstNode();
		remove(tail, node.getNext());
		return node.getElement();
		// if (node == tail)
		// tail = null;
		// else {
		// tail.setNext(node.getNext());
		// tail.getNext().setPrev(tail);
		// }
		// size--;
	}

	/** Removes and returns the last element of the list. */
	public E removeLast() {
		if (isEmpty())
			return null;
		Node<E> node = lastNode();
		remove(node.getPrev(), node.getNext());
		tail = node.getPrev();
		// if (node.getNext() == tail)
		// tail = null;
		// else {
		// node.getPrev().setNext(node.getNext());
		// tail = node.getPrev();
		// }
		// size--;
		return node.getElement();
	}

	/**
	 * Removes a given node from list while also updating predecessor and successor
	 * nodes.
	 * </p>
	 * List circular structure will be maintained by updating predecessor's next and
	 * successor previous pointer repectively.
	 * 
	 * @param predecessor previous node of <i>(node to be removed)</i>
	 * @param successor   next node of <i>(node to be removed)</i>
	 */
	private void remove(Node<E> predecessor, Node<E> successor) {
		if (size == 1)
			tail = null;
		else {
			predecessor.setNext(successor);
			successor.setPrev(predecessor);
		}
		size--;
	}

	public void printList() {
		int counter = 1;
		while (counter <= size) {
			System.out.println(tail.getNext().getElement());
			tail = tail.getNext();
			counter++;
		}
	}

	public void rotate() {
	}

	public void rotateBackwards() {
	}

	private static final class Node<E> {
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

	public int getSize() {
		return size;
	}

	public Node<E> getTail() {
		return tail;
	}
}
