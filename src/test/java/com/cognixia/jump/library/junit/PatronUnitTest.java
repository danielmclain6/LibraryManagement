package com.cognixia.jump.library.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Patron;

public class PatronUnitTest {
	
	private PatronDAO patronDAO = new PatronDAOImp();
	private Connection conn;
	
	@Before
	public void connectDB() {
		conn = ConnectionManager.getConnection();
	}
	
	@Test
	public void getAllPatrons() {
		List<Patron> patrons = patronDAO.getAllPatrons();
		assertTrue(patrons.size()>0);
	}
	
	@Test
	public void getPatronByIDTest() {
		int patronID = 1;
		Patron patron = patronDAO.getPatronById(patronID);
		
		assertTrue(patronID == patron.getId());
	}
	
	@Test
	public void addDeletePatronTest() {
		Patron patron = new Patron(-1, "Darrick", "Truong", "darrick_truong", "qwerty", true);
		assertTrue(patronDAO.addPatron(patron));
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from patron order by patron_id desc limit 1")) {
			rs.next();
			Patron patron1 = new Patron(
					rs.getInt("patron_id"), 
					rs.getString("first_name"), 
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getBoolean("account_frozen"));
			assertTrue(patronDAO.deletePatron(patron1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updatePatron() {
		Patron patron = new Patron(-1, "Darrick", "Truong", "darrick_truong", "qwerty", true);
		assertTrue(patronDAO.addPatron(patron));
		
		Patron patronEdit = null;
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from patron order by patron_id desc limit 1")) {
			rs.next();
			patronEdit = new Patron(
					rs.getInt("patron_id"), 
					rs.getString("first_name"), 
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getBoolean("account_frozen"));
			
			patronEdit.setFirst_name("darrick");
			patronEdit.setLast_name("truong");
			patronEdit.setUsername("truong_darrick");
			patronEdit.setPassword("QWERTY");
			assertTrue(patronDAO.updatePatron(patronEdit));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(patronDAO.deletePatron(patronEdit));
	}
	
//	@After
//	public void closeConn() {
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	

}
