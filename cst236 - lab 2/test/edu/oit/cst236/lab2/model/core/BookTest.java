package edu.oit.cst236.lab2.model.core;

import org.junit.Assert;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;

import edu.oit.cst236.lab2.model.core.Book;
import edu.oit.cst236.lab2.model.core.InvalidBookException;


public class BookTest {
	
	/******************************
	*
	* setup
	*
	*******************************/
	
	private Book sut;
	
	@Before
	public void setup() throws InvalidBookException{
		sut = new Book("id", "title");
	}
	
	
	/****************
	*
	* Ctor Tests
	*
	*****************/
	// TODO make sure to test cases where the ctor is null or empty strings or both, etc...should be about 6 cases
	//
	//
	@Test (expected = InvalidBookException.class)
	public void testCTOR_Both_Null() throws InvalidBookException {
		sut = new Book(null, null);
	}
	
	@Test (expected = InvalidBookException.class)
	public void testCTOR_Both_Empty() throws InvalidBookException {
		sut = new Book("","");
	}
	
	@Test (expected = InvalidBookException.class)
	public void testCTOR_ID_Null() throws InvalidBookException {
		sut = new Book(null,"null id");
	}
	
	@Test (expected = InvalidBookException.class)
	public void testCTOR_Name_Null() throws InvalidBookException {
		sut = new Book("null name",null);
	}
	
	@Test (expected = InvalidBookException.class)
	public void testCTOR_ID_Empty() throws InvalidBookException {
		sut = new Book("","empty id");
	}
	
	@Test (expected = InvalidBookException.class)
	public void testCTOR_Name_Empty() throws InvalidBookException {
		sut = new Book("empty name","");
	}
	
	
	/****************
	*
	* Get ID Tests
	*
	*****************/
	
	// happy test
	@Test
	public void testGetID() {
		String id = sut.getId();
		Assert.assertNotNull(id);
	}
	
	
	/****************
	*
	* Get Title Tests
	*
	*****************/
	
	// happy test
	@Test
	public void GetTitle() {
		String title = sut.getTitle();
		Assert.assertNotNull(title);
	}
	
	
	/******************************
	*
	* Get and Set Description Tests
	*
	*******************************/
	
	// test book description set and get work together
	@Test
	public void GetSetDescription() throws InvalidBookException {
		sut.setDescription("book description test");
		Assert.assertEquals("book description test", sut.getDescription());
	}
	
	
	@Test (expected = InvalidBookException.class)
	public void SetDescription_Decscription_Null() throws InvalidBookException {
		sut.setDescription(null);
	}
	
	/*
	@Test (expected = InvalidBookException.class)
	public void SetDescription_Decscription_EMPTY() throws InvalidBookException {
		sut.setDescription("");
		Assert.assertEquals("", sut.getDescription());
		Assert.assertNotNull(sut.getDescription());
	}*/
}
