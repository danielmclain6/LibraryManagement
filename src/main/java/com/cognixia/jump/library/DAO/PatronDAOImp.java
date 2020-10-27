package com.cognixia.jump.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Patron;

public class PatronDAOImp implements PatronDAO
{
	private Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Patron> getAllPatrons()
	{
		List<Patron> patList = new ArrayList<>();
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("select * from patron");
				ResultSet rs = pstmt.executeQuery(); 
			)
		{
			while(rs.next())
			{
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				
				Patron pat = new Patron(id, firstName, lastName, username, password, frozen);
				
				patList.add(pat);
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return patList;
	}

	@Override
	public Patron getPatronById(int patId)
	{
		Patron pat = null;
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("select * from patron where patron_id = ?");
			)
		{
			pstmt.setInt(1, patId);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next())
			{
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				
				pat = new Patron(id, firstName, lastName, username, password, frozen);
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return pat;
	}

	@Override
	public boolean addPatron(Patron pat)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("insert into patron values(null,?,?,?,?,?)")) 
		{
			LibrarianDAOImp ld = new LibrarianDAOImp();
			ld.searchUserNameUtility(pat.getUsername());
			pstmt.setString(1, pat.getFirst_name());
			pstmt.setString(2, pat.getLast_name());
			pstmt.setString(3, pat.getUsername());
			pstmt.setString(4, pat.getPassword());
			pstmt.setBoolean(5, pat.isAccount_frozen());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		} catch (UsernameAlreadyExistsException e)
		{
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public boolean deletePatron(Patron pat)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from patron where patron_id = ?")) 
		{	
			pstmt.setInt(1, pat.getId());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) 
			{
				return true;
			}
			
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updatePatron(Patron pat)
	{
		try(PreparedStatement pstmt = conn.prepareStatement("update patron set first_name = ?, last_name = ?, username = ?, password = ?, account_frozen = ? where patron_id = ?")) 
		{
			pstmt.setString(1, pat.getFirst_name());
			pstmt.setString(2, pat.getLast_name());
			pstmt.setString(3, pat.getUsername());
			pstmt.setString(4, pat.getPassword());
			pstmt.setBoolean(5, pat.isAccount_frozen());
			pstmt.setInt(6, pat.getId());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}
	
//	public static void main(String[] args)
//	{
//		PatronDAOImp test = new PatronDAOImp();
//		
//		List<Patron> pats = test.getAllPatrons();
//
//		for(Patron p: pats)
//		{
//			System.out.println(p);
//		}
//		
//		Patron pat = test.getPatronById(11);
//		
//		test.deletePatron(pat);
//
//		pats = test.getAllPatrons();
//		
//		for(Patron p: pats)
//		{
//			System.out.println(p);
//		}
//	}

}
