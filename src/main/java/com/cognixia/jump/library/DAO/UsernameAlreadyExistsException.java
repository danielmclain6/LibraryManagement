package com.cognixia.jump.library.DAO;

public class UsernameAlreadyExistsException extends Exception
{
	private static final long serialVersionUID = -4246844712746736717L;
	
	public UsernameAlreadyExistsException(String username) 
	{
		super("Username " + username + " already exists." );
	}

}
