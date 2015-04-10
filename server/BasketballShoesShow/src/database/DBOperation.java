package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Brand;
import objects.Color;
import objects.Series;
import objects.Shoes;

public class DBOperation {
	
	private int startServerId;
	private String brand_name;
	private String series_name;
	private String color_name;
	private String shoes_name;
	private Connection con;
	
	public DBOperation(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void setStartServerId(int startServerId){
		this.startServerId = startServerId;
	}
	
	public void setBrandName(String brand_name){
		this.brand_name = brand_name;
	}
	public void setSeriesName(String series_name){
		this.series_name = series_name;
	}
	public void setColorName(String color_name){
		this.color_name = color_name;
	}
	public void setshoesName(String shoes_name){
		this.shoes_name = shoes_name;
	}
	
	public ArrayList<Brand> getBrands() {

		ArrayList<Brand> list = new ArrayList<Brand>();
		String sql = "select * from brand where _id > "+startServerId+" limit " + 0 + "," + 20;
		System.out.println(sql);
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String name = null;
			byte[] bitmapBytes;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				name = rs.getString("brand_name");
				bitmapBytes = rs.getBytes("brand_pic");
				Brand brand = new Brand(serverId,name, bitmapBytes);
				list.add(brand);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Brand> getAllBrands() {

		ArrayList<Brand> list = new ArrayList<Brand>();
		String sql = "select * from brand";
		System.out.println(sql);
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String name = null;
			byte[] bitmapBytes;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				name = rs.getString("brand_name");
				bitmapBytes = rs.getBytes("brand_pic");
				Brand brand = new Brand(serverId,name, bitmapBytes);
				list.add(brand);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Series> getSeries() {
		ArrayList<Series> list = new ArrayList<Series>();
		String sql = "select * from series where brand_name = '"+brand_name+"' and _id > "+startServerId+" limit " + 0 + "," + 20;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String brandName = null;
			String name = null;
			String indro = null;
			byte[] bitmapBytes;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				brandName = rs.getString("brand_name");
				name = rs.getString("series_name");
				indro = rs.getString("series_indro");
				bitmapBytes = rs.getBytes("series_pic");
				Series series = new Series(serverId,brandName, name, bitmapBytes, indro);
				list.add(series);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Series> getAllSeries() {
		ArrayList<Series> list = new ArrayList<Series>();
		String sql = "select * from series where brand_name = '"+brand_name+"'";
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String brandName = null;
			String name = null;
			String indro = null;
			byte[] bitmapBytes;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				brandName = rs.getString("brand_name");
				name = rs.getString("series_name");
				indro = rs.getString("series_indro");
				bitmapBytes = rs.getBytes("series_pic");
				Series series = new Series(serverId,brandName, name, bitmapBytes, indro);
				list.add(series);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<Color> getColors() {
		ArrayList<Color> list = new ArrayList<Color>();
		String sql = "select * from color where brand_name = '"+brand_name+"' and series_name = '"+series_name+"' and _id > "+startServerId+" limit " + 0 + "," + 20;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String brandName = null;
			String seriesName = null;
			String name = null;
			byte[] bitmapBytes;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				brandName = rs.getString("brand_name");
				seriesName = rs.getString("series_name");
				name = rs.getString("color_name");
				bitmapBytes = rs.getBytes("color_pic");
				Color color = new Color(serverId,brandName, seriesName,name, bitmapBytes);
				list.add(color);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Shoes> getShoes() {
		ArrayList<Shoes> list = new ArrayList<Shoes>();
		String sql = "select * from shoes where brand_name = '"+brand_name+"' and series_name = '"+series_name+"' and color_name = '"+color_name+"' and _id > "+startServerId+" limit " + 0 + "," + 20;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + "shoesShow" + "?user=" + "root" + "&password=" + "huangyue");
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			int serverId;
			String brandName = null;
			String seriesName = null;
			String colorName = null;
			String name = null;
			byte[] bitmapBytes;
			int shoes_price = 0;
			String shoes_season = null;
			String shoes_upper = null;
			String shoes_upperMaterial = null;
			String shoes_lowMaterial = null;
			String shoes_function = null;
			String shoes_oisition = null;
			String shoes_sex = null;
			String shoes_technology = null;
			String shoes_indro = null;
			while (rs.next()) {
				serverId = rs.getInt("_id");
				brandName = rs.getString("brand_name");
				seriesName = rs.getString("series_name");
				colorName = rs.getString("color_name");
				name = rs.getString("shoes_name");
				bitmapBytes = rs.getBytes("shoes_pic");
				shoes_price = rs.getInt("shoes_price");
				shoes_season = rs.getString("shoes_season");
				shoes_upper = rs.getString("shoes_upper");
				shoes_upperMaterial = rs.getString("shoes_upperMaterial");
				shoes_lowMaterial = rs.getString("shoes_lowMaterial");
				shoes_function = rs.getString("shoes_function");
				shoes_oisition = rs.getString("shoes_oisition");
				shoes_sex = rs.getString("shoes_sex");
				shoes_technology = rs.getString("shoes_technology");
				shoes_indro = rs.getString("shoes_indro");
				Shoes shoes = new Shoes(serverId,brandName, seriesName, colorName, name, bitmapBytes, shoes_price, shoes_season, shoes_upper, shoes_upperMaterial, shoes_lowMaterial, shoes_function, shoes_oisition, shoes_sex, shoes_technology, shoes_indro);
				list.add(shoes);
			}
			//con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void insertBrand(Brand brand) throws SQLException {
	
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager
//					.getConnection("jdbc:mysql://localhost/" + "shoesShow"
//							+ "?user=" + "root" + "&password=" + "huangyue");
			String sql = "insert into brand (brand_name,brand_pic) values (?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, brand.getName());
			stat.setBytes(2, brand.getBitmapBytes());
			int n = stat.executeUpdate();
			//con.close();
	}

	public void insertSeries(Series series) throws SQLException {
	
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager
//					.getConnection("jdbc:mysql://localhost/" + "shoesShow"
//							+ "?user=" + "root" + "&password=" + "huangyue");
			String sql = "insert into series (brand_name,series_name,series_indro,series_pic) values (?,?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, series.getBrandName());
			stat.setString(2, series.getName());
			stat.setString(3, series.getIndro());
			stat.setBytes(4, series.getBitmapBytes());
			int n = stat.executeUpdate();
			//con.close();
	
	}

	public void insertColor(Color color) throws SQLException {

//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager
//					.getConnection("jdbc:mysql://localhost/" + "shoesShow"
//							+ "?user=" + "root" + "&password=" + "huangyue");
			String sql = "insert into color (brand_name,series_name,color_name,color_pic) values (?,?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, color.getBrandName());
			stat.setString(2, color.getSeriesName());
			stat.setString(3, color.getName());
			stat.setBytes(4, color.getBitmapBytes());
			int n = stat.executeUpdate();
			//con.close();

	}

	public void insertShoes(Shoes shoes) throws SQLException {

//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager
//					.getConnection("jdbc:mysql://localhost/" + "shoesShow"
//							+ "?user=" + "root" + "&password=" + "huangyue");
			String sql = "insert into shoes (brand_name,series_name,color_name,shoes_name,shoes_pic,shoes_price,shoes_season,shoes_upper,shoes_upperMaterial,shoes_lowMaterial,"
					+ "shoes_function,shoes_oisition,shoes_sex,shoes_technology,shoes_indro) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, shoes.getBrand());
			stat.setString(2, shoes.getSeries());
			stat.setString(3, shoes.getColor());
			stat.setString(4, shoes.getName());
			stat.setBytes(5, shoes.getBitmapBytes());
			stat.setInt(6, shoes.getPrice());
			stat.setString(7, shoes.getSeason());
			stat.setString(8, shoes.getUpper());
			stat.setString(9, shoes.getUpperMaterial());
			stat.setString(10, shoes.getLowMaterial());
			stat.setString(11, shoes.getFunction());
			stat.setString(12, shoes.getPosition());
			stat.setString(13, shoes.getSex());
			stat.setString(14, shoes.getTechnology());
			stat.setString(15, shoes.getIndro());
			int n = stat.executeUpdate();
			//con.close();
	}
	
	
	public int delete(String table,String serverId) throws SQLException{
		String sql = "delete from "+table+" where _id='"+serverId+"'";
		PreparedStatement stat = con.prepareStatement(sql);
		return stat.executeUpdate();
	}
	
	public Object getOneCategory(String table,String serverId) throws SQLException{
		String sql = null;
		System.out.println(table+":"+serverId);
		if("brand".equals(table)){
			sql = "select * from brand where _id='"+serverId+"'";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("_id");
			String name = rs.getString("brand_name");
			byte[] bitmapBytes = rs.getBytes("brand_pic");
			Brand brand = new Brand(id,name, bitmapBytes);
			return brand;
		}else if("series".equals(table)){
			sql = "select * from series where _id='"+serverId+"'";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("_id");
			String brandName = rs.getString("brand_name");
			String name = rs.getString("series_name");
			String indro = rs.getString("series_indro");
			byte[] bitmapBytes = rs.getBytes("series_pic");
			Series series = new Series(id,brandName, name, bitmapBytes, indro);
			return series;
		}else if ("color".equals(table)) {
			sql = "select * from color where _id='"+serverId+"'";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("_id");
			String brandName = rs.getString("brand_name");
			String seriesName = rs.getString("series_name");
			String name = rs.getString("color_name");
			byte[] bitmapBytes = rs.getBytes("color_pic");
			Color color = new Color(id,brandName, seriesName,name, bitmapBytes);
			return color;
		}else{
			sql = "select * from shoes where _id='"+serverId+"'";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("_id");
			String brandName = rs.getString("brand_name");
			String seriesName = rs.getString("series_name");
			String colorName = rs.getString("color_name");
			String name = rs.getString("shoes_name");
			byte[] bitmapBytes = rs.getBytes("shoes_pic");
			int shoes_price = rs.getInt("shoes_price");
			String shoes_season = rs.getString("shoes_season");
			String shoes_upper = rs.getString("shoes_upper");
			String shoes_upperMaterial = rs.getString("shoes_upperMaterial");
			String shoes_lowMaterial = rs.getString("shoes_lowMaterial");
			String shoes_function = rs.getString("shoes_function");
			String shoes_oisition = rs.getString("shoes_oisition");
			String shoes_sex = rs.getString("shoes_sex");
			String shoes_technology = rs.getString("shoes_technology");
			String shoes_indro = rs.getString("shoes_indro");
			Shoes shoes = new Shoes(id,brandName, seriesName, colorName, name, bitmapBytes, shoes_price, shoes_season, shoes_upper, shoes_upperMaterial, shoes_lowMaterial, shoes_function, shoes_oisition, shoes_sex, shoes_technology, shoes_indro);
			return shoes;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public String checkInfos(){
		String string = null;
		if(checkBrand(brand_name)){
			string = "brand";
			if(checkSeries(brand_name, series_name)){
				string = "series";
				if(checkColor(brand_name, series_name, color_name)){
					string = "color";
					if(checkShoes(brand_name, series_name, color_name, shoes_name)){
						string = "shoes";
					}
				}
			}
		}
		return string;
	}
	
	private boolean checkBrand(String brandName){
		boolean flag = true;
		String sql = "select 1 from brand where brand_name='"+brandName+"' limit 1";
		try{
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			flag = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	private boolean checkSeries(String brandName,String seriesName){
		boolean flag = true;
		String sql = "select 1 from series where brand_name='"+brandName+"' and series_name='"+seriesName+"' limit 1";
		try{
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			flag = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean checkColor(String brandName,String seriesName,String colorName){
		boolean flag = true;
		String sql = "select 1 from color where brand_name='"+brandName+"' and series_name='"+seriesName+"' and color_name='"+colorName+"' limit 1";
		try{
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			flag = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean checkShoes(String brandName,String seriesName,String colorName,String shoesName){
		boolean flag = true;
		String sql = "select 1 from shoes where brand_name='"+brandName+"' and series_name='"+seriesName+"' and color_name='"+colorName+"' and shoes_name='"+shoesName+"' limit 1";
		try{
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			flag = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
