package fundamentalDataStructures.util;

interface Node<E> {

	public Node<E> getNext();

	public void setNext(Node<E> n);

	default public Node<E> getPrev() {
		return null;
	}

	default public void setPrev(Node<E> n) {
	}
}

public abstract class SwapListNodes<T extends Node<T>> {
	private T firstNode = null;
	private T secondNode = null;

	private T firstNodeNextPointer = null;
	private T firstNodePrevPointer = null;

	private T secondNodeNextPointer = null;
	private T secondNodePrevPointer = null;

	public SwapListNodes() {
	}

	public abstract void swapNodes();

	public boolean nodesAdjacent(Node<T> n1, Node<T> n2) {
		return n1.getNext() == n2;
	}

	protected abstract void swapAdjacentNodes();

	protected abstract void swapUnAdjacentNodes();
}
