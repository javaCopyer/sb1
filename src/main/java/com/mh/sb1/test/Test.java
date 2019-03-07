package com.mh.sb1.test;

public class Test {
	static void say() throws BusineessException {
		throw new BusineessException("123");
	}
	
	public static void main(String[] args) {
		try {
			say();
		} catch (BusineessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
