package com.alexhuang.wrapper.msglist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class MsgListWrapper<T> implements List<T> {
	private List<T> msgList;
	private IteratorWrapper<T> iter;

	@Override
	public String toString() {
		return msgList.toString();
	}

	public MsgListWrapper(List<T> msgList) {
		this.msgList = msgList;
	}

	public T resetForNextLoop() {
		int cursor = iter.getCursor();
		iter = null;
		T res = msgList.get(cursor);
		msgList = msgList.subList(cursor + 1, msgList.size());
		return res;
	}

	public void resetForRetry() {
		int cursor = iter.getCursor();
		iter = null;
		if (cursor == 0)
			return;
		//iter = null;
		msgList = msgList.subList(cursor, msgList.size());
	}

	@Override
	public int size() {
		return msgList.size();
	}

	@Override
	public boolean isEmpty() {
		return msgList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return msgList.contains(o);
	}

	@Override
	public Object[] toArray() {
		return msgList.toArray();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return msgList.toArray(a);
	}

	@Override
	public boolean add(T e) {
		return msgList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return msgList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return msgList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return msgList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return msgList.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return msgList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return msgList.retainAll(c);
	}

	@Override
	public void clear() {
		msgList.clear();
	}

	@Override
	public T get(int index) {
		return msgList.get(index);
	}

	@Override
	public T set(int index, T element) {
		return msgList.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		msgList.add(index, element);
	}

	@Override
	public T remove(int index) {
		return msgList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return msgList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return msgList.lastIndexOf(o);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return msgList.subList(fromIndex, toIndex);
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("listIterator");
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException("listIterator(int)");
	}

	@Override
	public Iterator<T> iterator() {
		if (iter != null)
			throw new RuntimeException();
		else
			iter = new IteratorWrapper<>(this.msgList.iterator());
		return iter;
	}
	
	//ERROR USING!!!
	public static void test1() {
		List<String> myList = new ArrayList<String>();
		myList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11"));
		//
		MsgListWrapper<String> msgListWrapper = new MsgListWrapper<String>(
				myList);
		for (String str : msgListWrapper) {
			try {
				if ("4".equals(str)) {
					throw new NoSuchElementException();
				} else {
					System.out.println(str);
				}
			} catch (Exception ex) {
				//msgListWrapper.resetForNextLoop();
				msgListWrapper.resetForRetry();
			}
		}
	}
	
	public static void test2() {
		List<String> myList = new ArrayList<String>();
		myList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11"));
		//
		List<String> failList = new ArrayList<String>();
		MsgListWrapper<String> msgListWrapper = new MsgListWrapper<String>(
				myList);
		
		while(true) {
			try {
				if (msgListWrapper.size() > 0) {
					consumeMsgList(msgListWrapper);
				}
				break;
			} catch (Exception ex) {
				//先把出错的元素放到failList,然后把剩下未消费元素放到wrapper的msgList中,进行下次消费(consumeMsgList)
				failList.add(msgListWrapper.resetForNextLoop());
			}
		}
		failList.stream().forEach(str -> {
			System.out.println("failList Item -> " + str);
		});
	}
	
	public static void test3() {
		List<String> myList = new ArrayList<String>();
		myList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11"));
		MsgListWrapper<String> msgListWrapper = new MsgListWrapper<String>(
				myList);
		while(true) {
			try {
				if (msgListWrapper.size() > 0) {
					consumeMsgList(msgListWrapper);
				}
				break;
			} catch (Exception ex) {
				//从当前出错元素到最后放到wrapper的msgList中重新消费，可能导致死循环
				//程序中这样用主要是因为kafka只能连续抛出三次KafkaConsumeRetryException
				msgListWrapper.resetForRetry();//dead loop
			}
		}
	}
	
	public static void consumeMsgList(MsgListWrapper<String> msgListWrapper)
			throws NoSuchElementException {
		for (String str : msgListWrapper) {
			if (str.matches("4|6|8")) {
				throw new NoSuchElementException();
			} else {
				System.out.println(str);
			}
		}
	}
	
	public static void main(String[] args) {
		
//		test1();
//		test2();
		test3();
		
	}

}