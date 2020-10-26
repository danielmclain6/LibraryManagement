package com.cognixia.jump.library.DAO;

import java.util.List;

public interface LibrarianDAO
{
	public List<Librarian> getAllLibrarians();
	
	public Librarian getLibrarianById(int libId) throws LibrarianNotFoundException;
	
	public boolean addLibrarian(Librarian lib);
	
	public boolean deleteLibraian(int libId);
	
	public boolean updateLibrarian(Librarian lib);

}
