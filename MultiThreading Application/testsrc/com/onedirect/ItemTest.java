package com.onedirect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

	Item itemTest;
	@BeforeEach
	void setUp() throws Exception {
		itemTest=new Item();
	}

	@Test
	void testGetName() {
		itemTest.setName("ashank");
		assertEquals("ashank", itemTest.getName());
		
		itemTest.setName("ashank bharati");
		assertEquals("ashank bharati", itemTest.getName());
	}

	@Test
	void testGetType() {
		itemTest.setType(1);
		assertEquals("raw", itemTest.getType());
		
		itemTest.setType(2);
		assertEquals("manufactured", itemTest.getType());
		
		itemTest.setType(3);
		assertEquals("imported", itemTest.getType());
	}

	@Test
	void testGetPrice() {
		itemTest.setPrice(120.70);
		assertEquals(120.70, itemTest.getPrice());
	}

	@Test
	void testGetQuantity() {
		itemTest.setQuantity(120);
		assertEquals(120, itemTest.getQuantity());
	}

}
