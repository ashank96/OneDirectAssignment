package com.onedirect;
import java.util.*;

public class Graph {
	
	private HashMap<Integer,LinkedList<Integer>> adjList; //stores ajacency lists of all the nodes
	private HashMap<Integer,Node>nodes;  //stores all the nodes of the graph

	private static boolean flag=false; //flag variable to monitor if a node is a descendent of other node
	
	Graph(){
		adjList=new HashMap<>();
		nodes=new HashMap<>();
	}
	
	public HashMap<Integer, LinkedList<Integer>> getAdjList() {
		return adjList;
	}

	public HashMap<Integer, Node> getNodes() {
		return nodes;
	}

	public static boolean isFlag() {
		return flag;
	}

	public void addNode(int id,String name,String key,String value) {
		
		if(isNodePresent(id)) {
			System.out.println("Node with entered id already exists. Please enter some other id\n");
			return;
		}
		
		// creating node
		Node node=new Node();
		node.setId(id);
		node.setName(name);
		HashMap<String,String>info=new HashMap<>(); //additional info in key value pair
		info.put(key, value);
		node.setInfo(info);
		
		nodes.put(id, node); 						 //adding node to the graph
		adjList.put(id, new LinkedList<>());         //initializing node with an adjacency list
		
		System.out.println("Added node successfully!");
	}
	
	public void addDependency(int parentId,int childId) {
		if(parentId==childId) {
			System.out.println("Parent and child ids cannot be same!");
			return;
		}
			
		if(!isNodePresent(parentId)) {
			System.out.println("Invalid parent node");
			return;
		}
		if(!isNodePresent(childId)) {
			System.out.println("Invalid child node");
			return;
		}
		
		if(dependencyExists(parentId,childId)) {
			System.out.println("Dependency already exists from parent Node: "+parentId+" to child Node : "+childId);
			return;
		}
		if(isDescendent(childId,parentId)) {
			System.out.println("Cannot add cyclic dependency :  Node: "+parentId+" is a descendent Node : "+childId);
			flag=false;
			return;
		}
		
		adjList.get(parentId).add(childId);	
		System.out.println("Added dependency successfully!");
	}
	
	public void deleteDependency(int parentId,int childId) {
		if(parentId==childId) {
			System.out.println("Parent and child ids cannot be same!");
			return;
		}
			
		if(!isNodePresent(parentId)) {
			System.out.println("Invalid parent node");
			return;
		}
		if(!isNodePresent(childId)) {
			System.out.println("Invalid child node");
			return;
		}
		
		if(!dependencyExists(parentId,childId)) {
			System.out.println("Dependency does not exists from parent Node: "+parentId+" to child Node :"+childId);
			return;
		}
		LinkedList<Integer> list=adjList.get(parentId);
		for(int i=0;i<list.size();i++)
			if(list.get(i)==childId)
				list.remove(i);
		System.out.println("Deleted dependency successfully!");
		
	}
	
	public void deleteNode(int nodeId) {
		if(!isNodePresent(nodeId)) {
			System.out.println("Node with given Id does not exist");
			return;
		}
		
		nodes.remove(nodeId);   							//deleting node from graph
		adjList.remove(nodeId); 					     	//deleting node's adjacency list
		for(LinkedList<Integer> list:adjList.values()) {   //deleting all dependencies
			for(int i=0;i<list.size();i++)
				if(list.get(i)==nodeId)
					list.remove(i);
		}
		System.out.println("Deleted node successfully!");
	}
	
	
	public void getImmidiateParents(int nodeId) {
		if(!isNodePresent(nodeId))
			System.out.println("Invalid node id");
		
		
		for(int key:adjList.keySet()) {
			if(adjList.get(key).contains(nodeId)) {
				System.out.println(key+": "+nodes.get(key).getName());
			}
		}
	}
	
	public void getDescendents(int nodeId) {
		if(!isNodePresent(nodeId))
			System.out.println("Invalid node id");
		
		for(int key:adjList.get(nodeId)) {
				System.out.println(key+": "+nodes.get(key).getName());
				getDescendents(key);	
		}
				
	}
	
	public void getAncestors(int nodeId) {
		
		if(!isNodePresent(nodeId))
			System.out.println("Invalid node id");
		
		for(int key:adjList.keySet()) {
			if(adjList.get(key).contains(nodeId)) {
				System.out.println(key+": "+nodes.get(key).getName());
				getAncestors(key);
			}
		}
		
	}
	
	public void getImmidiateChildren(int nodeId) {
		
		if(!isNodePresent(nodeId))
			System.out.println("Invalid node id");
		
	
		for(int key:adjList.get(nodeId)) {
				System.out.println(key+": "+nodes.get(key).getName());
		
		}
		
	}
	
	public boolean isNodePresent(int id) {
		if(adjList.containsKey(id))
			return true;
		return false;
	}
	public boolean dependencyExists(int parent,int child) {
		if(adjList.get(parent).contains(child))
			return true;
		
		return false;
	}
	
	boolean isDescendent(int parentId,int childId) {  // checks whether the child node is already a descendent of the parent node
		
		for(int key:adjList.get(parentId)) {
			if(key==childId) {
				flag=true;
				break;
			}
			isDescendent(key, childId);	
		}
		
		return flag;
	 }
}
