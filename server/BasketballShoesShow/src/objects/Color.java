package objects;

import sun.misc.BASE64Encoder;

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

	public Color(String brandName,String seriesName,String name,byte[]bitmapBytes){
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
	
	public String getBitmapString(){
		BASE64Encoder base64  =new BASE64Encoder();
		return base64.encode(bitmapBytes);
	}
	public int getServerId(){
		return serverId;
	}
}
