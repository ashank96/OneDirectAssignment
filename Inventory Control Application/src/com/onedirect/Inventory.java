
/* Inventory Mangement System

 * author: Ashank Bharati
 * date: 29-11-18
 *
 *  */
package com.onedirect;
import java.io.*;

public class Inventory{
	public static void main(String args[]) {
		
		String choice="y";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int i=0;
		do {
			i++;
			Item item=new Item();
			System.out.println("Enter your item "+i+" details: ");
			System.out.println("Name: ");
			try {
				item.setName(br.readLine());
			}catch(IOException e) {
				System.out.println("Name Input Error");
			}
			System.out.println("Price: ");
			try {
				item.setPrice(Double.parseDouble(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Price Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.println("Quantity: ");
			try {
				item.setQuantity(Integer.parseInt(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Quantity Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.println("Choose for Type(1,2,3):\n1. Raw\n2. Manufactured\n3. Imported\n");
			try {
				item.setType(Integer.parseInt(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Type Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.println("Showing item "+i+" details: ");
			System.out.println(item.toString());
			
			
			System.out.println("Do you want to enter for another item?(y/n):");
			try {
				choice=br.readLine();
				if(choice.toLowerCase().equals("n")) {
					System.out.println("Exiting....Good Bye!");
					break;
				}
				else if(!choice.toLowerCase().equals("y")) {
					System.out.println("Invalid choice!");
					break;
				}
					
			} catch (IOException e) {
				System.out.println("Invalid choice");
			}
			
		}while(choice.toLowerCase().equals("y"));
		
	}
}
