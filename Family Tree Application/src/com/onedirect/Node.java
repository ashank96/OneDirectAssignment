package com.onedirect;
import java.util.*;

public class Node {
	private int id;
	private String name;
	private HashMap<String,String> info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, String> getInfo() {
		return info;
	}
	public void setInfo(HashMap<String, String> info) {
		this.info = info;
	}
	@Override
	public int hashCode() {
		return id;
	}
}
