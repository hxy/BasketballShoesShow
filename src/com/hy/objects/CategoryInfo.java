package com.hy.objects;


public class CategoryInfo {

	private int _id;
	private String tableName;
	
	public CategoryInfo(String tableName,int _id){
	        this.tableName = tableName;
	        this._id = _id;
	    }


	public String getTabaleName(){
	    return tableName;
	}
	
	public int getId(){
	    return _id;
	}

	public String getKey(){
		return tableName+_id;
	}
}
