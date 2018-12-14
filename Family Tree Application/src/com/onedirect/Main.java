package com.onedirect;
/* Family Tree-Dependency Graph Console Application
 * author: Ashank Bharati
 * date: 30-11-18
 *  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int getIntegerInput(BufferedReader br) {
		int num=0;
		try {
			num=Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a number!...Exiting..! Run again!");
			System.exit(1);
			
		}catch (IOException e) {
			System.out.println("Number input error!...Exiting..! Run again!");
			System.exit(2);
			
		}
		return num;
	
	}
	
	private static String getStringInput(BufferedReader br) {
		String data=null;
		try {
			data=(br.readLine());
		} catch ( IOException e) {
		
			System.out.println("String input error!...Exiting!.. Run again!");
			System.exit(2);
			
		}
		return data;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph=new Graph();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		System.out.println("\nEnter choice\n"
				+ "1.Add a new Node\n"
				+ "2.Add a new Dependency\n"
				+ "3.Delete a Node\n"
				+ "4.Delete a dependency\n"
				+ "5.Get Immidiate Parents\n"
				+ "6.Get Immidiate Children\n"
				+ "7.Get Ancestors\n"
				+ "8.Get Descendents\n"
				+ "9.Exit");
		int choice=getIntegerInput(br);
		
		switch(choice) {
			case 1: System.out.println("Enter node id");
					int id=getIntegerInput(br);
					System.out.println("Enter node name");
					String name=getStringInput(br);
					System.out.println("Enter key for additional info");
					String key=getStringInput(br);
					System.out.println("Enter additional info");
					String value=getStringInput(br);	
					graph.addNode(id, name, key, value);		
				break;
				
			case 2:	System.out.println("Enter parent node id");
				   	int pid=getIntegerInput(br);
				   	System.out.println("Enter child node id");
				   	int cid=getIntegerInput(br);
				   	graph.addDependency(pid, cid);
				break;
				
			case 3: System.out.println("Enter node id");
					id=getIntegerInput(br);
					graph.deleteNode(id);
				break;
	
			case 4: System.out.println("Enter parent node id");
		   			pid=getIntegerInput(br);
		   			System.out.println("Enter child node id");
		   			cid=getIntegerInput(br);
		   			graph.deleteDependency(pid, cid);
				break;
			case 5: System.out.println("Enter node id");
					id=getIntegerInput(br);
					graph.getImmidiateParents(id);
				break;
			case 6: System.out.println("Enter node id");
					id=getIntegerInput(br);
					graph.getImmidiateChildren(id);
				break;
			case 7:	System.out.println("Enter node id");
					id=getIntegerInput(br);
					graph.getAncestors(id);
				break;
			case 8:	System.out.println("Enter node id");
					id=getIntegerInput(br);
					graph.getDescendents(id);
				break;
			case 9: System.out.println("Exiting...Good Bye!");
					System.exit(0);
				break;
			default: System.out.println("Invalid Choice\n");
		
		}
		
		}

	}
	

}
