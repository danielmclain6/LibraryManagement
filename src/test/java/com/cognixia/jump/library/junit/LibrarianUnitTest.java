package com.cognixia.jump.library.junit;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.LibrarianDAOImp;
import com.cognixia.jump.library.models.Librarian;

public class LibrarianUnitTest {
	
	private LibrarianDAO librarianDAO = new LibrarianDAOImp();
	private Connection conn;
	
	@Before
	public void connectDB() {
		conn = ConnectionManager.getConnection();
	}
	
	@Test
	public void getLibrarianByIdTest() {
		assertTrue(librarianDAO.getLibrarianById(1)!=null);
	}
	
	@Test
	public void getAllLibrariansTest() {
		List<Librarian> librarians = librarianDAO.getAllLibrarians();
		assertTrue(librarians.size()>0);
	}
	
	@Test
	public void addDeleteLibrarianTest() {
		Librarian lib = new Librarian(1, "librarian3", "1234");
		assertTrue(librarianDAO.addLibrarian(lib));
		
		try (PreparedStatement pstmt = conn.prepareStatement("select * from librarian order by librarian_id desc limit 1");
				ResultSet rs = pstmt.executeQuery()){
			rs.next();
			Librarian lib2 = librarianDAO.getLibrarianById(rs.getInt("librarian_id"));
			assertTrue(librarianDAO.deleteLibrarian(lib2.getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
 	public void updateLibrarian() {
		Librarian lib = librarianDAO.getLibrarianById(1);
		lib.setUsername("Librarian1");
		lib.setPassword("12345");
		assertTrue(librarianDAO.updateLibrarian(lib));
	}

}
