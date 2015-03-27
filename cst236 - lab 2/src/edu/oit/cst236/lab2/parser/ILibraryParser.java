package edu.oit.cst236.lab2.parser;

import java.util.List;

import org.json.JSONException;

import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;

/**
 * The interface for a Library parser. Library parsers handle parsing library objects and
 * the checkin and checkout response.
 * 
 * @author nferraro
 *
 */
public interface ILibraryParser {
	/**
	 * Parse a library object from the string parameter.
	 * 
	 * @param libraryString The string representation of a library object.
	 * @return A parsed library object
	 * @throws ParseException thrown when a full-fledged library object cannot be parsed.
	 * @throws InvalidLibraryException 
	 */
	public ILibrary parseLibrary(String libraryString) throws ParseException, InvalidLibraryException;
	
	/**
	 * Parse an array of library objects from the string parameter.
	 * 
	 * @param librariesString The string representation of an array of library objects.
	 * @return A parsed list of library objects
	 * @throws ParseException thrown when a full-fledged list of library objects cannot be parsed.
	 * @throws InvalidLibraryException 
	 * @throws JSONException 
	 */
	public List<ILibrary> parseLibraries(String librariesString) throws ParseException, JSONException, InvalidLibraryException;
}