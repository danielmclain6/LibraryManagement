package com.cognixia.jump.library.DAO;

import java.util.List;

public interface PatronDAO
{
	public List<Patron> getAllPatrons();
	
	public Patron getLibrarianById(int patId) throws PatronNotFoundException;
	
	public boolean addPatron(Patron pat);
	
	public boolean deletePatron(int patId);
	
	public boolean updatePatron(Patron pat);

}
