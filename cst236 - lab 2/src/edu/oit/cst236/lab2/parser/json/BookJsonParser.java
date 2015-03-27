package edu.oit.cst236.lab2.parser.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.oit.cst236.lab2.model.IBook;
import edu.oit.cst236.lab2.model.core.Book;
import edu.oit.cst236.lab2.model.core.InvalidBookException;
import edu.oit.cst236.lab2.parser.IBookParser;
import edu.oit.cst236.lab2.parser.ParseException;

/**
 * A book parser that parses json formatted book objects.
 * 
 * @author nferraro
 *
 */
public class BookJsonParser implements IBookParser {
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESCRIPTION = "description";
	
	@Override
	public List<IBook> parseBooks(String booksJsonString) throws ParseException, InvalidBookException {
		try{
			List<IBook> parsedBooks = new ArrayList<IBook>();
			JSONArray booksArray = new JSONArray(booksJsonString);
			
			for(int i = 0; i < booksArray.length(); ++i ) {
				JSONObject book = booksArray.getJSONObject(i);
			
				IBook parsedBook = _parseBook(book);
				parsedBooks.add(parsedBook);
			}
			return parsedBooks;
		}catch(JSONException e){
			throw new ParseException();
		}
	}
	
	@Override
	public IBook parseBook(String bookJsonString) throws ParseException, InvalidBookException {
		try{
			JSONObject book = new JSONObject(bookJsonString);
			IBook parsedBook = _parseBook(book);
			return parsedBook;
		}catch(JSONException e){
			throw new ParseException();
		}
	}
	
	/**
	 * Parse a JSONObject that represents a book.
	 * @param bookJson A JSONObject representation of a book
	 * @return a parsed Book
	 * @throws ParseException thrown when parsing fails
	 * @throws InvalidBookException 
	 */
	private IBook _parseBook(JSONObject bookJson) throws ParseException, InvalidBookException {
		try {
			Book parsedBook = new Book(bookJson.getString(KEY_ID), bookJson.getString(KEY_TITLE));
			parsedBook.setDescription(bookJson.optString(KEY_DESCRIPTION));
			return parsedBook;
		} catch(Exception e) {
			throw new ParseException();
		}
	}
}
