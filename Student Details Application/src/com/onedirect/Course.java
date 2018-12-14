package com.onedirect;
import java.util.Arrays;
import java.util.List;
import java.io.*;

class Course implements Serializable {

	private static final long serialVersionUID = -7163044138002514822L;
	static List<String> available=(List<String>) Arrays.asList("A","B","C","D","E","F");
	String type;
	Course(String type){
		this.type=type;
	}
	public String toString() {
		return type;
	}
}
