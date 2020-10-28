package com.cognixia.jump.library.dao;

import java.util.List;
import com.cognixia.jump.library.models.Librarian;

public interface LibrarianDAO
{
	public List<Librarian> getAllLibrarians();
	
	public Librarian getLibrarianById(int libId);
	
	public Librarian getLibrarianByUsername(String username);
	
	public boolean addLibrarian(Librarian lib);
	
	public boolean deleteLibrarian(int libId);
	
	public boolean updateLibrarian(Librarian lib);

}
