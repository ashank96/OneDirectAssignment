package com.onedirect;
import java.io.*;
import java.util.ArrayList;

class SerializeTask {
	final String file="students.txt";
	public void write(ArrayList<Student> students) {
		try {
			FileOutputStream fo=new FileOutputStream(file);
			ObjectOutputStream out=new ObjectOutputStream(fo);
			out.writeObject(students);   //serializing whole arraylist of students
			System.out.println("All changes saved to the disk successfully");
			out.close();
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File"+file+" cannot be located");
		}catch (IOException e) {
			System.out.println("File I/O error");
		}
		
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Student> read() {
		ArrayList<Student> students=null;
		try {
			FileInputStream fi=new FileInputStream(file);
			ObjectInputStream in=new ObjectInputStream(fi);
			students=(ArrayList<Student>)in.readObject(); //reading from serialized data arraylist of students
			in.close();
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File"+file+" cannot be located");
		}catch (IOException e) {
			System.out.println("File I/O error");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

}
