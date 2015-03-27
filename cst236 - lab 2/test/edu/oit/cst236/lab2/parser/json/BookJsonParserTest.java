package edu.oit.cst236.lab2.parser.json;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.oit.cst236.lab2.model.IBook;
import edu.oit.cst236.lab2.model.core.InvalidBookException;
import edu.oit.cst236.lab2.parser.ParseException;

public class BookJsonParserTest {
	
	/******************************
	*
	* setup
	*
	*******************************/
	
	private BookJsonParser sut;
	
	@Before
	public void setup() {
		sut = new BookJsonParser();
	}

	/******************************
	*
	* parseBook Tests
	*
	*******************************/
	
	// happy test - good json string
	@Test
	public void testParseBook() throws ParseException, InvalidBookException{
		String goodBook = "{'id':'123','title':'Test Book'}";
		IBook parsedBook = sut.parseBook(goodBook);
		Assert.assertNotNull(parsedBook);
		Assert.assertEquals("123", parsedBook.getId());
		Assert.assertEquals("Test Book", parsedBook.getTitle());
	}
	
	// bad json string
	@Test (expected = ParseException.class)
	public void testParseBook_MissingParameters() throws ParseException, InvalidBookException{
		String goodBookBadParams = "{}";
		sut.parseBook(goodBookBadParams);
	}
	
	// invalid json string
	@Test (expected = ParseException.class)
	public void testParseBook_InvalidJson() throws ParseException, InvalidBookException{
		String badBookJson = ">crap<";
		sut.parseBook(badBookJson);
	}
	
	/******************************
	*
	* parseBooks Tests
	*
	*******************************/
	
	// happy test - good json string
	@Test
	public void testParseBooks() throws ParseException, InvalidBookException{
		String goodBooks = "[{'title':'Book', 'id' : '123'},{'title':'Book2','id':'456'}]";
		List<IBook> parsedList = sut.parseBooks(goodBooks);
		Assert.assertNotNull(parsedList);
		Assert.assertEquals(parsedList.size(), 2);
		Assert.assertEquals("123", parsedList.get(0).getId());
		Assert.assertEquals("Book", parsedList.get(0).getTitle());
	}
	
	// bad json string
	@Test (expected = ParseException.class)
	public void testParseBooks_MissingParameters() throws ParseException, InvalidBookException{
		String goodBooksBadParams = "[{'title':'Book', 'id' : '123'},{}]";
		sut.parseBooks(goodBooksBadParams);
	}
		
	// invalid json string
	@Test (expected = ParseException.class)
	public void testParseBooks_InvalidJson() throws ParseException, InvalidBookException{
		String badBooksJson = ">crap<";
		sut.parseBooks(badBooksJson);
	}
}

