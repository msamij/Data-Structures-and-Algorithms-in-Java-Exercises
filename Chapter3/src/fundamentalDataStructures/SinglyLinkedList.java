package fundamentalDataStructures;

import java.util.Stack;

public class SinglyLinkedList<E> implements Cloneable {
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList() {
	}

	/**
	 * @return size of list without using instance <i>size</i> variable defined in
	 *         list.
	 */
	public int sizeWithNoInstanceSizeVariable() {
		if (head == null)
			return 0;
		int size = 1;
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

	/**
	 * Adds element to the front of the list.
	 * 
	 * @param e element to be inserted
	 */
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (size == 0)
			tail = head; // special case: new node becomes tail also
		size++;
	}

	/**
	 * Adds element to the end of the list.
	 * 
	 * @param e element to be inserted
	 */
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if (isEmpty()) {
			head = newest;
		} else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}

	/** Moves the first element to end of the list. */
	public void rotate() {
		if (getSize() > 1) {
			// Save previous head.
			Node<E> prevHead = head;
			// Move head to next node.
			head = head.getNext();
			// Prev head had now moved to last.
			tail.setNext(prevHead);
			// Update tail to point to last node.
			tail = prevHead;
		}
	}

	/**
	 * @return removes and returns the first element
	 */
	public E removeFirst() { //
		if (isEmpty())
			return null;
		E answer = head.getElement();
		head = head.getNext(); // will become null if list had only one node
		size--;
		if (size == 0)
			tail = null; // special case as list is now empty
		return answer;
	}

	/**
	 * 
	 * @return second last element in the list, If list size is <= 2 it returns
	 *         first element.
	 */
	public E secondLast() {
		if (isEmpty())
			return null;

		if (getSize() <= 2)
			return first();

		int counter = 1;
		Node<E> walk = head;
		while (counter < getSize() - 1) {
			walk = walk.getNext();
			counter++;
		}
		return walk.getElement();
	}

	/**
	 * Concatenate two singlylinkedlist into a single one.
	 * 
	 * @param L List one reference.
	 * @param M Second list reference.
	 */
	public void concatenateList(SinglyLinkedList<E> L, SinglyLinkedList<E> M) {
		if (L == null || M == null)
			throw new IllegalArgumentException("List pointers cannot be null");

		if (L.getSize() == 0 || M.getSize() == 0)
			throw new IllegalArgumentException("One of the two lists is empty!");

		if (L == M)
			throw new IllegalArgumentException("Both parameters refers to same list.");

		head = L.getHead(); // point head to first linkedlist head.
		tail = L.getTail(); // point tail to first linkedlist tail.
		tail.setNext(M.getHead()); // set tail's next to 2nd linkedlist head. (two lists are now joined)
		tail = M.getTail(); // set tail to refer to 2nd list tail.
		size = L.getSize() + M.getSize(); // update list size.
	}

	/**
	 * Reverse list by moving pointers.
	 * <p>
	 * Runs in O(N) time and uses O(1) space.
	 */
	public void reverseList() {
		if (isEmpty())
			throw new IllegalStateException("Linked list is empty.");
		if (getSize() == 1)
			throw new IllegalStateException("Linked list must contain more than one element.");

		Node<E> prev = null, current = head, next = null;
		while (current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}
		swapHeadAndTailReferences();
	}

	/**
	 * Reverse linked list using stack.
	 * <p/>
	 * Uses O(N) space time.
	 */
	public void reverseListUsingStack() {
		Node<E> headWalk = head;
		Stack<Node<E>> stack = new Stack<>();
		while (headWalk != null) {
			stack.push(headWalk);
			headWalk = headWalk.getNext();
		}

		Node<E> oldTail = tail;
		tail = stack.pop();
		while (!stack.isEmpty()) {
			Node<E> popNode = stack.pop();
			tail.setNext(popNode);
			tail = tail.getNext();
		}
		tail.setNext(null);
		head = oldTail;
	}

	public void printList() {
		int counter = 1;
		Node<E> walk = head;
		while (counter <= getSize()) {
			System.out.println(walk.getElement());
			walk = walk.getNext();
			counter++;
		}
	}

	/**
	 * Swap two nodes in a given singlylinkedlist not just their contents but by
	 * their position in list.
	 * 
	 * @param x x node to be replaced with y.
	 * @param y y node to be replaced with x.
	 */
	public void swapNodes(Node<E> x, Node<E> y) {
		if (x == null || y == null)
			throw new IllegalArgumentException("LinkedList is empty!");
		if (x == y)
			throw new IllegalArgumentException("x and y both refer to same node.");

		new SwapSinglyLinkedList(x, y).swapNodes();
	}

	private void swapHeadAndTailReferences() {
		Node<E> oldTail = tail;
		tail = head;
		head = oldTail;
	}

	private final class SwapSinglyLinkedList {
		private Node<E> firstNode = null;
		private Node<E> secondNode = null;

		private Node<E> firstNodeNextPointer = null;
		private Node<E> secondNodeNextPointer = null;

		private Node<E> firstNodePrevPointer = head;
		private Node<E> secondNodePrevPointer = head;

		/**
		 * 
		 * @param x Reference to a node (x) to be replaced with another node (y).
		 *          (must not be null)
		 * @param y Reference to a node (y) to be replaced with another node (x).
		 *          (must not be null)
		 */
		public SwapSinglyLinkedList(Node<E> x, Node<E> y) {
			firstNode = x;
			secondNode = y;
			firstNodeNextPointer = x.getNext();
			secondNodeNextPointer = y.getNext();
		}

		/**
		 * Swap given nodes x and y with each other and not just their contents but
		 * their position in the linkedlist.
		 */
		public void swapNodes() {
			findPrevNodes();

			if (nodesAdjacent(firstNode, secondNode)) {
				swapAdjacentNodes(firstNodePrevPointer, firstNode, secondNode);
			} else if (nodesAdjacent(secondNode, firstNode)) {
				swapAdjacentNodes(secondNodePrevPointer, secondNode, firstNode);
			} else {
				swapUnAdjacentNodes();
			}

			updateTail();
			updateHead();
		}

		private void findPrevNodes() {
			Node<E> walkA = head;
			Node<E> walkB = head;

			// Find prev node of (firstNode).
			while (walkA != firstNode) {
				firstNodePrevPointer = walkA;
				walkA = walkA.getNext();
			}
			// Find prev node of (secondNode).
			while (walkB != secondNode) {
				secondNodePrevPointer = walkB;
				walkB = walkB.getNext();
			}
		}

		private boolean nodesAdjacent(Node<E> n1, Node<E> n2) {
			return n1.getNext() == n2;
		}

		private void swapUnAdjacentNodes() {
			firstNodePrevPointer.setNext(secondNode);
			secondNodePrevPointer.setNext(firstNode);
			firstNode.setNext(secondNodeNextPointer);
			secondNode.setNext(firstNodeNextPointer);
		}

		/** Update tail pointer if x or y point to last node. */
		private void updateTail() {
			if (tail == secondNode) {
				tail = firstNode;
			} else if (tail == firstNode) {
				tail = secondNode;
			}
		}

		/** Update head pointer if x or y point to first node. */
		private void updateHead() {
			if (head == secondNode) {
				head = firstNode;
			} else if (head == firstNode) {
				head = secondNode;
			}
		}

		/**
		 * Swap adjacent nodes with each other.
		 * Example: If x points to y after swap y will point to x.
		 * 
		 * @param prevNode    Previous node of predecessor.
		 * @param predecessor Predecessor node to be swap with successor.
		 * @param successor   Successor node to be swap with predecessor.
		 */
		private void swapAdjacentNodes(Node<E> prevNode, Node<E> predecessor, Node<E> successor) {
			prevNode.setNext(successor);
			predecessor.setNext(successor.getNext());
			successor.setNext(predecessor);
		}
	}

	public static class Node<E> {
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
	@SuppressWarnings("unchecked")
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
		var other = (SinglyLinkedList<E>) super.clone(); // safe castsuper.clone();

		if (size > 0) { // we need independent chain of nodes.
			other.head = new Node<E>(head.getElement(), null);
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

	public Node<E> getHead() {
		return head;
	}

	public Node<E> getTail() {
		return tail;
	}

	public int getSize() {
		return size;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}

	public void setTail(Node<E> tail) {
		this.tail = tail;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
