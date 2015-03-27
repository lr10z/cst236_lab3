package edu.oit.cst236.lab2.service.http;


import java.util.List;

import org.junit.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.oit.cst236.lab2.lib.ConnectionException;
import edu.oit.cst236.lab2.lib.IWebClient;
import edu.oit.cst236.lab2.lib.InvalidRequestException;
import edu.oit.cst236.lab2.model.IBook;
import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.model.core.InvalidBookException;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;
import edu.oit.cst236.lab2.parser.IBookParser;
import edu.oit.cst236.lab2.parser.ILibraryParser;
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.service.http.LibraryHttpService;

public class LibraryHttpServiceTest {
	
	/******************************
	*
	* setup
	*
	*******************************/
	private LibraryHttpService sut;
	private IWebClient mockClient;
	private IBookParser mockBookParser;
	private ILibraryParser mockLibraryParser;
	
	@Before
	public void setup() throws ConnectionException, InvalidLibraryException, InvalidBookException {
		mockClient = Mockito.mock(IWebClient.class);
		mockLibraryParser = Mockito.mock(ILibraryParser.class);
		mockBookParser = Mockito.mock(IBookParser.class);
		sut = new LibraryHttpService(mockClient);
		sut.setBookParser(mockBookParser);
		sut.setLibraryParser(mockLibraryParser);
	}
	
	/**************************************
	*
	* Ctor Test
	*
	***************************************/
	@Test (expected = ConnectionException.class)
	public void testLibServiceNull() throws ConnectionException {
		sut = new LibraryHttpService(null);
	}
	
	/******************************
	*
	* setParser Tests
	*
	*******************************/
	@Test (expected = InvalidLibraryException.class)
	public void testSetLibraryParser() throws InvalidLibraryException {
		sut.setLibraryParser(null);
	}
	
	
	@Test (expected = InvalidBookException.class)
	public void testSetBookParser() throws InvalidBookException{
		sut.setBookParser(null);
	}
	
	/******************************
	*
	* setWebClient Tests
	*
	*******************************/
	
	@Test (expected = ConnectionException.class)
	public void testSetWebClient_NULL() throws ConnectionException {
		sut.setWebClient(null);
	}
	
	@Test
	public void testSetWebClient() throws ConnectionException {
		sut.setWebClient(mockClient);
	}
	
	
	
	/******************************
	*
	* getLibrary Tests
	*
	*******************************/
	
	// happy test - valid library
	@Test
	public void testGetLibrary() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		ILibrary mockLibrary = Mockito.mock(ILibrary.class);
		Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn("goodjson");
		Mockito.when(mockLibraryParser.parseLibrary("goodjson")).thenReturn(mockLibrary);
		
		// test
		ILibrary library = sut.getLibrary("123");
		Assert.assertEquals(mockLibrary, library);
	}
	
	// bad library
	@Test
	public void testGetLibrary_ParseException() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		// mock
		Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn("badjson");
		Mockito.when(mockLibraryParser.parseLibrary("badjson")).thenThrow(new ParseException());
		
		// test
		Assert.assertNull(sut.getLibrary("123"));
	}
	
	/******************************
	*
	* getLibraries Tests
	*
	*******************************/
	
	
	// happy test - valid library
	@Test
	public void testGetLibraries() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		@SuppressWarnings("unchecked")
		List<ILibrary> mockLibraries = (List<ILibrary>)Mockito.mock(List.class);
		Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn("goodjson");
		Mockito.when(mockLibraryParser.parseLibraries("goodjson")).thenReturn(mockLibraries);
		
		// test
		List<ILibrary> libraries = sut.getLibraries();
		Assert.assertNotNull(libraries);
		Assert.assertEquals(mockLibraries, libraries);
	}
	
	// bad library
	@Test
	public void testGetLibraries_ParseException() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn("badjson");
		Mockito.when(mockLibraryParser.parseLibraries("badjson")).thenThrow(new ParseException());
		
		// test
		Assert.assertNotNull(sut.getLibraries());
	}
	
	
	/******************************
	*
	* getLibraryBook Tests
	*
	*******************************/
	
	
	// happy test - valid library book
	@Test
	public void testGetLibraryBook() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		IBook mockBook = Mockito.mock(IBook.class);
		Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn("goodjson");
		Mockito.when(mockBookParser.parseBook("goodjson")).thenReturn(mockBook);
		
		// test
		IBook book = sut.getLibraryBook("123");
		Assert.assertEquals(mockBook, book);
	}
	
	// bad book
	@Test
	public void testGetLibraryBook_ParseException() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		// mock
		Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn("badjson");
		Mockito.when(mockBookParser.parseBook("badjson")).thenThrow(new ParseException());
		
		// test
		Assert.assertNull(sut.getLibraryBook("123"));
	}
	
	
	
	/******************************
	*
	* getLibraryBooks Tests
	*
	*******************************/
	// happy test - valid library books
	@Test
	public void testGetLibraryBooks() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		@SuppressWarnings("unchecked")
		List<IBook> mockLibraryBooks = (List<IBook>)Mockito.mock(List.class);
		Mockito.when(mockClient.query("http://www.fake.com/books/123")).thenReturn("goodjson");
		Mockito.when(mockBookParser.parseBooks("goodjson")).thenReturn(mockLibraryBooks);
		
		// test
		List<IBook> libraryBooks = sut.getLibraryBooks("123");
		Assert.assertNotNull(libraryBooks);
		Assert.assertEquals(mockLibraryBooks, libraryBooks);
	}
	
	// bad library books
	@Test
	public void testGetLibraryBooks_ParseException() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
		//mock
		Mockito.when(mockClient.query("http://www.fake.com/books/123")).thenReturn("badjson");
		Mockito.when(mockBookParser.parseBooks("badjson")).thenThrow(new ParseException());
		
		// test
		Assert.assertNotNull(sut.getLibraryBooks("123"));
	}

}
