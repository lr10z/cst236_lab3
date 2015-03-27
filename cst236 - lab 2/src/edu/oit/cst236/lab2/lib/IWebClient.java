package edu.oit.cst236.lab2.lib;

import org.json.JSONException;

import edu.oit.cst236.lab2.model.core.InvalidBookException;
import edu.oit.cst236.lab2.model.core.InvalidLibraryException;

public interface IWebClient {

	String query(String url) throws InvalidRequestException,
			ConnectionException, JSONException, InvalidLibraryException, InvalidBookException;

}