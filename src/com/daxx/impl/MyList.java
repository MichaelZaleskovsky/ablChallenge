package com.daxx.impl;

import java.util.ArrayList;

import com.daxx.abstracts.MyListJ;
import com.daxx.abstracts.MyPredicate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * Challenge task for abl.
 * @autor Mikhail Zaleskovskiy
*/
public class MyList<A> extends MyListJ<A> {
	
	private class Node<T> {
		T body;
		Node<T> next;
		
		Node(T t) {
			this.body = t;
			this.next = null;
		}
		
		@SuppressWarnings("unchecked")
		T getBody() {
			return (T) deepClone(body);
		}
	}
	
	private Node<A> root;
	private Node<A> last;
	private int size;
	
	public MyList() {
		this.root = null;
		this.last = null;
		this.size = 0;
	}
	
	private MyListJ<A> cloneList(MyListJ<A> list) {
		return filter((a) -> a);
	}
	
	/* Solution for abstract class instead of interface
	
	private MyListJ<A> cloneList(MyListJ<A> list) {
		return filter(new MyPredicate<A>() {
			@Override
			public A test(A value) {
				return value;
			}
		});
	}
	*/
	
	private Object deepClone(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(object);
			return mapper.readValue(json, object.getClass());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void innerAdd(A value) {
		Node<A> node = new Node<>(value);
		if (isEmpty()) {
			this.root = node; 
		} else {
			this.last.next = node;
		}
		this.last = node;
		this.size++;
	}
	
    /** 
     * Return quantity of elements in list
     * @return int
     */
	public int size() {
		return this.size;
	}
	
    /** 
     * Return new list containing last n elements of source list.
     * If n >= size return new list with same quantity of elements as source. 
     * @param int 
     * @return MyListJ<A>
     */
    @SuppressWarnings("unchecked")
	public MyListJ<A> tail(int n){
		if (n >= size) return cloneList(this);
		MyList<A> newList = new MyList<>();
		Node<A> current = this.root;
		A element;
		for (int i = 1; i <= size; i++) {
			element = current.getBody();
			current = current.next;
			if (i > size - n) {
				newList.innerAdd(element);
			}
		}
    	return newList;		
	};
	
    /** 
     * Return array containing all elements of source list. 
     * @return A[]
     */
	@SuppressWarnings("unchecked")
	public A[] toArray() {
		ArrayList<A> list = new ArrayList<>();
		Node<A> current = this.root;
		while(current != null) {
			list.add(current.getBody());
			current = current.next;
		}
		return (A[]) list.toArray();
	}
	
    /** 
     * Return first element in list or NULL if list is empty
     * @return A
     */
	@Override
    public A head() {
    	return isEmpty() ? null : root.getBody();
    };
	
    /** 
     * Return new list containing all elements except first. 
     * If source list is empty or contains 1 element return empty list.
     * @return MyListJ<A>
     */
	@Override
    public MyListJ<A> tail(){
    	return tail(size - 1);		
	};
	
    /** 
     * Return TRUE if list is empty and FALSE in other cases. 
     * @return boolean
     */
	@Override
    public Boolean isEmpty() {
		return root == null;
	};
	
    /** 
     * Return new list containing all elements of source list plus addition one. 
     * @param A
     * @return MyListJ<A>
     */
	@SuppressWarnings("unchecked")
	@Override
    public MyListJ<A> add(A value){
		MyList<A> newList = (MyList<A>) cloneList(this);
		if (newList == null) newList = new MyList<>();
		newList.innerAdd(value);
		return newList;
	};

	
    /** 
     * Return new list containing elements obtained by method test() of functional interface MyPredicate.
     * If method test() return NULL corresponding element will be removed from result list. 
     * @param MyPredicate<A>
     * @return MyListJ<A>
     */
	@Override
    public MyListJ<A> filter(MyPredicate<A> predicate){
		MyList<A> newList = new MyList<A>();
		Node<A> current = this.root;
		A tempo;
		while(current != null) {
			tempo = current.getBody();
			if (predicate.test(tempo) != null) {
				newList.innerAdd(predicate.test(tempo));
			}
			current = current.next;
		}
		return newList;
	};

}
