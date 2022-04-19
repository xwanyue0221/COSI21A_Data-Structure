package main;

public class WorkEntryNode<T> {
	public T value;
	public WorkEntryNode next;
	
	/**
	 * The constructor of WorkEntryNode class
	 * @param data a T type object
	 */
	public WorkEntryNode(T data) {
		this.value = data;
		this.next = null;
	}

}
