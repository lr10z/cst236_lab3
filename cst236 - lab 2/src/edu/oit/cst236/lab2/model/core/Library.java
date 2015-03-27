package edu.oit.cst236.lab2.model.core;

import edu.oit.cst236.lab2.model.ILibrary;

/**
 * A Library POJO that follows the ILibrary interface
 * 
 * @author nferraro
 *
 */
public class Library implements ILibrary {
	private final String id;
	private String name;
	private int availableBooks = 0;
	private int unavailableBooks = 0;
	
	/**
	 * Creates an instance of Library.
	 * @param id the id of the library.
	 * @param name the name of the library.
	 */
	public Library(String id, String name) throws InvalidLibraryException {
		
		if( id == null || id.equals("") || name == null || name.equals("") )
		{
			throw new InvalidLibraryException();
		}
		
		this.id = id;
		setName(name);
	}

	@Override
	public String getId() throws InvalidLibraryException {
		return this.id;
	}
	
	/**
	 * Set the name of the library.
	 * @param name the library's name.
	 */
	public void setName(String name) throws InvalidLibraryException {
		
		if( name == null  ||  name.equals("") )
		{
			throw new InvalidLibraryException();
		}
		
		this.name = name;
	}

	@Override
	public String getName() throws InvalidLibraryException {
		return this.name;
	}
	
	/**
	 * Set the number of available books at this library.
	 * @param availableBooks the number of available books.
	 */
	public void setAvailableBooks(int availableBooks) throws InvalidLibraryException {
		
		if( availableBooks < 0 )
		{
			throw new InvalidLibraryException();
		}
		
		this.availableBooks = availableBooks;
	}

	@Override
	public int getAvailableBooks() throws InvalidLibraryException {
		return this.availableBooks;
	}
	
	/**
	 * Set the number of unavailable books at this library.
	 * @param unavailableBooks the number of unavailable books.
	 */
	public void setUnavailableBooks(int unavailableBooks) throws InvalidLibraryException {
		
		if( unavailableBooks < 0 )
		{
			throw new InvalidLibraryException();
		}
		
		this.unavailableBooks = unavailableBooks;
	}

	@Override
	public int getUnavailableBooks() throws InvalidLibraryException {
		return this.unavailableBooks;
	}
}
