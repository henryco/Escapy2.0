package net.tindersamurai.escapy.utils.memento;

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
		try { bottom = stack.pop();
		} catch (EmptyStackException ignored) {}
		return this;
	}

	public T get() {
		try { return stack.peek();
		} catch (EmptyStackException e) { return bottom; }
	}

}