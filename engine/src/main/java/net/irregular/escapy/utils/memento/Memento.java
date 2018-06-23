package net.irregular.escapy.utils.memento;

import java.util.EmptyStackException;
import java.util.Stack;

public class Memento<T extends EscapyCloneable<T>> {

	private final Stack<T> stack;
	private final int deep;
	private T bottom;

	public Memento(T content, int deep) {
		this.stack = new Stack<>();
		this.stack.push(content);
		this.deep = deep;
	}

	public Memento(T content) {
		this(content, 10);
	}

	public Memento<T> save() {
		stack.push(get().copy());
		if (stack.size() > deep + 1)
			stack.removeElementAt(0);
		return this;
	}

	public Memento<T> revert() {
		if (!stack.isEmpty()) bottom = stack.pop();
		return this;
	}

	public T get() {
		return stack.isEmpty() ? bottom : stack.peek();
	}

}