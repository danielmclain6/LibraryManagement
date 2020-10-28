package com.cognixia.jump.library.DAO;

import java.util.List;
import com.cognixia.jump.library.models.Librarian;

public interface LibrarianDAO
{
	public List<Librarian> getAllLibrarians();
	
	public Librarian getLibrarianById(int libId);
	
	public boolean addLibrarian(Librarian lib);
	
	public boolean deleteLibrarian(int libId);
	
	public boolean updateLibrarian(Librarian lib);

}
