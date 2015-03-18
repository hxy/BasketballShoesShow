package objects;

import sun.misc.BASE64Encoder;

public class Series {
	private int serverId;
	private String brandName;
	private String name;
	private String indro;
	private byte[] bitmapBytes;
	
	public Series(int serverId,String brandName,String name,byte[]bitmapBytes,String indro){
		this.serverId = serverId;
		this.brandName = brandName;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
		this.indro = indro;
	}
	
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
	
	public String getBitmapString(){
		BASE64Encoder base64  =new BASE64Encoder();
		return base64.encode(bitmapBytes);
	}
	
	public int getServerId(){
		return serverId;
	}
}
