package com.hy.objects;

public class Color {

	private String brandName;
	private String seriesName;
	private String generation;
	private String name;
	private byte[] bitmapBytes;
	
	public Color(String brandName,String seriesName,String generation,String name,byte[]bitmapBytes){
		this.brandName = brandName;
		this.seriesName = seriesName;
		this.generation = generation;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
	}
	
	public Color(String brandName,String seriesName,String name,byte[]bitmapBytes){
	        this.brandName = brandName;
	        this.seriesName = seriesName;
	        this.generation = null;
	        this.name = name;
	        this.bitmapBytes = bitmapBytes;
	    }

	public String getBrandName() {
		return brandName;
	}
	
	public String getSeriesName() {
		return seriesName;
	}

	public String getGeneration() {
		return generation;
	}

	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}
}
