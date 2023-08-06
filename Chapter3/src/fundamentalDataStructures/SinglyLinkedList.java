package fundamentalDataStructures;

public class SinglyLinkedList<E> implements Cloneable {
	private Node<E> head = null; // head node of the list (or null if empty)
	private Node<E> tail = null;// last node of the list (or null if empty)
	private int size = 0; // number of nodes in the list

	public SinglyLinkedList() {
	} // constructs an initially empty list

	public int size() {
		return size;
	}

	public int sizeWithNoInstanceSizeVariable() {
		if (head == null)
			return 0;
		int size = 1; // Assume we atleast have one element.
		while (head.getNext() != null) {
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
		return head.getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	public void addFirst(E e) { // adds element e to the front of the list
		head = new Node<>(e, head);
		if (size == 0) // create and link a new node
			tail = head; // special case: new node becomes tail also
		size++;
	}

	public void addLast(E e) {// adds element e to the end of the list
		Node<E> newest = new Node<>(e, null); // node will eventually be the tail
		if (isEmpty())
			head = newest; // special case: previously empty list
		else
			tail.setNext(newest); // new node after existing tail
		tail = newest; // new node becomes the tail
		size++;
	}

	public E removeFirst() { // removes and returns the first element
		if (isEmpty())
			return null; // nothing to remove
		E answer = head.getElement();
		head = head.getNext(); // will become null if list had only one node
		size--;
		if (size == 0)
			tail = null; // special case as list is now empty
		return answer;
	}

	public E secondLast() {
		if (isEmpty())
			return null;
		if (size() <= 2)
			return first();
		int counter = 1;
		Node<E> walk = head;
		while (counter < size() - 1) {
			walk = walk.getNext();
			counter++;
		}
		return walk.getElement();
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

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		SinglyLinkedList<?> other = (SinglyLinkedList<?>) o;
		if (size != other.size)
			return false;
		Node<?> walkA = head;
		Node<?> walkB = other.head;
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true; // if we reach this, everything matched successfully
	}

	@Override
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		/*
		 * The first step to making a class cloneable in Java is declaring that it
		 * implements the Cloneable interface. The remaining task is implementing a
		 * public version of the clone() method of the class. that method should begin
		 * by creating a new instance using a call to super.clone(), which in our
		 * case invokes the method from the Object class.
		 * 
		 * Because the inherited version returns an Object, we perform a narrowing cast
		 * to type SinglyLinkedList<E>. At this point in the execution, the other list
		 * has been created as a shallow copy of the original. Since our list class has
		 * two fields, size and head, the following assignments have been made:
		 * 
		 * other.size = this.size;
		 * other.head = this.head;
		 * 
		 * While the assignment of the size variable is correct, we cannot allow the new
		 * list to share the same head value (unless it is null). For a nonempty list to
		 * have an independent state, it must have an entirely new chain of nodes, each
		 * storing a reference to the corresponding element from the original list. We
		 * therefore create a new head node, and then perform a walk through the
		 * remainder of the original list while creating and linking new nodes for the
		 * new list.
		 */

		// always use inherited Object.clone() to create the initial copy.
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe castsuper.clone();

		if (size > 0) { // we need independent chain of nodes.
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext(); // walk through remainder of original list.
			Node<E> otherTail = other.head; // remember most recently created node.

			while (walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest); // link previous node to this one.
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}
}
