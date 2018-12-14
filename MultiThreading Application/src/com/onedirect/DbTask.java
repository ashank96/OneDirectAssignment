
 /*
 * DbName: Inventory
 * TableName: Item
 * username:root
 * password:
 */
package com.onedirect;
import java.util.ArrayList;




import java.sql.*;

public class DbTask{
	ArrayList<Item> retrievedItems;
	ArrayList<Item> taxedItems;
	
	DbTask(){
		retrievedItems=new ArrayList<>();
		taxedItems=new ArrayList<>();
	}
	
	public ArrayList<Item> getTaxedItems() {
		return taxedItems;
	}

	public Connection connect(){   //db connection method
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver loading error");
		}
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/inventory","root", "");
			System.out.println("Db connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in getting connection");
		}
		return conn;
	}
	
	public void retriveItems(Connection conn) throws InterruptedException { //thread 1 method which retrieves data from db and creates item object 
		Statement statement=null;
		ResultSet result=null;
		try {
			statement=conn.createStatement();
			result=statement.executeQuery("Select * from item");
	
			while(result.next()) {
				synchronized(this) {
					
					Item item=new Item();
					item.setName(result.getString("name"));
					System.out.println("Thread1: Retrieving item "+item.getName()+" data from the db");
					item.setType(result.getInt("type"));
					item.setPrice(result.getDouble("price"));
					item.setQuantity(result.getInt("quantity"));
					
					retrievedItems.add(item);
					
					notify();
					
					Thread.sleep(500);
					wait();
					
				}
			}
			
			conn.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in creating sql statement");
		}
		
 }
	
	public void taxItems() throws InterruptedException {  //thread 2 method which imposes tax on items and adds final info into item attribute
		int i=0;
		while(true) {
			synchronized (this) {
				wait(2000);
				Item item=new Item();
				try {
				item=retrievedItems.get(i++);
				}catch(IndexOutOfBoundsException e) {
					System.out.println("All items saved to other collection!");
					break;
				}
				System.out.println("Thread2: Item "+item.getName()+" retrieved,taxed and saved");
				item.setTax();
				taxedItems.add(item);
				notify();
				Thread.sleep(500);
			}
		}
	}
	
	
}
