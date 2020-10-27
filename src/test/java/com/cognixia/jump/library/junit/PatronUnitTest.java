package com.cognixia.jump.library.junit;

import java.sql.Connection;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Patron;

import org.junit.Before;

import junit.framework.TestCase;

public class PatronUnitTest extends TestCase {
	
	private PatronDAO patronDAO = new PatronDAOImp();
	private Connection conn;
	
	@Before
	public void connectDB() {
		conn = ConnectionManager.getConnection();
	}
	
	public void getAllPatrons() {
		List<Patron> patrons = patronDAO.getAllPatrons();
		assertTrue(patrons.size()>0);
	}
}
