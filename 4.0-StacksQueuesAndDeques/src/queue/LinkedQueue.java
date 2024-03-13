package queue;

import fundamentalDataStructures.SinglyLinkedList;

public class LinkedQueue<E> {
	private SinglyLinkedList<E> linkedQueue = new SinglyLinkedList<>();

	public LinkedQueue() {
	}

	public int size() {
		return linkedQueue.getSize();
	}

	public boolean isEmpty() {
		return linkedQueue.isEmpty();
	}

	public void enqueue(E element) {
		linkedQueue.addLast(element);
	}
}
