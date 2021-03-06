package com.hy.objects;

public class Color {

    private int serverId;
	private String brandName;
	private String seriesName;
	private String name;
	private byte[] bitmapBytes;
	
	public Color(int serverId,String brandName,String seriesName,String name,byte[]bitmapBytes){
	    this.serverId = serverId;
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
	public int getServerId(){
	    return serverId;
	}
}
