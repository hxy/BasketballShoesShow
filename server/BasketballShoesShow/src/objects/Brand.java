package objects;

import sun.misc.BASE64Encoder;

public class Brand {
	private int serverId;
	private String name;
	private byte[] bitmapBytes;
	
	public Brand(int serverId,String name, byte[] bitmapBytes){
		this.serverId = serverId;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
	}
	
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
	
	public String getBitmapString(){
		BASE64Encoder base64  =new BASE64Encoder();
		return base64.encode(bitmapBytes);
	}
	public int getServerId(){
		return serverId;
	}
	
}
