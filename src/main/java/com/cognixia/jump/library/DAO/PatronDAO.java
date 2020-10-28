package com.cognixia.jump.library.DAO;

import com.cognixia.jump.library.models.Patron;
import java.util.List;

public interface PatronDAO
{
	public List<Patron> getAllPatrons();
	
	public Patron getPatronById(int patId);
	
	public boolean addPatron(Patron pat);
	
	public boolean deletePatron(Patron pat);
	
	public boolean updatePatron(Patron pat);

}
