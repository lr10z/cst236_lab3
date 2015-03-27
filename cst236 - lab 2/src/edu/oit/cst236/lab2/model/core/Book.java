package edu.oit.cst236.lab2.model.core;

import edu.oit.cst236.lab2.model.IBook;

/**
 * A Book POJO that follows the IBook interface.
 * 
 * @author nferraro
 *
 */
public class Book implements IBook {
	private final String id;
	private final String title;
	private String description = "";
	
	/**
	 * Create an instance of a book object.
	 * @param id the id of the book.
	 * @param title the title of the book.
	 * @throws InvalidBookException 
	 */
	public Book(String id, String title) throws InvalidBookException{
		
		if( id == null || id.equals("") || title == null || title.equals("") )
		{
			throw new InvalidBookException();
		}
		else
		{
			this.id = id;
			this.title = title;
		}
		
	}
	
	
	@Override
	public String getId(){	
		return this.id;
	}
	
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Set the book's description.
	 * @param description the description of the book.
	 */
	public void setDescription(String description) throws InvalidBookException {
		
		if( description == null )
		{
			throw new InvalidBookException();
		}
		else
		{
			this.description = description;
		}
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
