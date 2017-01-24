package com.ecommerce.service;

import java.util.ArrayList;


public class cart {

	private int pid;
	private String name, desc, catagory;
	private int price;
	
	
	public cart(String cartStringObj)
	{
		this(cartStringObj.split(","));
	}
	
	public cart(String[] cartParam) {

		this.pid = Integer.parseInt(cartParam[0]);;
		this.name = cartParam[1];
		this.desc = cartParam[2];
		this.catagory = cartParam[3];
		this.price = Integer.parseInt(cartParam[4]);
	}
	
	public cart(int pid, String name, String catagory, int price, String desc) {

		this.pid = pid;
		this.name = name;
		this.desc = desc;
		this.catagory = catagory;
		this.price = price;
	}
	


	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "" + pid + "," + name + "," + desc
				+ "," + catagory + "," + price + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catagory == null) ? 0 : catagory.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pid;
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof cart)) {
			return false;
		}
		cart other = (cart) obj;
		if (catagory == null) {
			if (other.catagory != null) {
				return false;
			}
		} else if (!catagory.equals(other.catagory)) {
			return false;
		}
		if (desc == null) {
			if (other.desc != null) {
				return false;
			}
		} else if (!desc.equals(other.desc)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pid != other.pid) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		return true;
	}
	
	
	
	
}
