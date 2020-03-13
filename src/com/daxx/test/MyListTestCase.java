package com.daxx.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.daxx.abstracts.MyListJ;
import com.daxx.abstracts.MyPredicate;
import com.daxx.impl.MyList;
import com.daxx.impl.MyTestObj;

public class MyListTestCase {
	
	private static MyListJ<Integer> myList, myList1, myList2, myList3, myList4;
	
	@BeforeClass
	public static void before() {
		myList = new MyList<>();
		myList1 = myList.add(1);
		myList2 = myList1.add(2);
		myList3 = myList2.add(3);
		myList4 = myList3.add(4);
		
	}

	@Test
	public void testAdd() {
		assertEquals(4, ((MyList<Integer>)myList4).size());
		Integer[] expected = {1, 2, 3, 4};
		assertArrayEquals(expected, ((MyList<Integer>)myList4).toArray());
	}

	@Test
	public void testHead() {
		int actual = myList4.head();
		assertEquals(1, actual);
	}

	@Test
	public void testTail() {
		Integer[] expected = {2, 3, 4};
		assertArrayEquals(expected, ((MyList<Integer>)myList4.tail()).toArray());
	}

	@Test
	public void testSize1Head() {
		int actual = myList1.head();
		assertEquals(1, actual);
	}

	@Test
	public void testSize1Tail() {
		assertTrue(myList1.tail().isEmpty());
	}

	@Test
	public void testEmpty() {
		assertTrue(myList.isEmpty());
		assertNull(myList.head());
		assertTrue(myList.tail().isEmpty());
	}
	
	@Test
	public void testFilter() {
		MyPredicate<Integer> even = (a) -> a % 2 == 0 ? a : null;
		MyListJ<Integer> myList5 = myList4.filter(even);
		Integer[] expected = {2, 4};
		assertArrayEquals(expected, ((MyList<Integer>)myList5).toArray());
	}
	
	@Test
	public void testTransformer() {
		MyPredicate<Integer> square = (a) -> a*a;
		MyListJ<Integer> myList5 = myList4.filter(square);
		Integer[] expected = {1, 4, 9, 16};
		assertArrayEquals(expected, ((MyList<Integer>)myList5).toArray());
	}
	
	@Test
	public void testImutability() {
		MyListJ<Integer> myList5 = myList4.tail();
		myList5 = myList5.add(5);
		Integer[] expected = {1, 2, 3, 4};
		assertArrayEquals(expected, ((MyList<Integer>)myList4).toArray());
	}

	@Test
	public void testDeepImutability() {
		MyListJ<MyTestObj> myList6 = new MyList<>();
		myList6 = myList6.add(new MyTestObj(2, "white"));
		myList6 = myList6.add(new MyTestObj(3, "black"));
		MyTestObj obj = myList6.head();
		obj.setColor("yellow");
		assertEquals("white", myList6.head().getColor());
	}

}
