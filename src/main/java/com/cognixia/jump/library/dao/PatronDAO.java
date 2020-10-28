package com.cognixia.jump.library.dao;

import com.cognixia.jump.library.models.Patron;
import com.cognixia.jump.library.models.PatronHistory;

import java.util.List;

public interface PatronDAO
{
	public List<Patron> getAllPatrons();
	
	public Patron getPatronById(int patId);
	
	public Patron getPatronByUsername(String username);
	
	public boolean addPatron(Patron pat);
	
	public boolean deletePatron(Patron pat);
	
	public boolean updatePatron(Patron pat);
	
	public boolean unfreezePatron(Patron pat);
	
	public boolean freezePatron(Patron pat);
	
	public PatronHistory getPatronHistoryById(int patId);
}
