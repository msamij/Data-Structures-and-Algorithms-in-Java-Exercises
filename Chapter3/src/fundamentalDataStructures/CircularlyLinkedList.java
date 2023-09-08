package fundamentalDataStructures;

import java.util.HashSet;
import java.util.Set;

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

	public boolean isSequenceSame(CircularlyLinkedList<E> M) {
		if (isEmpty() || M.isEmpty())
			throw new IllegalArgumentException("Lists are empty.");

		Node<E> headNodeOfLargerList = null;
		Node<E> tailNodeOfLargerList = null;
		Node<E> headNodeOfSmallerList = null;

		// Setup head and tail nodes of larger lists for traversal.
		if (size() > M.size()) {
			headNodeOfLargerList = this.tail.getNext();
			tailNodeOfLargerList = this.tail;
			headNodeOfSmallerList = M.tail.getNext();
		} else {
			headNodeOfLargerList = M.tail.getNext();
			tailNodeOfLargerList = M.tail;
			headNodeOfSmallerList = this.tail.getNext();
		}

		Set<E> setL = new HashSet<>();
		Set<E> setM = new HashSet<>();

		// Traverse list until headNode of larger list reaches tail, This way we
		// would've traversed smaller list too.
		while (headNodeOfLargerList != tailNodeOfLargerList) {
			setL.add(headNodeOfLargerList.getElement());
			setM.add(headNodeOfSmallerList.getElement());

			headNodeOfLargerList = headNodeOfLargerList.getNext();
			headNodeOfSmallerList = headNodeOfSmallerList.getNext();
		}

		// Finally add last node of larger list to set.
		setL.add(tailNodeOfLargerList.getElement());

		if (setL.containsAll(setM))
			return true;
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;

		CircularlyLinkedList<?> other = (CircularlyLinkedList<?>) o;
		if (size() != other.size())
			return false;

		Node<?> walkA = tail;
		Node<?> walkB = other.tail;
		for (int i = 0; i < size(); i++) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
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
