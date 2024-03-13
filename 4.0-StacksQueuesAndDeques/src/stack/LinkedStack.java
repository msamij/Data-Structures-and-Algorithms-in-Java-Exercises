package stack;

import fundamentalDataStructures.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedStack() {
	}

	public int size() {
		return list.getSize();
	}

	public void push(E element) {
		list.addFirst(element);
	}

	public E top() {
		return list.first();
	}

	public E pop() {
		return list.removeFirst();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
}
