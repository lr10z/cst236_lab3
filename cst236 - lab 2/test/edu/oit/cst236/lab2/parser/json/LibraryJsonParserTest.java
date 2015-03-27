package edu.oit.cst236.lab2.parser.json;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;
import edu.oit.cst236.lab2.parser.ParseException;

public class LibraryJsonParserTest {

	/******************************
	*
	* setup
	*
	*******************************/
	
	private LibraryJsonParser sut;
	
	@Before
	public void setup() {
		sut = new LibraryJsonParser();
	}
	
	/******************************
	*
	* parseLibrary Tests
	*
	*******************************/
	
	// happy test - good json string
	@Test
	public void testParseLibrary() throws ParseException, InvalidLibraryException{
		String library = "{name: 'Book', id : '123', availableBooks : '1', unavailableBooks : 0}";
		ILibrary parsedLibrary = sut.parseLibrary(library);
		Assert.assertNotNull(parsedLibrary);
		Assert.assertEquals("123", parsedLibrary.getId());
		Assert.assertEquals("Book", parsedLibrary.getName());
		Assert.assertEquals(1, parsedLibrary.getAvailableBooks());
		Assert.assertEquals(0, parsedLibrary.getUnavailableBooks());
	}

	// bad json string
	@Test (expected = ParseException.class)
	public void testParseLibrary_MissingParameters() throws ParseException, InvalidLibraryException{
		String goodLibraryBadParams = "{}";
		sut.parseLibrary(goodLibraryBadParams);
	}
	
	// invalid json string
	@Test (expected = ParseException.class)
	public void testParseLibrary_InvalidJson() throws ParseException, InvalidLibraryException{
		String badLibraryJson = ">crap<";
		sut.parseLibrary(badLibraryJson);
	}
	
	/******************************
	*
	* parseLibraries Tests
	*
	*******************************/
	
	// happy test - good json string
	@Test
	public void testParseLibraries() throws ParseException, InvalidLibraryException{
		String goodLibraries = "[{name: 'Library', id : '1', availableBooks : '10', unavailableBooks : 0},"
							 + "{name: 'Library2', id : '2', availableBooks : '20', unavailableBooks : 0}]";
		List<ILibrary> parsedList = sut.parseLibraries(goodLibraries);
		Assert.assertNotNull(parsedList);
		Assert.assertEquals(parsedList.size(), 2);
		Assert.assertEquals("1", parsedList.get(0).getId());
		Assert.assertEquals("Library", parsedList.get(0).getName());
	}
	
	// bad json string
	@Test (expected = ParseException.class)
	public void testParseLibraries_MissingParameters() throws ParseException, InvalidLibraryException{
		String goodLibrarysBadParams = "[{'title':'Book', 'id' : '123'},{}]";
		sut.parseLibraries(goodLibrarysBadParams);
	}
		
	// invalid json string
	@Test (expected = ParseException.class)
	public void testParseLibraries_InvalidJson() throws ParseException, InvalidLibraryException{
		String badLibrariesJson = ">crap<";
		sut.parseLibraries(badLibrariesJson);
	}

}
