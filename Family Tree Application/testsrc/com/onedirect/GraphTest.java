package com.onedirect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	Graph graph;
	@BeforeEach
	void setUp() throws Exception {
		graph=new Graph();
		
	}

	@Test
	void testAddNode() {
		graph.addNode(1, "node1", "key", "value");
		graph.addNode(2, "node2", "key", "value");
	
		assertEquals(2, graph.getNodes().size());
		
	}

	@Test
	void testAddDependency() {
		graph.addNode(1, "node1", "key", "value");
		graph.addNode(2, "node2", "key", "value");
		graph.addDependency(1, 2);
		assertEquals(true,graph.dependencyExists(1, 2));
	}

	@Test
	void testDeleteDependency() {
		graph.addNode(1, "node1", "key", "value");
		graph.addNode(2, "node2", "key", "value");
		graph.addDependency(1, 2);
		graph.deleteDependency(1, 2);
		assertEquals(false,graph.dependencyExists(1, 2));
	}

	@Test
	void testDeleteNode() {
		graph.addNode(1, "node1", "key", "value");
		graph.addNode(2, "node2", "key", "value");
		graph.deleteNode(2);
		graph.deleteNode(1);
		assertEquals(0,graph.getNodes().size());
	}

	@Test
	void testIsDescendent() {
		graph.addNode(1, "node1", "key", "value");
		graph.addNode(2, "node2", "key", "value");
		graph.addNode(3, "node3", "key", "value");
		graph.addDependency(1, 2);
		graph.addDependency(2, 3);
		
		assertEquals(true, graph.isDescendent(1, 3) );
	}

}
