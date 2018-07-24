package net.tindersamurai.escapy.utils.memento;

import lombok.ToString;

import java.util.Stack;

@ToString public class Memento<T extends EscapyCloneable<T>> {

	private final Stack<T> stack;
	private final int depth;
	private T bottom;

	public Memento(T content, int depth) {
		this.stack = new Stack<>();
		this.stack.push(content);
		this.depth = depth;
	}

	public Memento(T content) {
		this(content, 10);
	}

	public Memento<T> save() {
		stack.push(get().copy());
		if (stack.size() > depth + 1)
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