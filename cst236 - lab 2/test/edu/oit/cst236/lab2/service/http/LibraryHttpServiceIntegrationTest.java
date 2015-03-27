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
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.service.http.LibraryHttpService;

public class LibraryHttpServiceIntegrationTest {

		
		/******************************
		*
		* setup
		*
		*******************************/
		private LibraryHttpService sut;
		private IWebClient mockClient;
		
		@Before
		public void setup() throws ConnectionException, InvalidLibraryException, InvalidBookException {
			mockClient = Mockito.mock(IWebClient.class);
			sut = new LibraryHttpService(mockClient);
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
			
			String library123 = "{name: 'Book', id : '123', availableBooks : '1', unavailableBooks : 0}";
			
			//mock
			Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn(library123);
			
			// test
			ILibrary library = sut.getLibrary("123");
			Assert.assertEquals("123", library.getId());
			Assert.assertEquals("Book", library.getName());
			Assert.assertEquals(1, library.getAvailableBooks());
			Assert.assertEquals(0, library.getUnavailableBooks());
		}
		
		// bad library
		@Test
		public void testGetLibrary_BadLibrary() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
			// mock
			String goodLibraryBadParams = "{}";
			Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn(goodLibraryBadParams);
			
			// test
			ILibrary library = sut.getLibrary("123");
			Assert.assertNull(library);
		}
		
		/******************************
		*
		* getLibraries Tests
		*
		*******************************/
		
		
		// happy test - valid libraries
		@Test
		public void testGetLibraries() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
			//mock
			String goodLibraries = "[{name: 'Library', id : '1', availableBooks : '10', unavailableBooks : 0},"
					 			 + "{name: 'Library2', id : '2', availableBooks : '20', unavailableBooks : 0}]";
		
			Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn(goodLibraries);
			
			// test
			List<ILibrary> libraries = sut.getLibraries();
			Assert.assertNotNull(libraries);
			Assert.assertEquals(2, libraries.size());
			Assert.assertEquals("1", libraries.get(0).getId());
			Assert.assertEquals("Library", libraries.get(0).getName());
		}
		
		// bad libraries
		@Test
		public void testGetLibraries_BadLibraries() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
			//mock
			String goodLibrarysBadParams = "[{'title':'Book', 'id' : '123'},{}]";
			Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn(goodLibrarysBadParams);
			
			// test
			List<ILibrary> libraries = sut.getLibraries();
			Assert.assertEquals(0, libraries.size());
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
			String goodBook = "{'id':'123','title':'Test Book'}";
			Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn(goodBook);
			
			// test
			IBook book = sut.getLibraryBook("123");
			Assert.assertEquals("123", book.getId());
			Assert.assertEquals("Test Book", book.getTitle());
		}
		
		// bad book
		@Test
		public void testGetLibraryBook_BadBook() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
			// mock
			String goodBookBadParams = "{}";
			Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn(goodBookBadParams);
			
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
			String goodBooks = "[{'title':'Book', 'id' : '123'},{'title':'Book2','id':'456'}]";
			Mockito.when(mockClient.query("http://www.fake.com/books/123")).thenReturn(goodBooks);
			
			// test
			List<IBook> libraryBooks = sut.getLibraryBooks("123");
			Assert.assertEquals("Book", libraryBooks.get(0).getTitle());
			Assert.assertEquals("456", libraryBooks.get(1).getId());
			Assert.assertEquals(2, libraryBooks.size());
		}
		
		// bad library books
		@Test
		public void testGetLibraryBooks_BadBooks() throws JSONException, InvalidRequestException, ConnectionException, InvalidLibraryException, InvalidBookException, ParseException{
			//mock
			String goodBooksBadParams = "[{'title':'Book', 'id' : '123'},{}]";
			Mockito.when(mockClient.query("http://www.fake.com/books/123")).thenReturn(goodBooksBadParams);
			
			// test
			List<ILibrary> libraries = sut.getLibraries();
			Assert.assertEquals(0, libraries.size());
		}
	
}
