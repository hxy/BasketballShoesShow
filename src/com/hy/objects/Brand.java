package com.hy.objects;

import android.R.integer;

public class Brand {

	private String name;
	private byte[] bitmapBytes;
	private int serverId;
	
	public Brand(int serverId,String name, byte[] bitmapBytes){
	    this.serverId = serverId;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
	}
	
	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}
	
	public int getServerId(){
	    return serverId;
	}
}
