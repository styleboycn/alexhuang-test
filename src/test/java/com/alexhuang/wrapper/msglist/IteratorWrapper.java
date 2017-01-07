package com.alexhuang.wrapper.msglist;

import java.util.Iterator;

class IteratorWrapper<T> implements Iterator<T> {

	private int cursor = -1;
	private Iterator<T> iter;

	public IteratorWrapper(Iterator<T> iter) {
		this.iter = iter;
	}

	public int getCursor() {
		return cursor;
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public T next() {
		cursor++;
		return iter.next();
	}

}
