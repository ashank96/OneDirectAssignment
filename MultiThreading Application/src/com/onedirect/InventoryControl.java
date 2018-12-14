package com.onedirect;
/* (Multithreading + jdbc) Inventory Control System
 * 
 * author: Ashank Bharati
 * date: 30-11-18
 *  */



import java.sql.Connection;
public class InventoryControl {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DbTask dbTask=new DbTask();
		Connection conn=dbTask.connect();
		if(conn==null) {
			System.out.println("Exiting...");
			return;
		}
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					dbTask.retriveItems(conn);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Retrieving thread interuppted");
				}
				
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					dbTask.taxItems();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Taxing items thread interuppted");
				}
				
			}
		});
		
		 t1.start(); 
	     t2.start(); 

	     t1.join(); 
	     t2.join(); 
	     
	     printItemsInfo(dbTask);  //prints all the item information
	}
	
	private static void printItemsInfo(DbTask dbTask) {
		System.out.println("\nAll the items are listed below");
		for(Item i:dbTask.getTaxedItems())
			System.out.println(i.toString()+"\n\n");
	}
	
	

}
