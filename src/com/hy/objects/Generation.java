package com.hy.objects;

public class Generation {
	
	private String brandName;
	private String seriesName;
	private String name;
	private byte[] bitmapBytes;
	
	public Generation(String brandName,String seriesName,String name,byte[]bitmapBytes){
		this.brandName = brandName;
		this.seriesName = seriesName;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
	}

	public String getBrandName() {
		return brandName;
	}
	
	public String getSeriesName() {
		return seriesName;
	}

	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}
}
