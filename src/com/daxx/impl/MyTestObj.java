package com.daxx.impl;

import java.io.Serializable;

public class MyTestObj{
	private int size;
	private String color;
	
	public MyTestObj() {}
	
	public MyTestObj(int size, String color) {
		super();
		this.size = size;
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
