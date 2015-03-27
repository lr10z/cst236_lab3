package edu.oit.cst236.lab2.parser.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;
import edu.oit.cst236.lab2.model.core.Library;
import edu.oit.cst236.lab2.parser.ILibraryParser;
import edu.oit.cst236.lab2.parser.ParseException;

/**
 * A library parser that parses json formatted library objects.
 * 
 * @author nferraro
 *
 */
public class LibraryJsonParser implements ILibraryParser {
	protected static final String KEY_ID = "id";
	protected static final String KEY_NAME = "name";
	protected static final String KEY_AVAILABLE_BOOKS = "availableBooks";
	protected static final String KEY_UNAVAILABLE_BOOKS = "unavailableBooks";
	
	@Override
	public ILibrary parseLibrary(String libraryJsonString) throws ParseException, InvalidLibraryException {
		try{
			JSONObject libraryJson = new JSONObject(libraryJsonString);
			ILibrary parsedLibrary = _parseLibrary(libraryJson);
			return parsedLibrary;
		}catch(JSONException e){
			throw new ParseException();
		}
	}

	@Override
	public List<ILibrary> parseLibraries(String librariesJsonString) throws ParseException, JSONException, InvalidLibraryException {
		try{
			JSONArray librariesJson = new JSONArray(librariesJsonString);
			
			List<ILibrary> libraries = new ArrayList<ILibrary>();
			for(int i = 0; i < librariesJson.length(); ++i ) {
				Library library = _parseLibrary(librariesJson.getJSONObject(i));
				libraries.add(library);
			}
			
			return libraries;
		}catch(JSONException e){
			throw new ParseException();
		}
	}
	
	/**
	 * Parse a library JSONObject
	 * 
	 * @param libraryJson A JSONObject representing a Library object
	 * @return a parsed Library object
	 * @throws ParseException thrown when parsing fails
	 * @throws InvalidLibraryException 
	 */
	private Library _parseLibrary(JSONObject libraryJson) throws ParseException, InvalidLibraryException {
		try {
			Library parsedLibrary = new Library(libraryJson.getString(KEY_ID), libraryJson.getString(KEY_NAME));
			parsedLibrary.setAvailableBooks(libraryJson.getInt(KEY_AVAILABLE_BOOKS));
			parsedLibrary.setUnavailableBooks(libraryJson.getInt(KEY_UNAVAILABLE_BOOKS));
			return parsedLibrary;
		} catch(Exception e) {
			throw new ParseException();
		}
	}
}
