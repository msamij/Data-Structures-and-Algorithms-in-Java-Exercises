package queue;

public class ArrayQueue<E> implements Queue<E> {
	private int f = 0; // index of the front element
	private int sz = 0; // current number of elements
	private final E[] data; // generic array used for storage
	public static final int CAPACITY = 1000; // default array capacity

	public ArrayQueue() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(final int capacity) {
		data = (E[]) new Object[capacity];
	}

	/** Removes and returns the first element of the queue (null if empty). */
	@Override
	public E dequeue() {
		if (isEmpty())
			return null;
		E answer = data[f];
		data[f] = null;
		f = (f + 1) % data.length;
		sz--;
		return answer;
	}

	/** Inserts an element at the rear of the queue. */
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if (sz == data.length) {
			throw new IllegalStateException("Queue is full");
		}
		int avail = (f + sz) % data.length;
		data[avail] = e;
		sz++;
	}

	/**
	 * Returns, but does not remove, the first element of the queue (null if empty)
	 */
	@Override
	public E first() {
		if (isEmpty())
			return null;
		return data[f];
	}

	/** Tests whether the queue is empty. */
	@Override
	public boolean isEmpty() {
		return sz == 0;
	}

	/** Returns the number of elements in the queue. */
	@Override
	public int size() {
		return sz;
	}
}
