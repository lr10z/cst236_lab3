package edu.oit.cst236.lab2.model.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.oit.cst236.lab2.model.core.Library;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;

public class LibraryTest {
	
	/******************************
	*
	* setup
	*
	*******************************/
	private Library sut;
	
	@Before
	public void setup() throws InvalidLibraryException{
		sut = new Library("id", "name");
	}
	
	
	/****************
	*
	* Ctor Tests
	*
	*****************/
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_Both_Null() throws InvalidLibraryException {
		sut = new Library(null, null);
	}
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_Both_Empty() throws InvalidLibraryException {
		sut = new Library("","");
	}
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_ID_Null() throws InvalidLibraryException {
		sut = new Library(null,"null id");
	}
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_Name_Null() throws InvalidLibraryException {
		sut = new Library("null name",null);
	}
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_ID_Empty() throws InvalidLibraryException {
		sut = new Library("","empty id");
	}
	
	@Test (expected = InvalidLibraryException.class)
	public void testCTOR_Name_EmptyR() throws InvalidLibraryException {
		sut = new Library("empty name","");
	}
	
	/****************
	*
	* Get Name Tests
	*
	*****************/
	
	// id happy test
	@Test
	public void testGetID() throws InvalidLibraryException {
		String id = sut.getId();
		Assert.assertNotNull(id);
	}
	
	
	/****************
	*
	* Set Name Tests
	*
	*****************/
	
	// happy test
	@Test
	public void testSetNAME() throws InvalidLibraryException {
		String name = sut.getName();
		Assert.assertNotNull(name);
	}
	
	// name is null test
	 @Test (expected = InvalidLibraryException.class)
	public void testSetNAME_Name_Null() throws InvalidLibraryException {
		sut.setName(null);
	}
	
	// name is empty test
	 @Test (expected = InvalidLibraryException.class)
	public void testSetNAME_Name_Empty() throws InvalidLibraryException {
		sut.setName("");
	}
	
	
	/****************
	*
	* Get Name Tests
	*
	*****************/
	
	//  name happy test
	@Test
	public void testGet_Name() throws InvalidLibraryException {
		String name = sut.getName();
		Assert.assertNotNull(name);
	}
	
	
	/***********************************
	*
	* Set and Get Available Books Tests
	*
	************************************/
	
	// happy - set available books
	@Test 
	public void testSetGet_AVAILABLEBOOKS() throws InvalidLibraryException {
		sut.setAvailableBooks(0);
		Assert.assertEquals(0, sut.getAvailableBooks());
	}
	
	// fail test
	@Test (expected = InvalidLibraryException.class)
	public void testSet_AVAILABLEBOOKS_Negative_Num() throws InvalidLibraryException {
		sut.setAvailableBooks(-1);
	}
	
	/************************************
	*
	* Set and Get Unavailable Books Tests
	*
	*************************************/
	
	// set unavailable books
	@Test
	public void testSetGet_UNAVAILABLEBOOKS() throws InvalidLibraryException {
		sut.setUnavailableBooks(0);
		Assert.assertEquals(0, sut.getUnavailableBooks());
	}
	
	// fail test
	@Test (expected = InvalidLibraryException.class)
	public void testSetUNAVAILABLEBOOKS() throws InvalidLibraryException {
		sut.setUnavailableBooks(-1);
	}
} 
