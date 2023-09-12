package fundamentalDataStructures;

/** CircularDoublyLinkedList implementation inheriting from CicularLinkedList */
public class InheritedCircularlyDoublyLinkedList<E> extends CircularlyLinkedList<E> {
	private Node<E> tail = null;

	public InheritedCircularlyDoublyLinkedList() {
		super();
	}

	@Override
	public void addFirst(E e) {
		if (isEmpty()) {
			tail = new Node<E>(e, null, null);
			addFirst(tail, tail, tail); // First node pointing to itself (from both ends).
		} else {
			Node<E> node = new Node<E>(e, tail, tail.getNext());
			addFirst(tail, node, tail.getNext());
		}
		setSize(getSize() + 1);
	}

	@Override
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}

	@Override
	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> node = firstNode();
		remove(tail, node.getNext());
		return node.getElement();
	}

	@Override
	public void rotate() {
		if (tail != null)
			tail = tail.getNext();
	}

	public void rotateBackwards() {
		if (tail != null)
			tail = tail.getPrev();
	}

	public Node<E> firstNode() {
		if (isEmpty())
			return null;
		return tail.getNext();
	}

	private void remove(Node<E> predecessor, Node<E> successor) {
		if (getSize() == 1)
			tail = null;
		else {
			predecessor.setNext(successor);
			successor.setPrev(predecessor);
		}
		setSize(getSize() - 1);
	}

	/**
	 * Adds a node to front of list also maintaining it's circular structure.
	 * <p>
	 * It maintains it circular structure by keeping lastNode next pointer points to
	 * new node. And firstNode previous pointer points to newNode.
	 * 
	 * @param lastNode  <i>(lastNode)</i> present in the list.
	 * @param node      <i>(node)</i> new node which is to be inserted in the list.
	 * @param firstNode <i>(firstNode)</i> present in the list.
	 */
	private void addFirst(Node<E> lastNode, Node<E> node, Node<E> firstNode) {
		lastNode.setNext(node);
		firstNode.setPrev(node);
	}

	public void printList() {
		int counter = 1;
		while (counter <= getSize()) {
			System.out.println(tail.getNext().getElement());
			tail = tail.getNext();
			counter++;
		}
	}

	public void printListReverse() {
		int counter = 1;
		while (counter <= getSize()) {
			System.out.println(tail.getElement());
			tail = tail.getPrev();
			counter++;
		}
	}

	private static final class Node<E> extends CircularlyLinkedList.Node<E> {
		private Node<E> prev;

		public Node(E e, Node<E> p, CircularlyLinkedList.Node<E> n) {
			super(e, n);
			prev = p;
		}

		@Override
		public Node<E> getNext() {
			return (Node<E>) super.getNext();
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}
}
