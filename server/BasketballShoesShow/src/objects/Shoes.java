package objects;

import sun.misc.BASE64Encoder;

public class Shoes {
	private int serverId;
	private String brand;
	private String series;
	private String generation;
	private String color;
	private String name;
	private byte[] bitmapBytes;
	private int price;
	private String season;
	private String upper;
	private String upperMaterial;
	private String lowMaterial;
	private String function;
	private String position;
	private String sex;
	private String technology;
	private String indro;
	
	public Shoes(int serverId,String brand,String serise,String generation,String color,String name,
			byte[] bitmapBytes, int price,String season,String upper,String upperMaterial,
			String lowMaterial,String function,String position,String sex,String technology,
			String indro){
		this.serverId = serverId;
		this.brand = brand;
		this.series = serise;
		this.generation = generation;
		this.color = color;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
		this.price = price;
		this.season = season;
		this.upper = upper;
		this.upperMaterial = upperMaterial;
		this.lowMaterial = lowMaterial;
		this.function = function;
		this.position = position;
		this.sex = sex;
		this.technology = technology;
		this.indro = indro;
	}
	
	public Shoes(String brand,String serise,String generation,String color,String name,
			byte[] bitmapBytes, int price,String season,String upper,String upperMaterial,
			String lowMaterial,String function,String position,String sex,String technology,
			String indro){
		this.brand = brand;
		this.series = serise;
		this.generation = generation;
		this.color = color;
		this.name = name;
		this.bitmapBytes = bitmapBytes;
		this.price = price;
		this.season = season;
		this.upper = upper;
		this.upperMaterial = upperMaterial;
		this.lowMaterial = lowMaterial;
		this.function = function;
		this.position = position;
		this.sex = sex;
		this.technology = technology;
		this.indro = indro;
	}

	public String getBrand() {
		return brand;
	}

	public String getSeries() {
		return series;
	}

	public String getGeneration() {
		return generation;
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public byte[] getBitmapBytes() {
		return bitmapBytes;
	}

	public int getPrice() {
		return price;
	}

	public String getSeason() {
		return season;
	}

	public String getUpper() {
		return upper;
	}

	public String getUpperMaterial() {
		return upperMaterial;
	}

	public String getLowMaterial() {
		return lowMaterial;
	}

	public String getFunction() {
		return function;
	}

	public String getPosition() {
		return position;
	}

	public String getSex() {
		return sex;
	}

	public String getTechnology() {
		return technology;
	}

	public String getIndro() {
		return indro;
	}
	
	public int getServerId(){
		return serverId;
	}
	
	public String getBitmapString(){
		BASE64Encoder base64  =new BASE64Encoder();
		return base64.encode(bitmapBytes);
	}
}
