package com.hy.objects;

public class Series {

	private String brandName;
	private String name;
	private String indro;
	private byte[] bitmapBytes;
	
	public Series(String brandName,String name,byte[]bitmapBytes,String indro){
		this.brandName = brandName;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
		this.indro = indro;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}
	
	public String getIndro(){
		return indro;
	}
	
}
