package fundamentalDataStructures;

import java.util.HashSet;
import java.util.Set;

public class CircularlyLinkedList<E> {
	private Node<E> tail = null;
	private int size = 0;

	public CircularlyLinkedList() {
	}

	public int size() {
		return size;
	}

	/**
	 * @return size of list without using instance <i>size</i> variable defined in
	 *         list.
	 */
	public int sizeWithoutInstanceSizeVariable() {
		if (tail == null)
			return 0;
		int size = 1; // Assume we have atleast one element.
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

	/** Rotate the first element to the back of the list */
	public void rotate() {
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

	/**
	 * 
	 * @param M Reference of 2nd list.
	 * @return true if invoking list contains same sequence as List. <i>M</i>
	 *         <p>
	 *         Two Lists contains same sequence if both lists have same elements
	 *         regardless of their order.
	 *         <p>
	 *         Example: L = {1, 2, 3}, M = {1, 3, 2, 4} in this case L contains
	 *         elements that are also in M, even though M has more elements than L.
	 */
	public boolean isSequenceSame(CircularlyLinkedList<E> M) {
		if (isEmpty() || M.isEmpty())
			throw new IllegalArgumentException("Lists are empty.");
		return new SequenceMatch().sequenceSame(M);
	}

	private final class SequenceMatch {
		private Node<E> headNodeOfLargerList = null;
		private Node<E> tailNodeOfLargerList = null;
		private Node<E> headNodeOfSmallerList = null;

		private final Set<E> setLarger = new HashSet<>();
		private final Set<E> setSmaller = new HashSet<>();

		public boolean sequenceSame(CircularlyLinkedList<E> M) {
			return setContainsAnotherSet(M);
		}

		private boolean setContainsAnotherSet(CircularlyLinkedList<E> M) {
			findLargerAndSmallerNodesForTraversal(M);
			addListsToSet();
			return setLarger.containsAll(setSmaller);
		}

		private void findLargerAndSmallerNodesForTraversal(CircularlyLinkedList<E> M) {
			if (size() > M.size()) {
				headNodeOfLargerList = tail.getNext();
				tailNodeOfLargerList = tail;
				headNodeOfSmallerList = M.tail.getNext();
			} else {
				headNodeOfLargerList = M.tail.getNext();
				tailNodeOfLargerList = M.tail;
				headNodeOfSmallerList = tail.getNext();
			}
		}

		private void addListsToSet() {
			// Traverse list until headNode of larger list reaches tail, This way we
			// would've traversed smaller list too.
			while (headNodeOfLargerList != tailNodeOfLargerList) {
				setLarger.add(headNodeOfLargerList.getElement());
				setSmaller.add(headNodeOfSmallerList.getElement());

				headNodeOfLargerList = headNodeOfLargerList.getNext();
				headNodeOfSmallerList = headNodeOfSmallerList.getNext();
			}
			// Finally add last node of larger list to set.(Since loop fill finish before
			// putting last element to set)
			setLarger.add(tailNodeOfLargerList.getElement());
		}

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

	static class Node<E> {
		private E element;
		private Node<E> next;

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

	public Node<E> getTail() {
		return tail;
	}

	public int getSize() {
		return size;
	}

	public void setTail(Node<E> tail) {
		this.tail = tail;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
