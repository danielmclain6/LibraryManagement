package com.cognixia.jump.library.dao;

import java.util.List;
import com.cognixia.jump.library.models.Librarian;

public interface LibrarianDAO
{
	public List<Librarian> getAllLibrarians();
	
	public Librarian getLibrarianById(int libId);
	
	public boolean addLibrarian(Librarian lib);
	
	public boolean deleteLibrarian(Librarian lib);
	
	public boolean updateLibrarian(Librarian lib);

}
