package com.hy.objects;

public class Brand {

	private String name;
	private byte[] bitmapBytes;
	
	public Brand(String name, byte[] bitmapBytes){
		this.name = name;
		this.bitmapBytes = bitmapBytes;
	}
	
	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}
}
